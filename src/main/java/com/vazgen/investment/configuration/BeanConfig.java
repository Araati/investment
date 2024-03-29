package com.vazgen.investment.configuration;

import com.vazgen.investment.security.Guard;
import com.vazgen.investment.security.handler.CommonAuthenticationFailureHandler;
import com.vazgen.investment.security.jwt.JwtTokenIssuer;
import com.vazgen.investment.security.principal.PrincipalHolder;
import com.vazgen.investment.security.principal.SecurityContextPrincipalHolder;
import com.vazgen.investment.security.provider.JwtTokenAuthenticationProvider;
import com.vazgen.investment.security.provider.UserCredentialsAuthenticationProvider;
import com.vazgen.investment.security.verifier.JwtAuthenticationVerifier;
import com.vazgen.investment.service.UserService;
import com.vazgen.investment.util.RequestIdHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Import({
        JwtTokenAuthenticationProvider.class,
        CommonAuthenticationFailureHandler.class
})
public class BeanConfig {

    @Bean
    public PrincipalHolder principalHolder() {
        return new SecurityContextPrincipalHolder();
    }

    @Bean
    public Guard guard(final PrincipalHolder principalHolder) {
        return new Guard(principalHolder);
    }

    @Bean
    public JwtAuthenticationVerifier authenticationVerifier(@Value("${auth.secret}") final String secret) {
        return new JwtAuthenticationVerifier(secret);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RequestIdHolder requestIdHolder() {
        return new RequestIdHolder();
    }

    @Bean
    public JwtTokenIssuer tokenIssuer(@Value("${auth.secret}") final String secret) {
        return new JwtTokenIssuer(secret);
    }

    @Bean
    public UserCredentialsAuthenticationProvider userCredentialsAuthenticationProvider(
            final JwtTokenIssuer tokenIssuer,
            final PasswordEncoder passwordEncoder,
            final UserService userService
    ) {

        return new UserCredentialsAuthenticationProvider(
                passwordEncoder,
                userService,
                tokenIssuer
        );
    }
}
