package com.vazgen.investment.security.response;

import java.time.LocalDateTime;

public interface AccessToken {
    String getAccessToken();

    String getTokenType();

    LocalDateTime getExpiresAt();
}
