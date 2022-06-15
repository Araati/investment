package com.vazgen.investment.dao;

import com.vazgen.investment.model.entity.ContributionEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContributionRepository extends CrudRepository<ContributionEntity, Long> {
}
