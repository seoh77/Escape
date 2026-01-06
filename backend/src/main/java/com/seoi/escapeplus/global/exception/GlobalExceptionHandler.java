package com.seoi.escapeplus.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
		log.error("BusinessException: {}", e.getMessage());

		ErrorCode errorCode = e.getErrorCode();
		ErrorResponse response = ErrorResponse.builder()
			.code(errorCode.name())
			.message(errorCode.getMessage())
			.status(errorCode.getHttpStatus().value())
			.build();

		return new ResponseEntity<>(response, errorCode.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handlerException(Exception e) {
		log.error("Unhandled Exception: ", e);

		ErrorResponse response = ErrorResponse.builder()
			.code("INTERNAL_SERVER_ERROR")
			.message("서버 내부에 오류가 발생했습니다.")
			.status(500)
			.build();

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
