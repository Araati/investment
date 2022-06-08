package com.vazgen.investment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// TODO: 08.06.2022 CRITERIA
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectFindDTO {

    @JsonProperty(value = "title")
    String title;

}
