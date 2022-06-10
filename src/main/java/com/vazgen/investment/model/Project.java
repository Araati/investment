package com.vazgen.investment.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public interface Project {

    long getId();

    String getType();

    // TODO: 10.06.2022 getStatus

    long getRequiredMoney();

    long getCollectedMoney();

    String getTitle();

    String getArticle();

    String getPreview();

    // TODO: 06.06.2022 TAG OBJECT 
    ArrayList<String> getTagList();

    LocalDateTime getCreatedAt();

    Optional<LocalDateTime> getUpdatedAt();

}
