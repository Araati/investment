package com.vazgen.investment.security;

import com.vazgen.investment.security.permission.Authority;
import com.vazgen.investment.security.principal.PrincipalHolder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultGuard implements Guard {

    private final PrincipalHolder principalHolder;

    @Override
    public boolean has(final Authority authority) {
        return principalHolder.get().getAuthorities().contains(authority);
    }
}
