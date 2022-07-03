package com.vazgen.investment.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface Project {

    long getId();

    String getType();

    int getStatus();

    long getRequiredMoney();

    long getCollectedMoney();

    String getTitle();

    String getArticle();

    String getPreview();

    List<Long> getTagList();

    List<Long> getContributionList();

    LocalDateTime getCreatedAt();

    Optional<LocalDateTime> getUpdatedAt();

}
