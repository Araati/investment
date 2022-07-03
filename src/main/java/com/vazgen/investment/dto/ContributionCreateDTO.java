package com.vazgen.investment.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class ContributionCreateDTO {

    @JsonProperty(value = "projectId", required = true)
    private long projectId;

    @NotBlank
    @JsonProperty(value = "account", required = true)
    private String account;

    @Positive
    @JsonProperty(value = "amount", required = true)
    private long amount;

    @JsonCreator
    public ContributionCreateDTO(final long projectId, final String account, final long amount)   {
        this.projectId = projectId;
        this.account = account;
        this.amount = amount;
    }

}
