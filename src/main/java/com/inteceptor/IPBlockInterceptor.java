package com.inteceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: yizhen
 * @Date: 2018/12/28 12:11
 */
@Slf4j
public class IPBlockInterceptor implements HandlerInterceptor {

	private Object lock = new Object();

	/** 根据浏览器头进行限制 */
	private static final String USERAGENT = "User-Agent";
	private static final String CRAWLER = "crawler";
	private static final String wechatMessage = "MicroMessenger";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		synchronized (lock) {
			return checkAgent(request);
		}
	}

	private boolean checkAgent(HttpServletRequest request) {
		String header = request.getHeader(USERAGENT);
		if (StringUtils.isEmpty(header)) {
			return false;
		}
		if (header.contains(CRAWLER)) {
			log.error("请求头有问题，拦截 ==> User-Agent = {}", header);
			return false;
		}
		if (!header.contains(wechatMessage)) {
			log.error("请求头有问题，拦截 ==> User-Agent = {}", header);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
	}
}
