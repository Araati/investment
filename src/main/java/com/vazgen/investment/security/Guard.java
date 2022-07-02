package com.vazgen.investment.security;

import com.vazgen.investment.security.permission.Authority;

public interface Guard {

    boolean has(Authority authority);

    default boolean isAdmin() {
        return has(Authority.ROLE_ADMIN);
    }

}
