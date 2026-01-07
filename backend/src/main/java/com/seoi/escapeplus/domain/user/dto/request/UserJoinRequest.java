package com.seoi.escapeplus.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJoinRequest {

	// @NotBlank(message = "로그인 타입은 필수 입력 값입니다.")
	// private LoginType loginType;

	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	private String loginId;

	private String password;

	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String name;

	@NotBlank(message = "생일은 필수 입력 값입니다.")
	private String birthday;

	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	private String email;

	@NotBlank(message = "연락처는 필수 입력 값입니다.")
	private String phoneNumber;

	@NotBlank(message = "연락처는 필수 입력 값입니다.")
	private String nickname;

	private String profileImg;
}