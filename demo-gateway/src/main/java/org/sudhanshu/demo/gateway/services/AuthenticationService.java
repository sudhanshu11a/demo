package org.sudhanshu.demo.gateway.services;

import org.sudhanshu.demo.gateway.dto.TokenInfo;

public interface AuthenticationService {

    String authenticate(String user, String password, String application);

    boolean validateToken(String user, String token);

    TokenInfo getTokenInfo(String token);
}
