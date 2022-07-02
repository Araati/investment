package com.vazgen.investment.service.impl;

import com.vazgen.investment.dao.UserRepository;
import com.vazgen.investment.dto.UserDTO;
import com.vazgen.investment.model.entity.UserAuthorityEntity;
import com.vazgen.investment.model.entity.UserEntity;
import com.vazgen.investment.security.User;
import com.vazgen.investment.service.UserService;
import com.vazgen.investment.util.*;
import lombok.RequiredArgsConstructor;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(final UserCreate data) {
        User user = new UserDTO(userRepository.save(new UserEntity(
                data.getUsername(),
                passwordEncoder.encode(data.getPassword()),
                data.getAuthorities()
        )));

        return user;
    }

    @Override
    public Optional<User> findById(final long id) {
        return userRepository.findById(id).map(UserDTO::new);
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return userRepository.findByUsername(username).map(UserDTO::new);
    }

    @Override
    public User update(final long id, final UserUpdateRequest data) {
        UserEntity entity = userRepository.mustFindById(id);
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
}
