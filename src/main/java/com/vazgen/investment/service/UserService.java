package com.vazgen.investment.service;

import com.vazgen.investment.dao.UserRepository;
import com.vazgen.investment.dto.UserCreateDTO;
import com.vazgen.investment.dto.UserDTO;
import com.vazgen.investment.exception.ResourceNotFoundException;
import com.vazgen.investment.model.entity.UserEntity;
import com.vazgen.investment.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(final UserCreateDTO data) {
        User user = new UserDTO(userRepository.save(new UserEntity(
                data.getUsername(),
                passwordEncoder.encode(data.getPassword()),
                data.getAuthorities()
        )));

        return user;
    }

    public Optional<User> findById(final long id) {
        return userRepository.findById(id).map(UserDTO::new);
    }

    public Optional<User> findByUsername(final String username) {
        return userRepository.findByUsername(username).map(UserDTO::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username).map(UserDTO::new).orElseThrow(() -> new ResourceNotFoundException("User", username));
    }
}
