package com.vazgen.investment.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProjectCreateDTO {

    @NotBlank
    @JsonProperty(value = "type", required = true)
    private String type;

    @JsonProperty(value = "status", required = true)
    private int status;

    @Positive
    @JsonProperty(value = "requiredMoney", required = true)
    private long requiredMoney;

    @NotBlank
    @JsonProperty(value = "title", required = true)
    private String title;

    @NotBlank
    @JsonProperty(value = "article", required = true)
    private String article;

    @NotBlank
    @JsonProperty(value = "preview", required = true)
    private String preview;

    // TODO: 03.07.2022 Не обязательное поле. Что тут с null?
    @JsonProperty(value = "tagList")
    private List<Long> tagList;


    @JsonCreator
    public ProjectCreateDTO(final String type, final int status, final long requiredMoney, final String title, final String article, final String preview, final List<Long> tagList) {
        this.type = type;
        this.status = status;
        this.requiredMoney = requiredMoney;
        this.title = title;
        this.article = article;
        this.preview = preview;
        this.tagList = tagList;
    }
}
