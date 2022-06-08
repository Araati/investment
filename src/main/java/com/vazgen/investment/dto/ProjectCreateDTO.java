package com.vazgen.investment.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class ProjectCreateDTO {

    // TODO: 06.06.2022 NOT EMPTY, NOT PLAIN
    // TODO: 06.06.2022 А здесь вообще нужно required = true?

    @NotBlank
    @JsonProperty(value = "type", required = true)
    private String type;

    // TODO: 08.06.2022 А нужен ли status при создании?
    @JsonProperty(value = "status", required = true)
    private int status;

    // TODO: 06.06.2022 А всегда ли нужны деньги?
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

    @JsonProperty(value = "tagList", required = true)
    private ArrayList<String> tagList;


    @JsonCreator
    public ProjectCreateDTO(final String type, final int status, final long requiredMoney, final String title, final String article, final String preview, final ArrayList<String> tagList) {
        this.type = type;
        this.status = status;
        this.requiredMoney = requiredMoney;
        this.title = title;
        this.article = article;
        this.preview = preview;
        this.tagList = tagList;
    }
}