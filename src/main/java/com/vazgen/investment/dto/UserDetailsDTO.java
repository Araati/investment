package com.vazgen.investment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vazgen.investment.model.entity.UserDetailsEntity;
import com.vazgen.investment.util.UserDetails;
import lombok.*;

import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDetailsDTO implements UserDetails {

    @JsonProperty(value = "id", required = true)
    private long id;

    @JsonProperty(value = "user_id", required = true)
    private long userId;

    @JsonProperty(value = "person_id", required = true)
    private String personId;

    public UserDetailsDTO(final UserDetailsEntity source){
        this(
                source.getId(),
                source.getUserId(),
                source.getPersonId()
        );
    }

    public UserDetailsDTO(final UserDetails source) {
        this(
                source.getId(),
                source.getUserId(),
                source.getPersonId().orElse(null)
        );
    }

    public Optional<String> getPersonId() {
        return Optional.ofNullable(personId);
    }
}
