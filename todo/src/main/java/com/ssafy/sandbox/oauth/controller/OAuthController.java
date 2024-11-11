package com.ssafy.sandbox.oauth.controller;

import com.ssafy.sandbox.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;

    /**
     * 인증 코드와 액세스/갱신 토큰을 통해 사용자 인증을 처리하는 메서드
     */
    @PostMapping({"/auth", "/authorization/auth", "/cookie/auth"})
    public ResponseEntity<?> authenticate(
            @RequestBody Map<String, String> request,
            @RequestHeader(value = "Authorization", required = false) String accessTokenHeader,
            @RequestHeader(value = "X-Refresh", required = false) String refreshTokenHeader,
            @CookieValue(value = "AccessToken", required = false) String accessTokenCookie,
            @CookieValue(value = "RefreshToken", required = false) String refreshTokenCookie) {

        // 헤더와 쿠키에서 유효한 액세스 토큰과 갱신 토큰을 선택
        String accessToken = accessTokenHeader != null ? extractBearerToken(accessTokenHeader) : extractBearerToken(accessTokenCookie);
        String refreshToken = refreshTokenHeader != null ? refreshTokenHeader : refreshTokenCookie;

        // 요청 본문에서 인증 코드를 가져옴
        String code = request.get("code");

        return oAuthService.authenticate(code, accessToken, refreshToken);
    }

    @GetMapping({"/member", "/authorization/member", "/cookie/member"})
    public ResponseEntity<?> getMemberInfo(
            @RequestHeader(value = "Authorization", required = false) String accessTokenHeader,
            @RequestHeader(value = "X-Refresh", required = false) String refreshTokenHeader,
            @CookieValue(value = "AccessToken", required = false) String accessTokenCookie,
            @CookieValue(value = "RefreshToken", required = false) String refreshTokenCookie) {

        // 헤더와 쿠키에서 유효한 액세스 토큰을 확인
        String accessToken = accessTokenHeader != null ? extractBearerToken(accessTokenHeader) : accessTokenCookie;
        String refreshToken = refreshTokenHeader != null ? refreshTokenHeader : refreshTokenCookie;

        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", 401, "error", "UNAUTHORIZED: Missing Access Token"));
        }
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", 401, "error", "UNAUTHORIZED: Missing Refresh Token"));
        }

        return oAuthService.getMemberInfo(accessToken, refreshToken);
    }

    /**
     * 갱신 토큰을 사용하여 새로운 액세스 토큰을 발급받는 메서드
     */
    @GetMapping({"/reissue", "/authorization/reissue", "/cookie/reissue"})
    public ResponseEntity<?> reissueToken(
            @RequestHeader(value = "X-Refresh", required = false) String refreshTokenHeader,
            @CookieValue(value = "RefreshToken", required = false) String refreshTokenCookie) {

        // 헤더 또는 쿠키에서 RefreshToken을 가져옴
        String refreshToken = refreshTokenHeader != null ? refreshTokenHeader : refreshTokenCookie;

        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", 401, "error", "UNAUTHORIZED: Missing Refresh Token"));
        }

        return oAuthService.reissueToken(refreshToken);
    }

    /**
     * 쿠키에 저장된 갱신 토큰을 사용해 로그아웃 처리하는 메서드
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue("RefreshToken") String refreshToken) {
        return oAuthService.logout(refreshToken);
    }

    /**
     * Bearer 토큰의 "Bearer " 접두사를 제거하고 토큰 값을 반환하는 메서드
     */
    private String extractBearerToken(String token) {
        return (token != null && token.startsWith("Bearer "))
                ? token.replace("Bearer ", "").trim()
                : null;
    }
}
