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
import java.util.*;
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
        tagFacade.create(new TagCreateDTO("HOT", "orange"));//4

        ArrayList<Long> array = new ArrayList<>();
        array.add(2L);
        array.add(3L);
        projectFacade.create(new ProjectCreateDTO(
                "RR",
                1,
                1000000000000L,
                "Oil trading",
                "Long article about Oil trading",
                "Short misleading preview",
                array));//5

        contributionFacade.create(new ContributionCreateDTO(
                5,
                "https://rivalregions.com/#slide/profile/192852686",
                1234567890L));//6

        contributionFacade.update(new ContributionUpdateDTO(null, null, null, true), 6);
        contributionFacade.create(new ContributionCreateDTO(
                5,
                "https://rivalregions.com/#slide/profile/2001311087",
                1L));//7

        array.add(4L);
        projectFacade.create(new ProjectCreateDTO(
                "VK",
                0,
                100L,
                "Very very very very very very long long long Title for testing purposes, y'a know",
                "TEST TEST TEST TESTEESETSETESTSETSETSETEST",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi et leo eget augue efficitur sollicitudin. Donec iaculis sagittis varius. Phasellus odio odio, efficitur vel ornare a, ullamcorper sed ex.",
                array));//8

        contributionFacade.create(new ContributionCreateDTO(
                8,
                "https://rivalregions.com/#slide/profile/2001311087",
                80L
        ));//9
        contributionFacade.update(new ContributionUpdateDTO(null, null, null, true), 9);

        contributionFacade.create(new ContributionCreateDTO(
                8,
                "https://rivalregions.com/#slide/profile/2001311087",
                43L
        ));//10

        projectFacade.create(new ProjectCreateDTO(
                "АЕ",
                3,
                0L,
                "Test",
                "AOSRING",
                "Pelmeni.",
                null
        ));//11
    }

    @SafeVarargs
    private static <T> List<T> listOf(T... elements) {
        List<T> list = new ArrayList<>();
        for (T e : elements)
            list.add(e);
        return Collections.unmodifiableList(list);
    }
}
