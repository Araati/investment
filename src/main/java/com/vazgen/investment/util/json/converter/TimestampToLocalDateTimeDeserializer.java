package com.vazgen.investment.util.json.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimestampToLocalDateTimeDeserializer extends StdConverter<Long, LocalDateTime> {
    @Override
    public LocalDateTime convert(final Long value) {
        return value == null ? null : LocalDateTime.ofInstant(Instant.ofEpochSecond(value), ZoneOffset.UTC);
    }
}
