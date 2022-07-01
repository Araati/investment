package com.vazgen.investment.security.entrypoint;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException authException
    ) throws IOException {
        /*log.warn(
                "Unsuccessful authentication attempt - {}, {}. Exc. type: [{}], Message: {}",
                request.getRequestURI(),
                request.getMethod(),
                authException.getClass().getName(),
                authException.getMessage()
        )*/;

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        PrintWriter writer = response.getWriter();
        writer.print(authException.getMessage());
        writer.flush();
    }
}
