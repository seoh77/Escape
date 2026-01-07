package com.seoi.escapeplus.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seoi.escapeplus.domain.auth.entity.LoginType;
import com.seoi.escapeplus.domain.auth.entity.UserAuth;
import com.seoi.escapeplus.domain.auth.repository.UserAuthRepository;
import com.seoi.escapeplus.domain.user.dto.request.UserJoinRequest;
import com.seoi.escapeplus.domain.user.entity.User;
import com.seoi.escapeplus.domain.user.exception.UserErrorCode;
import com.seoi.escapeplus.domain.user.repository.UserRepository;
import com.seoi.escapeplus.global.exception.BusinessException;
import com.seoi.escapeplus.global.util.DateTimeUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserAuthRepository userAuthRepository;

	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void join(UserJoinRequest request) {
		// 이메일 중복체크
		checkDuplicateEmail(request.getEmail());

		// 닉네임 중복체크
		checkDuplicateNickname(request.getNickname());

		// User 엔티티 생성 및 저장
		User user = User.builder()
			.name(request.getName())
			.birthday(DateTimeUtil.replaceStringWithLocalDate(request.getBirthday()))
			.email(request.getEmail())
			.phoneNumber(request.getPhoneNumber())
			.nickname(request.getNickname())
			.profileImg(request.getProfileImg())
			.build();

		User savedUser = userRepository.save(user);

		// UserAuth 엔티티 생성 및 저장
		UserAuth userAuth = UserAuth.builder()
			.user(savedUser)
			.loginType(LoginType.LOCAL)
			.loginId(request.getLoginId())
			.password(passwordEncoder.encode(request.getPassword()))
			.build();

		userAuthRepository.save(userAuth);
	}

	private void checkDuplicateNickname(String nickname) {
		if (userRepository.existsByNickname(nickname)) {
			throw new BusinessException(UserErrorCode.DUPLICATE_EMAIL);
		}
	}

	private void checkDuplicateEmail(String email) {
		if (userRepository.existsByEmail(email)) {
			throw new BusinessException(UserErrorCode.DUPLICATE_NICKNAME);
		}
	}
}
