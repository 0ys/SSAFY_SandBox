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

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate1(
            @RequestBody Map<String, String> request,
            @RequestHeader(value = "Authorization", required = false) String accessToken,
            @CookieValue(value = "RefreshToken", required = false) String refreshToken) {


        String code = request.get("code");
        String accessTokenValue = (accessToken != null && accessToken.startsWith("Bearer "))
                ? accessToken.replace("Bearer ", "").trim()
                : null;

        return oAuthService.authenticate(code, accessTokenValue, refreshToken);
    }

    // 2. /authorization/auth 엔드포인트
    @PostMapping("/authorization/auth")
    public ResponseEntity<?> authenticate2(
            @RequestBody Map<String, String> request,
            @RequestHeader(value = "Authorization", required = false) String accessToken,
            @RequestHeader(value = "X-Refresh", required = false) String refreshToken) {


        String code = request.get("code");
        String accessTokenValue = (accessToken != null && accessToken.startsWith("Bearer "))
                ? accessToken.replace("Bearer ", "").trim()
                : null;
        String refreshTokenValue = (refreshToken != null && refreshToken.startsWith("Bearer "))
                ? refreshToken.replace("Bearer ", "").trim()
                : null;

        System.out.println("==========="+ accessTokenValue);
        System.out.println(refreshTokenValue);
        return oAuthService.authenticate(code, accessTokenValue, refreshTokenValue);
    }

    @PostMapping("/cookie/auth")
    public ResponseEntity<?> authenticate3(
            @RequestBody Map<String, String> request,
            @CookieValue(value = "AccessToken", required = false) String accessToken,
            @CookieValue(value = "RefreshToken", required = false) String refreshToken) {

        String code = request.get("code");
        String accessTokenValue = (accessToken != null && accessToken.startsWith("Bearer "))
                ? accessToken.replace("Bearer ", "").trim()
                : null;


        return oAuthService.authenticate(code, accessTokenValue, refreshToken);
    }



    @GetMapping("/member")
    public ResponseEntity<?> getMemberInfo1(
            @RequestHeader(value = "Authorization", required = false) String accessToken,
            @CookieValue(value = "RefreshToken", required = false) String refreshToken) {

        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", 401, "error", "UNAUTHORIZED: Missing Access Token"));
        }
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", 401, "error", "UNAUTHORIZED: Missing Refresh Token"));
        }

        String token = accessToken.replace("Bearer ", "").trim();

        System.out.println("AccessToken Header: " + token);
        System.out.println("RefreshToken Cookie: " + refreshToken);

        return oAuthService.getMemberInfo(token, refreshToken);
    }


    @GetMapping("/authorization/member")
    public ResponseEntity<?> getMemberInfo2(
            @RequestHeader(value = "Authorization", required = false) String accessToken,
            @RequestHeader(value = "X-Refresh", required = false) String refreshToken) {


        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", 401, "error", "UNAUTHORIZED: Missing Access Token"));
        }
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", 401, "error", "UNAUTHORIZED: Missing Refresh Token"));
        }

        String token = accessToken.replace("Bearer ", "").trim();

        System.out.println("AccessToken Header: " + token);
        System.out.println("RefreshToken Header: " + refreshToken);

        // oAuthService를 통해 토큰을 사용한 인증 및 정보 조회
        return oAuthService.getMemberInfo(token, refreshToken);
    }

    @GetMapping("/cookie/member")
    public ResponseEntity<?> getMemberInfo3(
            @CookieValue(value = "AccessToken", required = false) String accessToken,
            @CookieValue(value = "RefreshToken", required = false) String refreshToken) {

        System.out.println("AccessToken Cookie: " + accessToken);
        System.out.println("RefreshToken Cookie: " + refreshToken);

        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", 401, "error", "UNAUTHORIZED: Missing Access Token"));
        }

        return oAuthService.getMemberInfo(accessToken, refreshToken);
    }

    @GetMapping("/reissue")
    public ResponseEntity<?> reissueToken1(@CookieValue("RefreshToken") String refreshToken) {
        return oAuthService.reissueToken(refreshToken);
    }

    @GetMapping("/authorization/reissue")
    public ResponseEntity<?> reissueToken2(@RequestHeader(value = "X-Refresh", required = false) String refreshToken) {
        System.out.println(refreshToken);
        return oAuthService.reissueToken(refreshToken);
    }

    @GetMapping("/cookie/reissue")
    public ResponseEntity<?> reissueToken3(@CookieValue("RefreshToken") String refreshToken) {
        return oAuthService.reissueToken(refreshToken);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue("RefreshToken") String refreshToken) {
        return oAuthService.logout(refreshToken);
    }
}
