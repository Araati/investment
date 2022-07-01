package com.vazgen.investment.service.impl;

import com.vazgen.investment.dao.UserDetailsRepository;
import com.vazgen.investment.dto.UserDetailsDTO;
import com.vazgen.investment.model.entity.UserDetailsEntity;
import com.vazgen.investment.service.UserDetailsService;
import com.vazgen.investment.util.UserDetails;
import com.vazgen.investment.util.UserDetailsCreation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository detailsRepository;

    @Override
    public Optional<UserDetails> findByUserId(final long id) {
        return detailsRepository.findByUserId(id).map(UserDetailsDTO::new);
    }

    @Override
    public UserDetails create(final UserDetailsCreation dto) {
        return new UserDetailsDTO(detailsRepository.save(new UserDetailsEntity(
                dto.getUserId(),
                dto.getPersonId().orElse(null)
        )));
    }
}
