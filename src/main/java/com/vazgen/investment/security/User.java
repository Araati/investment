package com.vazgen.investment.security;

import com.nimbusds.jose.shaded.json.annotate.JsonIgnore;
import com.vazgen.investment.security.permission.Authority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface User extends UserDetails {

    long getId();
    String getUsername();
    String getPassword();
    List<Authority> getAuthorities();
    LocalDateTime getCreatedAt();
    Optional<LocalDateTime> getUpdatedAt();

    @Override
    @JsonIgnore
    default boolean isAccountNonExpired(){
        return true;
    }

    @Override
    @JsonIgnore
    default boolean isAccountNonLocked(){
        return true;
    }

    @Override
    @JsonIgnore
    default boolean isCredentialsNonExpired(){
        return true;
    }
}
