package com.vazgen.investment.security.principal;

public interface AuthenticationVerifier {

    Principal verify(String token);
}
