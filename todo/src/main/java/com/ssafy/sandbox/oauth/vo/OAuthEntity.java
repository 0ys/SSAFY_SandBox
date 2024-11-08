package com.ssafy.sandbox.oauth.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class OAuthEntity {
    @Id
    private String userId;

    private String accessToken;
    private String refreshToken;

    public OAuthEntity(String userId, String accessToken, String refreshToken) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void updateAccessToken(String newAccessToken) {
        this.accessToken = newAccessToken;
    }
}
