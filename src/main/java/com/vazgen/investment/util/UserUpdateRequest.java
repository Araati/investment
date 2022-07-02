package com.vazgen.investment.util;

import com.vazgen.investment.security.permission.Authority;

import java.util.List;
import java.util.Optional;

public interface UserUpdateRequest {

    Optional<Boolean> isEnabled();
    Optional<List<Authority>> getAuthorities();
}
