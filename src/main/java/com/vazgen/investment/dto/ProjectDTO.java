package com.vazgen.investment.dto;

import com.vazgen.investment.model.Project;
import com.vazgen.investment.model.entity.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO implements Project {

    private long id;
    private String type;
    private int status;
    private long requiredMoney;
    private long collectedMoney;
    private String title;
    // TODO: 15.06.2022 Выдавать общий список без article
    private String article;
    private String preview;
    private List<Long> tagList;
    private LocalDateTime createdAt;
    private Optional<LocalDateTime> updatedAt;

    public ProjectDTO(final ProjectEntity request)  {
        this.id = request.getId();
        this.type = request.getType();
        this.status = request.getStatus();
        this.requiredMoney = request.getRequiredMoney();
        this.collectedMoney = request.getCollectedMoney();
        this.title = request.getTitle();
        this.article = request.getArticle();
        this.preview = request.getPreview();
        this.tagList = request.getTagList();
        this.createdAt = request.getCreatedAt();
        this.updatedAt = Optional.ofNullable(request.getUpdatedAt());
    }

}
