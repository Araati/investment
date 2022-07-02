package com.vazgen.investment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vazgen.investment.model.entity.UserAuthorityEntity;
import com.vazgen.investment.model.entity.UserEntity;
import com.vazgen.investment.security.User;
import com.vazgen.investment.security.permission.Authority;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements User {

    @Positive
    @JsonProperty(value = "id", required = true)
    private long id;

    @Getter(AccessLevel.NONE)
    @JsonProperty(value = "username")
    private String username;

    @JsonIgnore
    private String password;

    @JsonProperty(value = "authorities", required = true)
    private List<Authority> authorities;

    @JsonProperty(value = "enabled", required = true)
    private boolean enabled;

    @JsonProperty(value = "created_at", required = true)
    private LocalDateTime createdAt;

    @JsonProperty(value = "updated_at")
    private LocalDateTime updatedAt;

    public UserDTO(final UserEntity source) {
        this(
                source.getId(),
                source.getUsername(),
                source.getPassword(),
                source.getAuthorities().stream().map(UserAuthorityEntity::getAuthority).collect(Collectors.toList()),
                source.isEnabled(),
                source.getCreatedAt(),
                source.getUpdatedAt()
        );
    }

    public UserDTO(final User source) {
        this(
                source.getId(),
                source.getUsername(),
                source.getPassword(),
                source.getAuthorities(),
                source.isEnabled(),
                source.getCreatedAt(),
                source.getUpdatedAt().orElse(null)
        );
    }

    @Override
    public Optional<LocalDateTime> getUpdatedAt() {
        return Optional.ofNullable(updatedAt);
    }

    @Override
    public String getUsername() {
        return username;
    }

}
