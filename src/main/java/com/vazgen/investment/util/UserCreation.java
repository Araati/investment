package com.vazgen.investment.util;

import com.vazgen.investment.security.permission.Authority;

import java.util.List;

public interface UserCreation {
    String getUsername();
    String getEmail();
    String getPassword();
    List<Authority> getAuthorities();
}
