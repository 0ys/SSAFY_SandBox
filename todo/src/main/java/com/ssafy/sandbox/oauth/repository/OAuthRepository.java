package com.ssafy.sandbox.oauth.repository;

import com.ssafy.sandbox.oauth.vo.OAuthEntity;
import com.ssafy.sandbox.oauth.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuthRepository extends JpaRepository<OAuthEntity, String> {
    Optional<OAuthEntity> findByAccessToken(String accessToken);
    Optional<OAuthEntity> findByRefreshToken(String refreshToken);

    Optional<OAuthEntity> findByUser(User user);
}
