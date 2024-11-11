package com.ssafy.sandbox.oauth.repository;

import com.ssafy.sandbox.oauth.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
