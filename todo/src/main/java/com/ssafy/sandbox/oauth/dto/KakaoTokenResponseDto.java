package com.ssafy.sandbox.oauth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class KakaoTokenResponseDto {
    private String access_token;
    private String refresh_token;


    public String getAccessToken() {
        return access_token;
    }

    public String getRefreshToken() {
        return refresh_token;
    }
}
