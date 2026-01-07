package com.seoi.escapeplus.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seoi.escapeplus.domain.auth.service.UserAuthService;
import com.seoi.escapeplus.domain.user.dto.request.UserJoinRequest;
import com.seoi.escapeplus.domain.user.dto.response.CheckDuplicateResponse;
import com.seoi.escapeplus.domain.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserAuthService userAuthService;

	@PostMapping("/join")
	@ResponseStatus(HttpStatus.CREATED)
	public String join(@Valid @RequestBody UserJoinRequest requestDto) {
		userService.join(requestDto);
		return "회원가입이 완료되었습니다.";
	}

	@GetMapping("/check/id")
	public CheckDuplicateResponse checkId(@RequestParam String id) {
		if (userAuthService.checkDuplicateLoginId(id)) {
			return new CheckDuplicateResponse(false, "이미 사용 중인 아이디입니다.");
		}

		return new CheckDuplicateResponse(true, "사용 가능한 아이디입니다.");
	}

	@GetMapping("/check/email")
	public CheckDuplicateResponse checkEmail(@RequestParam String email) {
		if (userService.checkDuplicateEmail(email)) {
			return new CheckDuplicateResponse(false, "이미 사용 중인 이메일입니다.");
		}

		return new CheckDuplicateResponse(true, "사용 가능한 이메일입니다.");
	}

	@GetMapping("/check/nickname")
	public CheckDuplicateResponse checkNickname(@RequestParam String nickname) {
		if (userService.checkDuplicateNickname(nickname)) {
			return new CheckDuplicateResponse(false, "이미 사용 중인 닉네임입니다");
		}

		return new CheckDuplicateResponse(true, "사용 가능한 닉네임입니다.");
	}
}
