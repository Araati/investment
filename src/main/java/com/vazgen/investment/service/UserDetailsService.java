package com.vazgen.investment.service;

import com.vazgen.investment.util.UserDetails;
import com.vazgen.investment.util.UserDetailsCreation;

import java.util.Optional;

public interface UserDetailsService {

    Optional<UserDetails> findByUserId(long id);

    UserDetails create(UserDetailsCreation dto);
}
