package com.vazgen.investment.security.provider;

import com.vazgen.investment.security.User;
import com.vazgen.investment.security.jwt.JwtTokenIssuer;
import com.vazgen.investment.security.principal.DefaultPrincipal;
import com.vazgen.investment.security.principal.Principal;
import com.vazgen.investment.security.token.JwtAuthentication;
import com.vazgen.investment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;

@RequiredArgsConstructor
public class UserCredentialsAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtTokenIssuer tokenIssuer;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        // TODO: 02.07.2022 ЧТО ЗДЕСЬ БУДЕТ ПОД USERNAME?
        final String username = (String) authentication.getPrincipal();
        final String password = (String) authentication.getCredentials();
        final User user = userService.findByUsername(username)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User not found"));

        verifyCredentials(user, password);

        Principal principal = new DefaultPrincipal(
                String.valueOf(user.getId()),
                user.getUsername(),
                LocalDateTime.now().plusHours(1),
                new HashSet<>(user.getAuthorities())
        );

        String token = tokenIssuer.sign(principal);
        return new JwtAuthentication(token, principal, user.getAuthorities());
    }

    private void verifyCredentials(final User user, final String password) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationServiceException("Password does not matches");
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
