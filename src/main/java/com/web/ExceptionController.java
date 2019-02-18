package com.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author: yizhen
 * @Date: 2018/12/7 16:27
 */
@ControllerAdvice
@Slf4j
public class ExceptionController {

	@ResponseBody
	@ExceptionHandler
	public String exceptionError(Throwable throwable, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		StringBuilder headers = new StringBuilder();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			headers.append(name + " = " + request.getHeader(name) + "\n");
		}
		// 其他异常 未知
		String msg = throwable.getMessage();
		log.warn("url = " + url);
		log.warn("headers = " + headers);
		log.error(msg);
		return msg;
	}
}
