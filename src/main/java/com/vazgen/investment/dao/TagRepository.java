package com.vazgen.investment.dao;

import com.vazgen.investment.model.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<TagEntity, Long> {


    // TODO: 10.06.2022 Не сдавайся и разберись с Iterable (преобразование entity в dto)
    List<TagEntity> findAll();
}
