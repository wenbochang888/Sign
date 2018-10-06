package com.service;

import com.dao.SignDao;
import com.util.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Chang
 * @Date: 2018/10/3
 */
@Service
public class CookieCheckService {

	@Autowired
	HttpMethod httpMethod;

	@Autowired
	SignDao dao;

	public boolean check(String cookie, String username) {
		String url = "http://tieba.baidu.com/mo/";
		String content = httpMethod.get(url, cookie);
		if (content.contains("登录")) {
			return false;
		}
		// 将cookie插入到数据库中
		dao.insert(username, cookie);
		return true;
	}
}
