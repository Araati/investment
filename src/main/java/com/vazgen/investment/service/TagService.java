package com.vazgen.investment.service;

import com.vazgen.investment.dao.TagRepository;
import com.vazgen.investment.dto.TagCreateDTO;
import com.vazgen.investment.dto.TagDTO;
import com.vazgen.investment.dto.TagUpdateDTO;
import com.vazgen.investment.exception.ResourceNotFoundException;
import com.vazgen.investment.model.Tag;
import com.vazgen.investment.model.entity.TagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag create(final TagCreateDTO request)   {
        TagEntity entity = new TagEntity(request);
        tagRepository.save(entity);
        return new TagDTO(entity);
    }

    public Tag update(final TagUpdateDTO request, final long id)   {
        TagEntity entity = tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag", id));
        entity = entity.withLabel(request.getLabel().orElse(entity.getLabel()))
                .withType(request.getType().orElse(entity.getType()))
                .withUpdatedAt(LocalDateTime.now());
        // TODO: 10.06.2022 Костыль на updated_at
        tagRepository.save(entity);
        return new TagDTO(entity);
    }

    public void delete(final long id)   {
        TagEntity entity = tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag", id));
        tagRepository.delete(entity);
    }

    public Tag findById(final long id)  {
        return new TagDTO(tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag", id)));
    }

    public List<Tag> findAll()  {
        List<TagEntity> tagEntities = new ArrayList<>();
        tagRepository.findAll().forEach(tagEntities::add);
        return tagEntities.stream().map(TagDTO::new).collect(Collectors.toList());
    }
}
