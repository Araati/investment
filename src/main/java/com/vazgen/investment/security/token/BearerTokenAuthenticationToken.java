package com.vazgen.investment.security.token;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Getter
public class BearerTokenAuthenticationToken extends AbstractAuthenticationToken {

    private final String token;

    public BearerTokenAuthenticationToken(final String token) {
        this(token, Collections.emptyList());
    }

    public BearerTokenAuthenticationToken(final String token, final Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }
}
