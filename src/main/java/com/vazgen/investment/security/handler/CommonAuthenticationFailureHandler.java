package com.vazgen.investment.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vazgen.investment.util.ErrorCode;
import com.vazgen.investment.util.RequestIdHolder;
import com.vazgen.investment.util.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class CommonAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;
    private final RequestIdHolder requestIdHolder;

    @Override
    public void onAuthenticationFailure(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse,
            final AuthenticationException e) throws IOException, ServletException {

        if(e != null){

            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());

            PrintWriter writer = httpServletResponse.getWriter();
            writer.print(objectMapper.writeValueAsString(
                    new ErrorResponse(
                            requestIdHolder.get().orElse(null),
                            ErrorCode.ACCESS_DENIED,
                            e.getMessage()
                    )
            ));
            writer.flush();
        }
    }
}