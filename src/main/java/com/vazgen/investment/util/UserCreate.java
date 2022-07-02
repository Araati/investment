package com.vazgen.investment.util;

import com.vazgen.investment.security.permission.Authority;

import java.util.List;

public interface UserCreate {
    String getUsername();
    String getPassword();
    List<Authority> getAuthorities();
}
