package com.ssafy.sandbox.oauth.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserInfoResponseDto {
    private String nickname;

    public UserInfoResponseDto(String nickname) {
        this.nickname = nickname;
    }
}
