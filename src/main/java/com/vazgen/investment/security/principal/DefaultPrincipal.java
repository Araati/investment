package com.vazgen.investment.security.principal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vazgen.investment.security.permission.Authority;
import com.vazgen.investment.util.json.converter.LocalDateTimeToTimestampSerializer;
import com.vazgen.investment.util.json.converter.TimestampToLocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@AllArgsConstructor
public class DefaultPrincipal implements Principal {

    @JsonProperty(value = "sub", required = true)
    private String subject;

    @JsonProperty(value = "username", required = true)
    private String username;

    @JsonProperty(value = "person_id", required = true)
    private String personId;

    @JsonProperty(value = "expired_in", required = true)
    @JsonDeserialize(converter = TimestampToLocalDateTimeDeserializer.class)
    @JsonSerialize(converter = LocalDateTimeToTimestampSerializer.class)
    private LocalDateTime tokenExpirationDate;

    @JsonProperty(value = "permissions", required = true)
    private Set<Authority> authorities;

}
