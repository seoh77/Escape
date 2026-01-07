package com.seoi.escapeplus.domain.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seoi.escapeplus.domain.auth.entity.LoginType;
import com.seoi.escapeplus.domain.auth.entity.UserAuth;
import com.seoi.escapeplus.domain.auth.repository.UserAuthRepository;
import com.seoi.escapeplus.domain.user.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAuthService {

	private final UserAuthRepository userAuthRepository;

	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void save(User user, String loginId, String password) {
		UserAuth userAuth = UserAuth.builder()
			.user(user)
			.loginType(LoginType.LOCAL)
			.loginId(loginId)
			.password(passwordEncoder.encode(password))
			.build();

		userAuthRepository.save(userAuth);
	}

	public boolean checkDuplicateLoginId(String loginId) {
		return userAuthRepository.existsByLoginId(loginId);
	}
}
