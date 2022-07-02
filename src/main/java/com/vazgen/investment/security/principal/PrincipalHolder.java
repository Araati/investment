package com.vazgen.investment.security.principal;

import org.springframework.security.access.AccessDeniedException;

import java.util.Optional;

public interface PrincipalHolder {

    Optional<Principal> getPrincipal();

    default Principal get() {
        return getPrincipal().orElseThrow(() -> new AccessDeniedException("Undefined principal"));
    }

    /*Optional<String> getAccessToken();

    default String mustGetAccessToken() {
        return getAccessToken().orElseThrow(() -> new RuntimeException("Not found access token"));
    }

    default boolean isAuthorized() {
        return getAccessToken().isPresent();
    }*/
}
