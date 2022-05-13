package com.example.practice.order.controller;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiResponse<T> {

	private int statusCode;
	private T data;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime serverDatetime;

	public ApiResponse(int statusCode, T data) {
		this.statusCode = statusCode;
		this.data = data;
		this.serverDatetime = LocalDateTime.now();
	}

	public static <T> ApiResponse<T> ok(T data) {
		return new ApiResponse<>(200, data);
	}

	public static <T> ApiResponse<T> fail(int statusCode, T data) {
		return new ApiResponse<>(statusCode, data);
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public LocalDateTime getServerDatetime() {
		return serverDatetime;
	}

	public void setServerDatetime(LocalDateTime serverDatetime) {
		this.serverDatetime = serverDatetime;
	}
}
