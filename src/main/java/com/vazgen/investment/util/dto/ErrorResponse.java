package com.vazgen.investment.util.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vazgen.investment.util.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    @JsonProperty(value = "request_id", required = true)
    private UUID requestId;

    @JsonProperty(value = "error_code", required = true, defaultValue = "0")
    private ErrorCode errorCode;

    @JsonProperty(value = "error_reason", required = true, defaultValue = "ERROR")
    private String errorReason;

    @JsonProperty(value = "timestamp", required = true)
    private LocalDateTime timestamp;

    public ErrorResponse(
            final UUID requestId,
            final ErrorCode errorCode,
            final String reason) {

        this(requestId, errorCode, reason, LocalDateTime.now());
    }

    public ErrorResponse(
            final UUID requestId,
            final ErrorCode errorCode,
            final String errorReason,
            final LocalDateTime timestamp) {
        this.requestId = requestId;
        this.errorCode = errorCode;
        this.errorReason = errorReason;
        this.timestamp = timestamp;
    }
}
