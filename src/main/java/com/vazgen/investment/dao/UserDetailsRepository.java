package com.vazgen.investment.dao;

import com.vazgen.investment.model.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends PagingAndSortingRepository<UserDetailsEntity, Long>, CrudRepository<UserDetailsEntity, Long>, JpaSpecificationExecutor<UserDetailsEntity> {
    Optional<UserDetailsEntity> findByUserId(long id);
    Optional<UserDetailsEntity> findByPersonId(long id);
}
