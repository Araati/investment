package com.vazgen.investment.security.token;

import com.vazgen.investment.security.principal.Principal;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class JwtAuthentication extends AbstractAuthenticationToken {

    private final String token;
    private final Principal payload;

    public JwtAuthentication(
            final String token,
            final Principal payload,
            final Collection<? extends GrantedAuthority> authorities){

        super(authorities);
        this.token = token;
        this.payload = payload;
        super.setAuthenticated(true);
    }

    @Override
    public String getCredentials() {
        return this.token;
    }

    @Override
    public Principal getPrincipal() {
        return this.payload;
    }
}
