package com.ssafy.sandbox.oauth.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String nickname;

    public User(String id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

}
