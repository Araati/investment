package com.vazgen.investment.security.converter;

import com.vazgen.investment.security.extractor.AuthenticationTokenExtractor;
import com.vazgen.investment.security.extractor.BearerAuthenticationTokenExtractor;
import com.vazgen.investment.security.token.BearerTokenAuthenticationToken;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

public class BearerTokenAuthenticationConverter implements AuthenticationConverter {

    private final AuthenticationTokenExtractor tokenExtractor = new BearerAuthenticationTokenExtractor();

    @Override
    public Authentication convert(final HttpServletRequest httpServletRequest) {
        return tokenExtractor.extract(() -> httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION))
                .map(BearerTokenAuthenticationToken::new)
                .orElse(null);
    }
}
