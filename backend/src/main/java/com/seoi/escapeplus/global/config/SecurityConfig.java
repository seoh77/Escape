package com.seoi.escapeplus.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			// 1. CSRF 보호 비활성화
			.csrf(AbstractHttpConfigurer::disable)
			// 2. HTTP Basic 로그인 비활성화
			.httpBasic(AbstractHttpConfigurer::disable)
			// 3. Form 로그인 비활성화 (JWT 사용)
			.formLogin(AbstractHttpConfigurer::disable)
			// 4. 세션 관리 정책 설정 (STATELESS: 서버에 세션을 만들지 않음)
			.sessionManagement(session ->
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			// 5. 요청에 대한 권한 관리
			.authorizeHttpRequests(auth -> auth
				// 인증 없이 접근 가능한 공개 API
				.requestMatchers("/api/**").permitAll()
				.anyRequest().authenticated()
			);

		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
