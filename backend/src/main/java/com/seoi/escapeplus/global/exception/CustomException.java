package com.seoi.escapeplus.global.exception;

import org.springframework.http.HttpStatus;

import com.seoi.escapeplus.domain.user.exception.UserExceptionCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {

	private final String errorMessage;
	private final HttpStatus httpStatus;

	public CustomException(UserExceptionCode userExceptionCode) {
		this.errorMessage = userExceptionCode.getMessage();
		this.httpStatus = userExceptionCode.getHttpStatus();
	}
}
