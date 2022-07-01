package com.vazgen.investment.security.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse implements AccessToken {
    @Getter
    @JsonProperty(value = "access_token", required = true)
    private String accessToken;

    @Getter
    @JsonProperty(value = "token_type", required = true)
    private String tokenType;

    @Getter
    @JsonProperty(value = "expires_at", required = true)
    private LocalDateTime expiresAt;
}