package com.vazgen.investment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vazgen.investment.security.permission.Authority;
import com.vazgen.investment.util.UserCreation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserCreationDTO implements UserCreation {

    @JsonProperty(value = "username", required = true)
    private String username;

    @Email
    @NotBlank
    @JsonProperty(value = "email", required = true)
    private String email;

    @Size(min = 6)
    @JsonProperty(value = "password", required = true)
    private String password;

    @JsonProperty(value = "authorities")
    private List<Authority> authorities;

    public String getUsername() {
        return username == null ? email.substring(0, email.indexOf("@")) : username;
    }

    public List<Authority> getAuthorities() {
        return authorities == null ? Collections.emptyList() : authorities;
    }
}
