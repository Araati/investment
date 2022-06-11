package com.vazgen.investment.dao;

import com.vazgen.investment.model.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {

    Iterable<ProjectEntity> findByTitleContainsIgnoreCase(String title);
}
