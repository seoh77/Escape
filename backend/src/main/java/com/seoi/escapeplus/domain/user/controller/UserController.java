package com.seoi.escapeplus.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seoi.escapeplus.domain.user.dto.request.UserJoinRequest;
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

	@PostMapping("/join")
	@ResponseStatus(HttpStatus.CREATED)
	public String join(@Valid @RequestBody UserJoinRequest requestDto) {
		userService.join(requestDto);
		return "회원가입이 완료되었습니다.";
	}
}
