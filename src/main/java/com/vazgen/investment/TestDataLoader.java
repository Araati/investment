package com.vazgen.investment;

import com.vazgen.investment.dao.UserRepository;
import com.vazgen.investment.dto.*;
import com.vazgen.investment.facade.ContributionFacade;
import com.vazgen.investment.facade.ProjectFacade;
import com.vazgen.investment.facade.TagFacade;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TestDataLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ProjectFacade projectFacade;
    private final TagFacade tagFacade;
    private final ContributionFacade contributionFacade;

    @EventListener(ApplicationReadyEvent.class)
    public void init()  {
        final Optional<User> admin = userRepository.findByUsername("admin").map(UserDTO::new);
        //was admin.isEmpty()
        if (!admin.isPresent()) {
            userRepository.save(new UserEntity(
                    0,
                    "admin",
                    passwordEncoder.encode("123123"),
                    listOf(Authority.ROLE_ADMIN).stream().map(UserAuthorityEntity::new).collect(Collectors.toList()),
                    true,
                    LocalDateTime.now(),
                    null
            ));
        }//1

        tagFacade.create(new TagCreateDTO("Архив", "end"));//2
        tagFacade.create(new TagCreateDTO("Завершен", "green"));//3
        ArrayList<Long> array = new ArrayList<>();
        array.add(2L);
        array.add(3L);
        projectFacade.create(new ProjectCreateDTO("RR",
                1,
                1000000000000L,
                "Oil trading",
                "Long article about Oil trading",
                "Short misleading preview",
                array));//4
        contributionFacade.create(new ContributionCreateDTO(
                4,
                "https://rivalregions.com/#slide/profile/192852686",
                1234567890L));//5
        contributionFacade.update(new ContributionUpdateDTO(null, null, null, true), 5);
        contributionFacade.create(new ContributionCreateDTO(
                4,
                "https://rivalregions.com/#slide/profile/2001311087",
                1L));//6
    }

    @SafeVarargs
    private static <T> List<T> listOf(T... elements) {
        List<T> list = new ArrayList<>();
        for (T e : elements)
            list.add(e);
        return Collections.unmodifiableList(list);
    }
}
