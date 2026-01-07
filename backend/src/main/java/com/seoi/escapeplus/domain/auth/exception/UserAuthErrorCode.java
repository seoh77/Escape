package com.seoi.escapeplus.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.seoi.escapeplus.global.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAuthErrorCode implements ErrorCode {

	DUPLICATE_LOGINID("이미 사용 중인 아이디입니다.", HttpStatus.BAD_REQUEST);

	private final String message;
	private final HttpStatus httpStatus;
}
