package com.vazgen.investment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vazgen.investment.security.permission.Authority;
import com.vazgen.investment.util.UserCreate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserCreateDTO implements UserCreate {

    @JsonProperty(value = "username", required = true)
    private String username;

    @Size(min = 6)
    @JsonProperty(value = "password", required = true)
    private String password;

    @JsonProperty(value = "authorities")
    private List<Authority> authorities;

    public List<Authority> getAuthorities() {
        return authorities == null ? Collections.emptyList() : authorities;
    }
}
