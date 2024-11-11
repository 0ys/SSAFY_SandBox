package com.ssafy.sandbox.oauth.dto;

import lombok.Data;

@Data
public class TokenResponseDto {
    private final String accessToken;
    private final String refreshToken;
}
