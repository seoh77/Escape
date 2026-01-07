package com.seoi.escapeplus.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJoinRequest {

	// @NotBlank(message = "로그인 타입은 필수 입력 값입니다.")
	// private LoginType loginType;

	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	@Size(min = 5, max = 15, message = "아이디는 5자 이상 15자 이하로 입력해주세요.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영어와 숫자만 입력 가능합니다.")
	private String loginId;

	@Size(min = 5, max = 15, message = "비밀번호는 5자 이상 15자 이하로 입력해주세요.")
	@Pattern(regexp = "^[a-zA-Z0-9!@*~$%]*$", message = "비밀번호는 영어, 숫자, 특수문자(!, @, *, ~, $, %)만 입력 가능합니다.")
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