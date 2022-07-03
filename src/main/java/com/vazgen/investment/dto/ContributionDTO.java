package com.vazgen.investment.dto;

import com.vazgen.investment.model.Contribution;
import com.vazgen.investment.model.entity.ContributionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContributionDTO implements Contribution {

    private long id;
    private long projectId;
    private String account;
    private long amount;
    private boolean approved;
    private LocalDateTime createdAt;
    private Optional<LocalDateTime> updatedAt;

    // FIXME: 03.07.2022 Null в createdAt и updatedAt при создании
    public ContributionDTO(final ContributionEntity request)    {
        this.id = request.getId();
        this.projectId = request.getProjectId();
        this.account = request.getAccount();
        this.amount = request.getAmount();
        this.approved = request.isApproved();
        this.createdAt = request.getCreatedAt();
        this.updatedAt = Optional.ofNullable(request.getUpdatedAt());
    }

}
