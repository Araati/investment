package com.vazgen.investment.dao;

import com.vazgen.investment.exception.ResourceNotFoundException;
import com.vazgen.investment.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByUsername(String username);

    default UserEntity mustFindById(long id){
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

}
