package com.seoi.escapeplus.domain.user.entity;

import java.time.LocalDate;

import com.seoi.escapeplus.global.entity.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 30, nullable = false)
	private String name;

	@Column(nullable = false)
	private LocalDate birthday;

	@Column(length = 100, nullable = false, unique = true)
	private String email;

	@Column(length = 30, nullable = false)
	private String phoneNumber;

	@Column(length = 30, nullable = false, unique = true)
	private String nickname;

	@Column(length = 255)
	private String profileImg;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserStatusType status;

	@Builder
	private User(String name, LocalDate birthday, String email, String phoneNumber, String nickname,
		String profileImg) {
		this.name = name;
		this.birthday = birthday;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.nickname = nickname;
		this.profileImg = profileImg;
		this.status = UserStatusType.ACTIVE;
	}
}
