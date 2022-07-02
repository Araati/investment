package com.vazgen.investment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vazgen.investment.model.entity.UserDetailsEntity;
import com.vazgen.investment.util.UserDetails;
import lombok.*;

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

    public UserDetailsDTO(final UserDetailsEntity source){
        this(
                source.getId(),
                source.getUserId()
        );
    }

    public UserDetailsDTO(final UserDetails source) {
        this(
                source.getId(),
                source.getUserId()
        );
    }
}
