package com.vazgen.investment.security.provider;

import com.vazgen.investment.security.User;
import com.vazgen.investment.security.principal.DefaultPrincipal;
import com.vazgen.investment.security.principal.Principal;
import com.vazgen.investment.security.principal.TokenIssuer;
import com.vazgen.investment.security.token.JwtAuthentication;
import com.vazgen.investment.service.UserDetailsService;
import com.vazgen.investment.service.UserService;
import com.vazgen.investment.util.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;

@RequiredArgsConstructor
public class UserCredentialsAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final TokenIssuer tokenIssuer;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        // TODO: 02.07.2022 ЧТО ЗДЕСЬ БУДЕТ ПОД USERNAME?
        final String username = (String) authentication.getPrincipal();
        final String password = (String) authentication.getCredentials();
        final User user = userService.findByUsername(username)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User not found"));
        final UserDetails userDetails = userDetailsService.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("User details not found"));

        if (!user.isEnabled()) {
            throw new DisabledException("User is disabled");
        }
        if (!user.isAccountNonLocked()) {
            throw new LockedException("User is locked");
        }
        if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException("Account expired");
        }
        if (!user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("Credentials expired");
        }

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
