package com.vazgen.investment.dto;

import com.vazgen.investment.model.Tag;
import com.vazgen.investment.model.entity.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO implements Tag {

    private long id;
    private String label;
    private String type;
    private LocalDateTime createdAt;
    private Optional<LocalDateTime> updatedAt;

    public TagDTO(final TagEntity request) {
        this.id = request.getId();
        this.label = request.getLabel();
        this.type = request.getType();
        this.createdAt = request.getCreatedAt();
        this.updatedAt = Optional.ofNullable(request.getUpdatedAt());
    }
}
