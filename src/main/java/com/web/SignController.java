package com.web;

import com.po.Record;
import com.service.CookieCheckService;
import com.service.TiebaSign;
import com.service.UseCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Chang
 * @Date: 2018/10/3
 */
@CrossOrigin
@Controller
public class SignController {

	@Autowired
	private TiebaSign sign;
	@Autowired
	private CookieCheckService cookieCheckService;
	@Autowired
	private UseCheck check;

	@RequestMapping("/")
	public String index() {

		return "/login";
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String check(@RequestParam("cookie") String cookie,
	                    @RequestParam("username") String username,
	                    Map<String, Object> map) {
		if (check.checkUseName(username) != null) {
			map.put("msg", "用户名输入重复，请重新输入");
			return "/login";
		}
		boolean flag = cookieCheckService.check(cookie, username);
		if (flag) {
			map.put("msg", "cookie有效，登录成功");
			sign.setUsername(username);
			Thread t = new Thread(sign);
			t.start();
			return "redirect:/" + username;
		} else {
			map.put("msg", "请检查，cookie错误，登录失败");
			return "/login";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/sign")
	public String sign(@RequestParam("username") String username) {
		if (check.checkUseName(username) == null) {
			return "请检查用户名是否有误，请重新输入";
		}
		sign.setUsername(username);
		Thread t = new Thread(sign);
		t.start();
		return "签到成功，3秒后自动跳转到详情页";
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String selectRecord(@PathVariable(value = "username") String username,
	                           Map<String, Object> map) {
		List<Record> res = check.selectRecord(username);
		map.put("res", res);
		return "/info";
	}
}
