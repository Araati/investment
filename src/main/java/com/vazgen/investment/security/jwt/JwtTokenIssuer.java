package com.vazgen.investment.security.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.vazgen.investment.security.permission.Authority;
import com.vazgen.investment.security.principal.Principal;
import com.vazgen.investment.security.principal.TokenIssuer;

import java.time.ZoneOffset;
import java.util.stream.Collectors;

public class JwtTokenIssuer implements TokenIssuer {

    private final JWSSigner signer;
    private final JWSAlgorithm algo;

    public JwtTokenIssuer(final String secret) {
        try {
            this.signer = new MACSigner(secret);
            this.algo = JWSAlgorithm.HS256;
        } catch (JOSEException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String sign(final Principal principal) {
        JWTClaimsSet claimsSet = constructClaimSet(principal);
        SignedJWT preparedJwt = new SignedJWT(new JWSHeader(algo), claimsSet);
        try {
            preparedJwt.sign(signer);
            return preparedJwt.serialize();
        } catch (JOSEException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private JWTClaimsSet constructClaimSet(Principal payload) {
        JSONObject permissionsJSON = new JSONObject();
        JSONArray array = new JSONArray();
        array.addAll(payload.getAuthorities()
                .stream()
                .map(Authority::toValue)
                .collect(Collectors.toSet()));
        permissionsJSON.put("permissions", array);

        return new JWTClaimsSet.Builder()
                .subject(payload.getSubject())
                .claim("person_id", payload.getPersonId())
                .claim("username", payload.getUsername())
                .claim("expires_in", payload.getTokenExpirationDate().toEpochSecond(ZoneOffset.UTC))
                .claim("permissions", permissionsJSON)
                .build();
    }
}
