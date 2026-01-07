package com.seoi.escapeplus.domain.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seoi.escapeplus.domain.auth.entity.UserAuth;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

	boolean existsByLoginId(String loginId);
}
