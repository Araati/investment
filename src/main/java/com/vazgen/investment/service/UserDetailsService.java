package com.vazgen.investment.service;

import com.vazgen.investment.util.UserDetails;
import com.vazgen.investment.util.UserDetailsCreate;

import java.util.Optional;

public interface UserDetailsService {

    Optional<UserDetails> findByUserId(long id);

    UserDetails create(UserDetailsCreate dto);
}
