package com.vazgen.investment.util;

import org.slf4j.MDC;

import java.util.Optional;
import java.util.UUID;

public class RequestIdHolder {

    // TODO: 02.07.2022 Надо бы выпилить
    public Optional<UUID> get() {
        return Optional.ofNullable(MDC.get("request_id")).map(UUID::fromString);
    }

    public void save(final UUID id) {
        MDC.put("request_id", id.toString());
    }

    public UUID generate() {
        return UUID.randomUUID();
    }
}
