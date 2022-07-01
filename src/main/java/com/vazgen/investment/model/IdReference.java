package com.vazgen.investment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
public interface IdReference {

    @JsonProperty(value = "id", required = true)
    long getId();
}
