package com.vazgen.investment.model.entity;

import com.vazgen.investment.security.permission.Authority;
import com.vazgen.investment.util.db.converter.UserAuthorityConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class UserAuthorityEntity {

    @Column(name = "authority", nullable = false, updatable = false)
    @Convert(converter = UserAuthorityConverter.class)
    private Authority authority;

    public UserAuthorityEntity(final Authority authority) {
        this.authority = authority;
    }

}
