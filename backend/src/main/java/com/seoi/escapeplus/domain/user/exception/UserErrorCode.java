package com.seoi.escapeplus.domain.user.exception;

import org.springframework.http.HttpStatus;

import com.seoi.escapeplus.global.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

	DUPLICATE_EMAIL("이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST),
	DUPLICATE_NICKNAME("이미 사용 중인 닉네임입니다.", HttpStatus.BAD_REQUEST);

	private final String message;
	private final HttpStatus httpStatus;
}
