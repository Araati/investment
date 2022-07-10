package com.vazgen.investment.model.entity;

import com.vazgen.investment.dto.ProjectCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "type")
    private String type;

    // TODO: 08.06.2022 Индексирование
    @Column(name = "status")
    private int status;

    @Column(name = "required_money")
    private long requiredMoney;

    @Column(name = "collected_money")
    private long collectedMoney;

    @Column(name = "title")
    private String title;

    @Column(name = "article")
    private String article;

    @Column(name = "preview")
    private String preview;

    @ElementCollection
    @Column(name = "tags")
    private List<Long> tagList;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ProjectEntity(final ProjectCreateDTO request)    {
        this.type = request.getType();
        this.status = request.getStatus();
        this.requiredMoney = request.getRequiredMoney();
        this.title = request.getTitle();
        this.article = request.getArticle();
        this.preview = request.getPreview();
        this.tagList = request.getTagList();
    }
}
