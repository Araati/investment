package com.vazgen.investment.util.json.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocalDateTimeToTimestampSerializer extends StdConverter<LocalDateTime, Long> {
    @Override
    public Long convert(final LocalDateTime value) {
        return value == null ? null : value.toInstant(ZoneOffset.UTC).getEpochSecond();
    }
}
