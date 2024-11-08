package com.ssafy.sandbox.oauth.dto;

import lombok.Data;

@Data
public class KakaoTokenDto {
    private String accessToken;
    private String refreshToken;

    public KakaoTokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
