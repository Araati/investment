package com.vazgen.investment.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsUpdateRequestDTO {

    @JsonProperty(value = "person_id", required = true)
    private String personId;
}
