package com.seoi.escapeplus.global.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seoi.escapeplus.global.dto.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ResponseHandler implements ResponseBodyAdvice<Object> {

	private final ObjectMapper objectMapper;

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
		Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
		ServerHttpResponse response) {

		// body가 이미 ApiResponse 형태라면 그대로 반환 (더블매핑 문제 해결)
		if (body instanceof ApiResponse) {
			return body;
		}

		// body가 문자로 들어오는 경우 처리
		if (body instanceof String) {
			try {
				return objectMapper.writeValueAsString(ApiResponse.success((String)body));
			} catch (Exception e) {
				log.error("String response conversion error", e);
				return ApiResponse.fail("서버 내부에 오류가 발생했습니다.");
			}
		}

		return ApiResponse.success(body);
	}
}