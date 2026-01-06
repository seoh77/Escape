package com.seoi.escapeplus.domain.auth.entity;

import java.time.LocalDateTime;

import com.seoi.escapeplus.domain.user.entity.User;
import com.seoi.escapeplus.global.entity.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_auth")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuth extends BaseTimeEntity {

	@Id
	@Column(name = "auth_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private LoginType loginType;

	@Column(length = 255, nullable = false)
	private String loginId;

	@Column(length = 255)
	private String password;

	private LocalDateTime pwUpdateTime;

	/**
	 * 자체 로그인
	 */
	@Builder
	private UserAuth(User user, LoginType loginType, String loginId, String password) {
		this.user = user;
		this.loginType = loginType;
		this.loginId = loginId;
		this.password = password;
		this.pwUpdateTime = LocalDateTime.now();
	}
}
