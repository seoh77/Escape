package com.seoi.escapeplus.domain.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seoi.escapeplus.domain.auth.exception.UserAuthErrorCode;
import com.seoi.escapeplus.domain.auth.service.UserAuthService;
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
@Transactional(readOnly = true)
public class UserService {

	private final UserRepository userRepository;

	private final UserAuthService userAuthService;

	@Transactional
	public void join(UserJoinRequest request) {
		validateDuplicateJoinInfo(request);

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
		userAuthService.save(savedUser, request.getLoginId(), request.getPassword());
	}

	/**
	 * 아이디, 이메일, 닉네임 중복 확인
	 * @param request
	 */
	private void validateDuplicateJoinInfo(UserJoinRequest request) {
		// 아이디 중복체크
		if (userAuthService.checkDuplicateLoginId(request.getLoginId())) {
			throw new BusinessException(UserAuthErrorCode.DUPLICATE_LOGINID);
		}

		// 이메일 중복체크
		if (checkDuplicateEmail(request.getEmail())) {
			throw new BusinessException(UserErrorCode.DUPLICATE_EMAIL);
		}

		// 닉네임 중복체크
		if (checkDuplicateNickname(request.getNickname())) {
			throw new BusinessException(UserErrorCode.DUPLICATE_NICKNAME);
		}
	}

	/**
	 * 닉네임 중복 확인
	 * @param nickname
	 * @return
	 */
	public boolean checkDuplicateNickname(String nickname) {
		return userRepository.existsByNickname(nickname);
	}

	/**
	 * 이메일 중복 확인
	 * @param email
	 * @return
	 */
	public boolean checkDuplicateEmail(String email) {
		return userRepository.existsByEmail(email);
	}
}
