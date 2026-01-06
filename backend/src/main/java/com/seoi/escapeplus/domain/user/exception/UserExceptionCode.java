package com.seoi.escapeplus.domain.user.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserExceptionCode {

	Duplicate_EMAIL("이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST),
	Duplicate_Nickname("이미 사용 중인 닉네임입니다.", HttpStatus.BAD_REQUEST);

	private final String message;
	private final HttpStatus httpStatus;
}
