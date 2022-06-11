package com.vazgen.investment.dao;

import com.vazgen.investment.model.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {

    // TODO: 08.06.2022 Не сдавайся и разберись с Iterable (преобразование entity в dto)
    Iterable<ProjectEntity> findByTitleContainsIgnoreCase(String title);
}
