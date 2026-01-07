package com.seoi.escapeplus;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaAuditing
public class EscapeplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscapeplusApplication.class, args);
	}

	// KST로 타임존 설정
	@PostConstruct
	public void setTime() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
}
