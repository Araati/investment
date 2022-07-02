package com.vazgen.investment.controller.v1;

import com.vazgen.investment.dao.UserDetailsRepository;
import com.vazgen.investment.dao.UserRepository;
import com.vazgen.investment.dto.ContributionCreateDTO;
import com.vazgen.investment.dto.ContributionUpdateDTO;
import com.vazgen.investment.dto.UserCreateDTO;
import com.vazgen.investment.dto.UserDTO;
import com.vazgen.investment.facade.ContributionFacade;
import com.vazgen.investment.facade.UserFacade;
import com.vazgen.investment.model.Contribution;
import com.vazgen.investment.model.entity.UserAuthorityEntity;
import com.vazgen.investment.model.entity.UserDetailsEntity;
import com.vazgen.investment.model.entity.UserEntity;
import com.vazgen.investment.security.User;
import com.vazgen.investment.security.permission.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/v1/contribution")
@RequiredArgsConstructor
public class ContributionController {

    private final ContributionFacade contributionFacade;
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Contribution create(@Valid @RequestBody final ContributionCreateDTO request)   {
        return contributionFacade.create(request);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Contribution update(@RequestBody final ContributionUpdateDTO request, @PathVariable final long id)  {
        return contributionFacade.update(request, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable final long id) {
        contributionFacade.delete(id);
    }

    @GetMapping("/{id}")
    public Contribution findById(@PathVariable final long id)   {
        return contributionFacade.findById(id);
    }

    @GetMapping
    public List<Contribution> findAll() {
        return contributionFacade.findAll();
    }

    @GetMapping("/trigger")
    public void trigger()   {
        final String adminUsername = "TestAdmin";
        final Optional<User> admin = userRepository.findByUsername(adminUsername).map(UserDTO::new);
        if (admin.isEmpty()) {
            userFacade.create(new UserCreateDTO(adminUsername, "123123", List.of(Authority.ROLE_ADMIN)));
        }

        final String userUsername = "TestUser";
        final Optional<User> user = userRepository.findByUsername(userUsername).map(UserDTO::new);
        if(user.isEmpty()) {
            userFacade.create(new UserCreateDTO(userUsername, "123123", List.of(Authority.ROLE_USER)));
        }

        final Optional<User> engine = userRepository.findByUsername("engine").map(UserDTO::new);
        if (engine.isEmpty()) {
            final UserEntity newUser = userRepository.save(new UserEntity(
                    0,
                    "TestSystem",
                    passwordEncoder.encode("123123"),
                    List.of(Authority.ROLE_SYSTEM).stream().map(UserAuthorityEntity::new).collect(Collectors.toList()),
                    true,
                    LocalDateTime.now(),
                    null
            ));
            userDetailsRepository.save(new UserDetailsEntity(newUser.getId()));
        }
    }

}
