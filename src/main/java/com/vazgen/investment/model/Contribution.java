package com.vazgen.investment.model;

import java.time.LocalDateTime;
import java.util.Optional;

public interface Contribution {

    long getId();

    long getProjectId();

    String getAccount();

    long getAmount();

    boolean isApproved();

    LocalDateTime getCreatedAt();

    Optional<LocalDateTime> getUpdatedAt();

}
