package com.seoi.escapeplus.global.dto;

import org.springframework.http.HttpStatus;

import com.seoi.escapeplus.global.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

	private int status;
	private String code;
	private String message;
	private T data;

	// 성공 (반환 데이터 있음)
	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(HttpStatus.OK.value(), "SUCCESS", "요청에 성공하였습니다.", data);
	}

	// 성공 (반환 데이터 없음, 메세지만 반환)
	public static ApiResponse<String> success(String message) {
		return new ApiResponse<>(HttpStatus.OK.value(), "SUCCESS", message, null);
	}

	// 실패 (ErrorCode 반환 - BusinessException)
	public static ApiResponse<Void> fail(ErrorCode errorCode) {
		return new ApiResponse<>(errorCode.getHttpStatus().value(), errorCode.name(), errorCode.getMessage(), null);
	}

	// 실패 (단순 에러 메세지 - 시스템 에러)
	public static ApiResponse<Void> fail(String message) {
		return new ApiResponse<>(
			HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR", message, null
		);
	}

	// 반환 값 커스텀
	@Builder
	public static <T> ApiResponse<T> of(HttpStatus status, String code, String message, T data) {
		return new ApiResponse<>(status.value(), code, message, data);
	}
}