package com.vazgen.investment.security.principal;

import com.vazgen.investment.security.permission.Authority;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

public interface Principal extends Serializable {
    String getSubject();
    String getPersonId();
    String getUsername();
    LocalDateTime getTokenExpirationDate();
    Set<Authority> getAuthorities();
}
