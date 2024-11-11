package com.ssafy.sandbox.oauth.service;

import com.ssafy.sandbox.oauth.dto.TokenResponseDto;
import com.ssafy.sandbox.oauth.dto.KakaoTokenDto;
import com.ssafy.sandbox.oauth.dto.UserInfoResponseDto;
import com.ssafy.sandbox.oauth.repository.OAuthRepository;
import com.ssafy.sandbox.oauth.repository.UserRepository;
import com.ssafy.sandbox.oauth.vo.OAuthEntity;
import com.ssafy.sandbox.oauth.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {

    private final OAuthRepository repository;
    private final KakaoApiService kakaoApi;
    private final UserRepository userRepository;

    /**
     * 사용자 인증 메서드
     */
    @Override
    public ResponseEntity<?> authenticate(String code, String accessToken, String refreshToken) {
        KakaoTokenDto kakaoToken = kakaoApi.getToken(code);
        User user = kakaoApi.getUserInfo(kakaoToken.getAccessToken());

        // 기존 사용자 검색 또는 새로 저장
        User existingUser = userRepository.findById(user.getId())
                .orElseGet(() -> userRepository.save(user));

        // OAuthEntity를 조회하고 없으면 새로 생성 후 저장
        OAuthEntity oAuthEntity = repository.findByUser(existingUser)
                .orElse(new OAuthEntity(existingUser.getId(), kakaoToken.getAccessToken(), kakaoToken.getRefreshToken()));
        oAuthEntity.updateTokens(kakaoToken.getAccessToken(), kakaoToken.getRefreshToken());
        repository.save(oAuthEntity);

        return buildTokenResponse(kakaoToken.getAccessToken(), kakaoToken.getRefreshToken());
    }

    /**
     * 회원 정보 조회 메서드
     */
    @Override
    public ResponseEntity<?> getMemberInfo(String accessToken, String refreshToken) {
        Optional<OAuthEntity> oAuthEntityOpt = repository.findByAccessToken(accessToken);

        if (oAuthEntityOpt.isEmpty()) {
            return unauthorizedResponse("ERR_NOT_FOUND_MEMBER");
        }

        OAuthEntity oAuthEntity = oAuthEntityOpt.get();
        return ResponseEntity.ok(new UserInfoResponseDto(oAuthEntity.getUser().getNickname()));
    }

    /**
     * 토큰 재발급 메서드
     */
    @Override
    public ResponseEntity<?> reissueToken(String refreshToken) {
        OAuthEntity oAuthEntity = repository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("ERR_MISSING_REFRESH_TOKEN"));

        KakaoTokenDto newToken = kakaoApi.reissueToken(refreshToken);
        oAuthEntity.updateAccessToken(newToken.getAccessToken());
        repository.save(oAuthEntity);

        return ResponseEntity.ok(new TokenResponseDto(newToken.getAccessToken(), newToken.getRefreshToken()));
    }

    /**
     * 로그아웃 메서드
     */
    @Override
    public ResponseEntity<?> logout(String refreshToken) {
        OAuthEntity oAuthEntity = repository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("ERR_MISSING_REFRESH_TOKEN"));

        repository.delete(oAuthEntity);
        return ResponseEntity.ok()
                .header("Set-Cookie", "refreshToken=; Max-Age=0; HttpOnly; Secure; SameSite=None")
                .build();
    }

    /**
     * 토큰 응답을 생성하는 헬퍼 메서드
     */
    private ResponseEntity<?> buildTokenResponse(String accessToken, String refreshToken) {
        return ResponseEntity.ok()
                .header("Set-Cookie", "AccessToken=" + accessToken + "; Path=/; HttpOnly; Secure; SameSite=None")
                .header("Set-Cookie", "RefreshToken=" + refreshToken + "; Path=/; HttpOnly; Secure; SameSite=None")
                .body(new TokenResponseDto(accessToken, refreshToken));
    }

    /**
     * 인증 실패 응답을 생성하는 헬퍼 메서드
     */
    private ResponseEntity<?> unauthorizedResponse(String errorMessage) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("status", 401, "error", errorMessage));
    }
}
