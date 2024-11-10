package com.ssafy.sandbox.oauth.service;

import com.ssafy.sandbox.oauth.dto.KakaoTokenDto;
import com.ssafy.sandbox.oauth.dto.KakaoUserResponseDto;
import com.ssafy.sandbox.oauth.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KakaoApiService {

    private final RestTemplate restTemplate;
    private final String clientId = "0da56a0700a56821782b91c49ce03b42";
    private final String redirectUri = "https://ssafysandbox.vercel.app/oauth/redirect";

    /**
     * 인증 코드를 사용하여 Kakao OAuth 토큰을 가져오는 메서드
     */
    public KakaoTokenDto getToken(String authorizationCode) {
        String url = "https://kauth.kakao.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", authorizationCode);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<KakaoTokenDto> response = restTemplate.postForEntity(url, entity, KakaoTokenDto.class);
        KakaoTokenDto tokenResponse = response.getBody();

        if (tokenResponse == null) {
            throw new RuntimeException("Failed to retrieve token: Empty response from Kakao API");
        }

        return tokenResponse;
    }

    /**
     * 액세스 토큰을 사용하여 사용자 정보를 가져오는 메서드
     */
    public User getUserInfo(String accessToken) {
        String url = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<KakaoUserResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, KakaoUserResponseDto.class);
        KakaoUserResponseDto userResponse = response.getBody();

        if (userResponse == null) {
            throw new RuntimeException("Failed to retrieve user info: Empty response from Kakao API");
        }

        return new User(userResponse.getId(), userResponse.getProperties().get("nickname"));
    }

    /**
     * 갱신 토큰을 사용하여 새로운 액세스 토큰을 발급받는 메서드
     */
    public KakaoTokenDto reissueToken(String refreshToken) {
        String url = "https://kauth.kakao.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "refresh_token");
        params.add("client_id", clientId);
        params.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<KakaoTokenDto> response = restTemplate.postForEntity(url, entity, KakaoTokenDto.class);
        KakaoTokenDto tokenResponse = response.getBody();

        if (tokenResponse == null) {
            throw new RuntimeException("Failed to reissue token: Empty response from Kakao API");
        }

        return tokenResponse;
    }
}
