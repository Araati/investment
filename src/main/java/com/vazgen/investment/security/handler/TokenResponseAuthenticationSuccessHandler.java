package com.vazgen.investment.security.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vazgen.investment.security.principal.Principal;
import com.vazgen.investment.security.response.TokenResponse;
import com.vazgen.investment.security.token.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class TokenResponseAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse,
            final Authentication authentication) throws IOException, ServletException {

        JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        PrintWriter writer = httpServletResponse.getWriter();
        writer.print(write(jwtAuthentication));
        writer.flush();
    }

    private String write(final JwtAuthentication authentication) throws JsonProcessingException {
        final Principal principal = authentication.getPrincipal();
        return objectMapper.writeValueAsString(new TokenResponse(authentication.getToken(), "bearer", principal.getTokenExpirationDate()));
    }
}
