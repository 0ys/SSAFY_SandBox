package com.ssafy.sandbox.oauth.service;

import com.ssafy.sandbox.oauth.dto.KakaoTokenDto;
import com.ssafy.sandbox.oauth.dto.KakaoTokenResponseDto;
import com.ssafy.sandbox.oauth.dto.KakaoUserResponseDto;
import com.ssafy.sandbox.oauth.vo.User;
import lombok.NoArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.client.RestTemplate;

@Service
@NoArgsConstructor
public class KakaoApi {

    private RestTemplate restTemplate = new RestTemplate();
    private final String clientId = "0da56a0700a56821782b91c49ce03b42";
    private final String redirectUri = "https://ssafysandbox.vercel.app/oauth/redirect";

    public KakaoApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


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

        ResponseEntity<KakaoTokenResponseDto> response = restTemplate.postForEntity(url, entity, KakaoTokenResponseDto.class);
        KakaoTokenResponseDto tokenResponse = response.getBody();

        return new KakaoTokenDto(tokenResponse.getAccessToken(), tokenResponse.getRefreshToken());
    }

    public User getUserInfo(@CookieValue(value = "accessToken", required = false) String accessToken) {
        String url = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<KakaoUserResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, KakaoUserResponseDto.class);
        KakaoUserResponseDto userResponse = response.getBody();

        return new User(userResponse.getId(), userResponse.getProperties().get("nickname"));
    }

    public KakaoTokenDto reissueToken(String refreshToken) {
        String url = "https://kauth.kakao.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "refresh_token");
        params.add("client_id", clientId);
        params.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<KakaoTokenResponseDto> response = restTemplate.postForEntity(url, entity, KakaoTokenResponseDto.class);
        KakaoTokenResponseDto tokenResponse = response.getBody();

        if (tokenResponse == null) {
            throw new RuntimeException("Failed to reissue token: Empty response from Kakao API");
        }

        return new KakaoTokenDto(tokenResponse.getAccessToken(), refreshToken);
    }
}

