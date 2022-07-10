package com.vazgen.investment.dao;

import com.vazgen.investment.model.entity.ContributionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ContributionRepository extends CrudRepository<ContributionEntity, Long> {

    Optional<List<ContributionEntity>> findAllByProjectId(long id);
    Optional<List<ContributionEntity>> findAllByProjectIdAndApproved(long id, boolean approved);

    Optional<List<ContributionEntity>> findAllByApproved(boolean approved);

}
