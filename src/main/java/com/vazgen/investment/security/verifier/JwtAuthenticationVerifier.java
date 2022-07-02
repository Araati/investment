package com.vazgen.investment.security.verifier;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jwt.SignedJWT;
import com.vazgen.investment.security.permission.Authority;
import com.vazgen.investment.security.principal.DefaultPrincipal;
import com.vazgen.investment.security.principal.Principal;
import org.springframework.security.authentication.AuthenticationServiceException;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtAuthenticationVerifier  {

    private final JWSVerifier verifier;

    public JwtAuthenticationVerifier(final String secret) {
        try {
            this.verifier = new MACVerifier(secret);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public Principal verify(final String token) {
        SignedJWT jwt = parse(token);
        if (!isValid(jwt)) {
            throw new AuthenticationServiceException("Invalid JWT");
        }
        try {
            return parse(jwt);
        } catch (Exception e) {
            throw new AuthenticationServiceException("Error parsing jwt");
        }
    }

    private SignedJWT parse(final String token) {
        try {
            return SignedJWT.parse(token);
        } catch (ParseException e) {
            throw new AuthenticationServiceException("Unable to parse JWT token", e);
        }
    }

    private DefaultPrincipal parse(final SignedJWT jwt) {
        final Map<String, Object> objectMap = jwt.getPayload().toJSONObject();
        String subject = (String) objectMap.get("sub");
        String username = (String) objectMap.get("username");
        long timestamp = (long) objectMap.get("expires_in");
        LocalDateTime expiresIn = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.UTC).withNano(0);
        final JSONObject jsonObject = (JSONObject) objectMap.get("permissions");
        final JSONArray array = (JSONArray) jsonObject.get("permissions");
        final Set<Authority> authorities = array.stream()
                .map(x -> Authority.fromValue(((long) x)))
                .collect(Collectors.toSet());
        return new DefaultPrincipal(subject, username, expiresIn, authorities);
    }

    private boolean isValid(final SignedJWT jwt) {
        try {
            return jwt.verify(verifier);
        } catch (JOSEException e) {
            throw new AuthenticationServiceException("An error was occurs on token verification", e);
        }
    }
}
