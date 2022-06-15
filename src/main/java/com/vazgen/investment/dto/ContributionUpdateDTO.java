package com.vazgen.investment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public class ContributionUpdateDTO {

    @JsonProperty(value = "projectId")
    private Long projectId;

    @JsonProperty(value = "account")
    private String account;

    @JsonProperty(value = "amount")
    private Long amount;

    @JsonProperty(value = "approved")
    private Boolean approved;

    public Optional<Long> getProjectId()    {
        return Optional.ofNullable(projectId);
    }

    public Optional<String> getAccount()    {
        return Optional.ofNullable(account);
    }

    public Optional<Long> getAmount()   {
        return Optional.ofNullable(amount);
    }

    public Optional<Boolean> isApproved()   {
        return Optional.ofNullable(approved);
    }

}
