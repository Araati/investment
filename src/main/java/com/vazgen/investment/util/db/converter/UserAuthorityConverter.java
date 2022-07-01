package com.vazgen.investment.util.db.converter;

import com.vazgen.investment.security.permission.Authority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserAuthorityConverter implements AttributeConverter<Authority, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final Authority authority) {
        return authority == null ? null : authority.getId();
    }

    @Override
    public Authority convertToEntityAttribute(final Integer integer) {
        return integer == null ? null : Authority.fromValue(integer);
    }

    // TODO: 28.06.2022 Сократить если требуется 

}
