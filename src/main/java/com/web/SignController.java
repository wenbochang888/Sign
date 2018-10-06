package com.web;

import com.po.Record;
import com.service.CookieCheckService;
import com.service.TiebaSign;
import com.service.UseCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Chang
 * @Date: 2018/10/3
 */
@RestController
public class SignController {

	@Autowired
	TiebaSign sign;
	@Autowired
	CookieCheckService cookieCheckService;
	@Autowired
	UseCheck check;

	@RequestMapping("/")
	public String index() {
		return "Hello World";
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String check(@RequestParam("cookie") String cookie,
	                    @RequestParam("username")String username) {
		if (check.checkUseName(username) != null) {
			return "用户名输入重复，请重新输入";
		}
		boolean flag = cookieCheckService.check(cookie, username);
		if (flag) {
			return "cookie有效，登录成功";
		} else {
			return "请检查，cookie错误，登录失败";
		}
	}

	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public String sign(@RequestParam("username")String username) {
		if (check.checkUseName(username) == null) {
			return "请检查用户名是否有误，请重新输入";
		}
		sign.setUsername(username);
		Thread t = new Thread(sign);
		t.start();
		return "签到成功，3秒后自动跳转到详情页";
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public List<Record> selectRecord(@PathVariable(value = "username") String username) {
		List<Record> res = check.selectRecord(username);
		return res;
	}
}
