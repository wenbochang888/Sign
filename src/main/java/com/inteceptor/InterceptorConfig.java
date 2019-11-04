package com.inteceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: yizhen
 * @Date: 2018/12/24 15:45
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Bean
	public IPBlockInterceptor ipBlockInterceptor() {
		return new IPBlockInterceptor();
	}

	// 配置拦截规则
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(ipBlockInterceptor())
				.addPathPatterns("/**");
	}
}










