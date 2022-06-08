package com.vazgen.investment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public class ProjectUpdateDTO {

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "status")
    private int status;

    @JsonProperty(value = "requiredMoney")
    private long requiredMoney;

    @JsonProperty(value = "collectedMoney")
    private long collectedMoney;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "article")
    private String article;

    @JsonProperty(value = "preview")
    private String preview;

    @JsonProperty(value = "tagList")
    private ArrayList<String> tagList;

    public Optional<String> getType()   {
        return Optional.ofNullable(type);
    }

    // TODO: 08.06.2022 Проверить, будет ли та же проблема, что и с RequiredMoney
    public Optional<Integer> getStatus()    {
        return Optional.ofNullable(status);
    }

    public Optional<Long> getRequiredMoney()   {
        if(requiredMoney == 0)
            return Optional.ofNullable(null);
        else
            return Optional.ofNullable(requiredMoney);
    }

    public Optional<Long> getCollectedMoney()   {
        if(collectedMoney == 0)
            return Optional.ofNullable(null);
        else
            return Optional.ofNullable(collectedMoney);
    }

    public Optional<String> getTitle()   {
        return Optional.ofNullable(title);
    }

    public Optional<String> getArticle()   {
        return Optional.ofNullable(article);
    }

    public Optional<String> getPreview()   {
        return Optional.ofNullable(preview);
    }

    // TODO: 06.06.2022 У меня большие сомнения в работоспособности этой шняги
    public Optional<ArrayList<String>> getTagList()   {
        return Optional.ofNullable(tagList);
    }

}
