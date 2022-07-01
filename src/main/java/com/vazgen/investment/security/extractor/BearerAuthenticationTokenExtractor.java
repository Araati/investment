package com.vazgen.investment.security.extractor;

import java.util.Optional;

public class BearerAuthenticationTokenExtractor implements AuthenticationTokenExtractor {

    private static final String AUTHENTICATION_SCHEMA = "Bearer";

    @Override
    public Optional<String> extract(final String value) {
        if (value == null || !value.startsWith(AUTHENTICATION_SCHEMA) || value.length() <= AUTHENTICATION_SCHEMA.length()) {
            return Optional.empty();
        }
        return Optional.of(value.substring(AUTHENTICATION_SCHEMA.length()));
    }
}