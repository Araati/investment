package com.vazgen.investment.facade;

import com.vazgen.investment.dto.TagCreateDTO;
import com.vazgen.investment.dto.TagUpdateDTO;
import com.vazgen.investment.model.Tag;
import com.vazgen.investment.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TagFacade {

    private final TagService tagService;

    public Tag create(final TagCreateDTO request)   {
        return tagService.create(request);
    }

    public Tag update(final TagUpdateDTO request, final long id)   {
        return tagService.update(request, id);
    }

    public void delete(final long id)    {
        tagService.delete(id);
    }

    public Tag findById(final long id)  {
        return tagService.findById(id);
    }

    public List<Tag> findAll()  {
        return tagService.findAll();
    }
}
