package com.vazgen.investment;

import com.vazgen.investment.dao.UserRepository;
import com.vazgen.investment.dto.UserDTO;
import com.vazgen.investment.model.entity.UserAuthorityEntity;
import com.vazgen.investment.model.entity.UserEntity;
import com.vazgen.investment.security.User;
import com.vazgen.investment.security.permission.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TestDataLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void init()  {
        final Optional<User> admin = userRepository.findByUsername("admin").map(UserDTO::new);
        if (admin.isEmpty()) {
            final UserEntity newUser = userRepository.save(new UserEntity(
                    0,
                    "admin",
                    passwordEncoder.encode("123123"),
                    List.of(Authority.ROLE_ADMIN).stream().map(UserAuthorityEntity::new).collect(Collectors.toList()),
                    true,
                    LocalDateTime.now(),
                    null
            ));
        }
    }
}
