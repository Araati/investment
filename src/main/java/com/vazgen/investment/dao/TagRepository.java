package com.vazgen.investment.dao;

import com.vazgen.investment.model.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<TagEntity, Long> {}
