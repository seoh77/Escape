package com.seoi.escapeplus.global.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.seoi.escapeplus.global.dto.ApiResponse;
import com.seoi.escapeplus.global.exception.BusinessException;
import com.seoi.escapeplus.global.exception.ErrorCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
		log.error("BusinessException: {}", e.getMessage());

		ErrorCode errorCode = e.getErrorCode();
		ApiResponse<Void> response = ApiResponse.fail(errorCode);

		return new ResponseEntity<>(response, errorCode.getHttpStatus());
	}

	// @Valid를 사용하여 데이터 유효성 검사를 할 때, 검증 실패시 MethodArgumentNotValidException 발생
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException e) {
		log.error("MethodArgumentNotValidException", e);

		String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		ApiResponse<Void> response = ApiResponse.of(HttpStatus.BAD_REQUEST, "INVALID_INPUT", message, null);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ApiResponse<Void>> handlerException(Exception e) {
		log.error("Unhandled Exception: ", e);

		ApiResponse<Void> response = ApiResponse.fail("서버 내부에 오류가 발생했습니다.");

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
