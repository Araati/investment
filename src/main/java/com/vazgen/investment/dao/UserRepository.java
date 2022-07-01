package com.vazgen.investment.dao;

import com.vazgen.investment.dto.UserDTO;
import com.vazgen.investment.exception.ResourceNotFoundException;
import com.vazgen.investment.model.entity.UserEntity;
import com.vazgen.investment.security.User;
import com.vazgen.investment.util.PageableUserCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>, CrudRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByEmail(String email);

    default UserEntity mustFindById(long id){
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    default Page<User> findByCriteria(final PageableUserCriteria criteria, final Pageable pageable){
        return findAll(pageable).map(UserDTO::new); //todo: implements criteria
    }
}
