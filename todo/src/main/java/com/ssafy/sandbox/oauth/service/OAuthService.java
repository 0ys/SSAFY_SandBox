package com.ssafy.sandbox.oauth.service;

import org.springframework.http.ResponseEntity;

public interface OAuthService {
    ResponseEntity<?> reissueToken(String refreshToken);
    ResponseEntity<?> logout(String refreshToken);
    ResponseEntity<?> getMemberInfo(String accessToken, String refreshToken);
    ResponseEntity<?> authenticate(String code, String accessToken, String refreshToken);
}
