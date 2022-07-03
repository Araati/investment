package com.vazgen.investment.model.entity;

import com.vazgen.investment.dto.ContributionCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "contribution")
public class ContributionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private long projectId;

    @Column(name = "account")
    private String account;

    @Column(name = "amount")
    private long amount;

    @Column(name = "approved")
    private boolean approved;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ContributionEntity(final ContributionCreateDTO request)  {
        this.projectId = request.getProjectId();
        this.account = request.getAccount();
        this.amount = request.getAmount();
        this.approved = false;
    }

}
