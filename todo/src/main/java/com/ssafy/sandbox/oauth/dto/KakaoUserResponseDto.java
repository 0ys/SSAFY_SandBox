package com.ssafy.sandbox.oauth.dto;

import lombok.Data;

import java.util.Map;

@Data
public class KakaoUserResponseDto {
    private final String id;
    private final Map<String, String> properties;
}
