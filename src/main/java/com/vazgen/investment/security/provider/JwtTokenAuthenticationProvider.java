package com.vazgen.investment.security.provider;

import com.vazgen.investment.security.principal.Principal;
import com.vazgen.investment.security.token.BearerTokenAuthenticationToken;
import com.vazgen.investment.security.token.JwtAuthentication;
import com.vazgen.investment.security.verifier.JwtAuthenticationVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtTokenAuthenticationProvider implements AuthenticationProvider {

    private final JwtAuthenticationVerifier verifier;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();
        Principal principal = verifier.verify(token);
        LocalDateTime tokenExpirationDate = principal.getTokenExpirationDate();

        if(tokenExpirationDate.isBefore(LocalDateTime.now())){
            throw new CredentialsExpiredException("Token was expired");
        }

        return new JwtAuthentication(token, principal, principal.getAuthorities());
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.isAssignableFrom(BearerTokenAuthenticationToken.class);
    }
}