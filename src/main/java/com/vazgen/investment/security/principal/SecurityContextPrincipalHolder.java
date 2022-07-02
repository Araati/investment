package com.vazgen.investment.security.principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityContextPrincipalHolder implements PrincipalHolder {
    @Override
    public Optional<Principal> getPrincipal() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(x -> (Principal) x.getPrincipal());
    }

    /*@Override
    public Optional<String> getAccessToken() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(Authentication::isAuthenticated)
                .filter(x -> !x.getPrincipal().equals("anonymousUser"))
                .filter(x -> !((String)x.getCredentials()).isBlank())
                .map(x -> (String) x.getCredentials());
    }*/
}
