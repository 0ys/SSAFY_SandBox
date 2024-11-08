package com.ssafy.sandbox.oauth.dto;

import lombok.Data;

import java.util.Map;

@Data
public class KakaoUserResponseDto {
    private String id;
    private Map<String, String> properties;

    public String getId() {
        return id;
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
