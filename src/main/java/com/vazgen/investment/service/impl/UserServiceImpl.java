package com.vazgen.investment.service.impl;

import com.vazgen.investment.dao.UserDetailsRepository;
import com.vazgen.investment.dao.UserRepository;
import com.vazgen.investment.dto.UserDTO;
import com.vazgen.investment.dto.UserDetailsDTO;
import com.vazgen.investment.exception.ResourceNotFoundException;
import com.vazgen.investment.model.entity.UserAuthorityEntity;
import com.vazgen.investment.model.entity.UserDetailsEntity;
import com.vazgen.investment.model.entity.UserEntity;
import com.vazgen.investment.security.User;
import com.vazgen.investment.service.UserService;
import com.vazgen.investment.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(final UserCreation data) {
        User user = new UserDTO(userRepository.save(new UserEntity(
                data.getUsername(),
                data.getEmail(),
                passwordEncoder.encode(data.getPassword()),
                data.getAuthorities()
        )));

        userDetailsRepository.save(new UserDetailsEntity(user.getId()));

        return user;
    }

    @Override
    public Optional<User> findById(final long id) {
        return userRepository.findById(id).map(UserDTO::new);
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email).map(UserDTO::new);
    }

    @Override
    public Optional<UserDetails> findUserDetailsByUserId(final long id) {
        return userDetailsRepository.findByUserId(id).map(UserDetailsDTO::new);
    }

    @Override
    public Page<User> findByCriteria(
            final PageableUserCriteria criteria, final Pageable pageable
    ) {
        return userRepository.findByCriteria(criteria, pageable);
    }

    @Override
    public User update(final long id, final UserUpdateRequest data) {
        UserEntity entity = userRepository.mustFindById(id);
        entity = entity.withEnabled(data.isEnabled().orElse(entity.isEnabled()))
                .withEmailConfirmed(data.isEmailConfirmed().orElse(entity.isEmailConfirmed()));
        if(data.getAuthorities().isPresent()){
            final List<UserAuthorityEntity> newAuthorities = data.getAuthorities()
                    .get()
                    .stream()
                    .map(UserAuthorityEntity::new)
                    .collect(Collectors.toList());
            entity = entity.withAuthorities(newAuthorities);
        }
        return new UserDTO(userRepository.save(entity));
    }

    public UserDetails update(final long id, final UserDetailsUpdateRequestDTO data) {
        final UserDetailsEntity detailsEntity = userDetailsRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserDetails.user_id", id));

        return new UserDetailsDTO(userDetailsRepository.save(detailsEntity.withPersonId(data.getPersonId())));
    }
}
