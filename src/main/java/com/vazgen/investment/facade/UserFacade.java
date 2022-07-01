package com.vazgen.investment.facade;

import com.vazgen.investment.dto.UserDTO;
import com.vazgen.investment.security.User;
import com.vazgen.investment.service.UserService;
import com.vazgen.investment.util.UserCreation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService service;

    public UserDTO create(final UserCreation data) {
        final User user = service.create(data);
        return new UserDTO(user);
    }

}
