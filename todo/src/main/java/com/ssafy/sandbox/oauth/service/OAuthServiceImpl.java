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
    private final KakaoApi kakaoApi;
    private final UserRepository userRepository;


    @Override
    public ResponseEntity<?> authenticate(String code, String accessToken, String refreshToken) {
        KakaoTokenDto kakaoToken = kakaoApi.getToken(code);

        User user = kakaoApi.getUserInfo(kakaoToken.getAccessToken());
        User existingUser = userRepository.findById(user.getId()).orElseGet(() -> userRepository.save(user));

        OAuthEntity oAuthEntity = repository.findByUser(existingUser)
                .orElse(new OAuthEntity(existingUser.getId(), kakaoToken.getAccessToken(), kakaoToken.getRefreshToken()));

        oAuthEntity.setAccessToken(kakaoToken.getAccessToken());
        oAuthEntity.setRefreshToken(kakaoToken.getRefreshToken());
        repository.save(oAuthEntity);

        return ResponseEntity.ok()
                .header("Set-Cookie", "AccessToken=" + kakaoToken.getAccessToken() + "; Path=/; HttpOnly; Secure; SameSite=None")
                .header("Set-Cookie", "RefreshToken=" + kakaoToken.getRefreshToken() + "; Path=/; HttpOnly; Secure; SameSite=None")
                .body(new TokenResponseDto(kakaoToken.getAccessToken(), kakaoToken.getRefreshToken()));
    }

    @Override
    public ResponseEntity<?> getMemberInfo(String accessToken, String refreshToken) {
        // 토큰 로그 출력
        System.out.println("Received AccessToken: " + accessToken);

        // Access Token을 통해 회원 정보 조회
        Optional<OAuthEntity> oAuthEntityOpt = repository.findByAccessToken(accessToken);
        if (oAuthEntityOpt.isEmpty()) {
            // 토큰이 존재하지 않는 경우
            System.out.println("AccessToken not found or invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", 401, "error", "ERR_NOT_FOUND_MEMBER"));
        }

        OAuthEntity oAuthEntity = oAuthEntityOpt.get();
        return ResponseEntity.ok().body(new UserInfoResponseDto(oAuthEntity.getUser().getNickname()));
    }



    @Override
    public ResponseEntity<?> reissueToken(String refreshToken) {
        OAuthEntity oAuthEntity = repository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("ERR_MISSING_REFRESH_TOKEN"));

        KakaoTokenDto newToken = kakaoApi.reissueToken(refreshToken);
        oAuthEntity.updateAccessToken(newToken.getAccessToken());
        repository.save(oAuthEntity);

        return ResponseEntity.ok().body(new TokenResponseDto(newToken.getAccessToken(), newToken.getRefreshToken()));
    }

    @Override
    public ResponseEntity<?> logout(String refreshToken) {
        OAuthEntity oAuthEntity = repository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("ERR_MISSING_REFRESH_TOKEN"));

        repository.delete(oAuthEntity);
        return ResponseEntity.ok().header("Set-Cookie", "refreshToken=; Max-Age=0; HttpOnly; Secure; SameSite=None").build();
    }
}
