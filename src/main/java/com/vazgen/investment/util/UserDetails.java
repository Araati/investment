package com.vazgen.investment.util;

import com.vazgen.investment.model.IdReference;

import java.util.Optional;

public interface UserDetails extends IdReference {

    long getUserId();
    Optional<String> getPersonId();
}
