package com.seoi.escapeplus.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckDuplicateResponse {

	private boolean isAvailable;
	private String message;

}
