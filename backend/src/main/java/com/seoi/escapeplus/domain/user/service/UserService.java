package com.seoi.escapeplus.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seoi.escapeplus.domain.auth.entity.UserAuth;
import com.seoi.escapeplus.domain.auth.repository.UserAuthRepository;
import com.seoi.escapeplus.domain.user.dto.UserJoinRequestDto;
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

	private final DateTimeUtil dateTimeUtil;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void join(UserJoinRequestDto requestDto) {
		// 이메일 중복체크
		checkDuplicateEmail(requestDto.getEmail());

		// 닉네임 중복체크
		checkDuplicateNickname(requestDto.getNickname());

		// User 엔티티 생성 및 저장
		User user = User.builder()
			.name(requestDto.getName())
			.birthday(dateTimeUtil.replaceStringWithLocalDate(requestDto.getBirthday()))
			.email(requestDto.getEmail())
			.phoneNumber(requestDto.getPhoneNumber())
			.nickname(requestDto.getNickname())
			.profileImg(requestDto.getProfileImg())
			.build();

		User savedUser = userRepository.save(user);

		// UserAuth 엔티티 생성 및 저장
		UserAuth userAuth = UserAuth.builder()
			.user(savedUser)
			.loginType(requestDto.getLoginType())
			.loginId(requestDto.getLoginId())
			.password(passwordEncoder.encode(requestDto.getPassword()))
			.build();

		userAuthRepository.save(userAuth);
	}

	private void checkDuplicateNickname(String nickname) {
		if (userRepository.existsByNickname(nickname)) {
			throw new BusinessException(UserErrorCode.Duplicate_EMAIL);
		}
	}

	private void checkDuplicateEmail(String email) {
		if (userRepository.existsByEmail(email)) {
			throw new BusinessException(UserErrorCode.Duplicate_Nickname);
		}
	}
}
