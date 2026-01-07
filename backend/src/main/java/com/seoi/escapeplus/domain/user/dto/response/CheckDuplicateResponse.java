package com.seoi.escapeplus.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckDuplicateResponse {

	private boolean isAvailable;
	private String message;

	public static CheckDuplicateResponse available(String message) {
		return new CheckDuplicateResponse(true, message);
	}

	public static CheckDuplicateResponse unavailable(String message) {
		return new CheckDuplicateResponse(false, message);
	}
}
