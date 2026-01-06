package com.seoi.escapeplus.global.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

	String name();

	String getMessage();

	HttpStatus getHttpStatus();
}
