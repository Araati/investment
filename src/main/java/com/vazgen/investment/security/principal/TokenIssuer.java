package com.vazgen.investment.security.principal;

public interface TokenIssuer {
    String sign(Principal principal);
}
