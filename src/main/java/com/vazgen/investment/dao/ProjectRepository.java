package com.vazgen.investment.dao;

import com.vazgen.investment.model.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {

    // TODO: 06.06.2022 Не сдавайся и разберись с Iterable (преобразование entity в dto) 
    List<ProjectEntity> findAll();

    // TODO: 08.06.2022 Не сдавайся и разберись с Iterable (преобразование entity в dto)
    List<ProjectEntity> findByTitleContainsIgnoreCase(String title);
}
