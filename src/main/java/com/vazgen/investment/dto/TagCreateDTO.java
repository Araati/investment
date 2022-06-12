package com.vazgen.investment.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class TagCreateDTO {

    @NotBlank
    @JsonProperty(value = "label", required = true)
    private String label;

    @JsonProperty(value = "type", required = true)
    private String type;

    @JsonCreator
    public TagCreateDTO(final String label, final String type) {
        this.label = label;
        this.type = type;
    }
}
