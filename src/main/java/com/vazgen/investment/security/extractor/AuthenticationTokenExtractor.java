package com.vazgen.investment.security.extractor;

import java.util.Optional;
import java.util.function.Supplier;

public interface AuthenticationTokenExtractor {

    Optional<String> extract(final String value);

    default Optional<String> extract(final Supplier<String> authHeaderSupplier) {
        return extract(authHeaderSupplier.get());
    }
}