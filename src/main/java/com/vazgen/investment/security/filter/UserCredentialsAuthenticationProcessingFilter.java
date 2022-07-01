package com.vazgen.investment.security.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UserCredentialsAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public UserCredentialsAuthenticationProcessingFilter(final ObjectMapper objectMapper){
        this("/auth/signin", objectMapper);
    }

    public UserCredentialsAuthenticationProcessingFilter(final String url, final ObjectMapper objectMapper) {
        super(url);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        if(!httpServletRequest.getMethod().equals("POST")){
            throw new AuthenticationServiceException("Authentication method not supported: "+httpServletRequest.getMethod());
        }
        UsernamePasswordAuthenticationToken authenticationToken = constructAuthentication(httpServletRequest);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    private UsernamePasswordAuthenticationToken constructAuthentication(final HttpServletRequest httpServletRequest)
            throws IOException {

        String body = httpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        final Map<String, String> map = objectMapper.readValue(body, new TypeReference<HashMap<String, String>>() {});
        return new UsernamePasswordAuthenticationToken(map.get("username"), map.get("password"));
    }
}
