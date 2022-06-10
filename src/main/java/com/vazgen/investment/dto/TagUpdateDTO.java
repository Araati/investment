package com.vazgen.investment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public class TagUpdateDTO {

    // TODO: 10.06.2022 Может быть пустым
    @JsonProperty(value = "label")
    private String label;

    @JsonProperty(value = "type")
    private int type;

    public Optional<String> getLabel()   {
        return Optional.ofNullable(label);
    }

    public Optional<Integer> getType()    {
        return Optional.ofNullable(type);
    }

}
