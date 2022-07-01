package com.vazgen.investment.service;

import com.vazgen.investment.dto.UserDTO;
import com.vazgen.investment.exception.ResourceNotFoundException;
import com.vazgen.investment.security.User;
import com.vazgen.investment.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User create(UserCreation data);

    User update(long id, UserUpdateRequest data);

    UserDetails update(long id, UserDetailsUpdateRequestDTO data);

    @Override
    default org.springframework.security.core.userdetails.UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        return findByEmail(s).map(UserDTO::new).orElseThrow(() -> new ResourceNotFoundException("User", s));
    }

    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);

    Optional<UserDetails> findUserDetailsByUserId(long id);

    Page<User> findByCriteria(PageableUserCriteria criteria, Pageable pageable);

    default User mustFindById(final long id){
        return findById(id).map(UserDTO::new).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }
}
