package com.vazgen.investment.security;

import com.vazgen.investment.security.permission.Authority;

public interface Guard {

    boolean has(Authority authority);

    default boolean isUser() {
        return has(Authority.ROLE_USER);
    }

    default boolean isAdmin() {
        return has(Authority.ROLE_ADMIN);
    }

    default boolean isSystem() {
        return has(Authority.ROLE_SYSTEM);
    }
}
