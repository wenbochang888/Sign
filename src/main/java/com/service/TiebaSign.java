package com.service;

import com.dao.SignDao;
import com.util.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Chang
 * @Date: 2018/10/3
 */
@Service
@Slf4j
public class TiebaSign implements Runnable {

	@Autowired
	private SignDao dao;
	@Autowired
	private HttpMethod http;

	private Map<String, String> map = new HashMap<>();

	private static final String COOKIE = "cookie";

	String username = null;

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCookie(String username) {
		String cookie = null;
		if (StringUtils.isEmpty(map.get(COOKIE))) {
			cookie = dao.select(username);
			map.put(COOKIE, cookie);
		}
		cookie = map.get(COOKIE);
		return cookie;
	}

	public void execute(String username) {
		// 获取cookie
		String cookie = getCookie(username);
		// 获取首页html内容
		String content = http.get("http://tieba.baidu.com/mo/", cookie);
		log.info("content = {}", content);
		// 获取所有连接
		String links = getMore(content);
		log.info("links = {}", links);
		if (StringUtils.isEmpty(links)) {
			return;
		}
		links = "http://tieba.baidu.com" + links;
		// 获取所有贴吧html内容
		content = http.get(links, cookie);
		log.info("content = {}", content);
		List<String> likesLink = getLike(content);
		sign(likesLink, cookie, username);
		log.warn("==============所有贴吧签到完成==============");
	}

	private void sign(List<String> likesLink, String cookie, String username) {
		for (String link : likesLink) {
			String content = http.get(link, cookie);
			Document doc = Jsoup.parse(content);
			Elements titles = doc.getElementsByClass("bc");
			String title = titles.get(0).text();
			title = title.split("吧")[0];
			Elements links = doc.select("a[href]");
			boolean flag = true;
			for (Element ele : links) {
				String ss = ele.attr("href");
				ss = "http://tieba.baidu.com" + ss;
				if (ss.contains("sign")) {
					http.get(ss, cookie);
					// 插入到数据库中
					String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
					dao.insertRecord(username, title, date, "签到成功");
					log.info(username + "---" + title + "---" + "签到成功");
					flag = false;
					break;
				}
			}
			if (flag) {
				// 插入到数据库中
				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
				//dao.insertRecord(username, title, date, "已签到");
				log.warn(username + "---" + title + "---" + "已签到");
			}
		}
	}

	private List<String> getLike(String content) {
		Document doc = Jsoup.parse(content);
		Elements links = doc.select("a[href]");
		List<String> list = new ArrayList<>();
		for (Element link : links) {
			String res = link.attr("href");
			if (res.contains("kw")) {
				list.add("http://tieba.baidu.com/mo/q---3339B9B19C4A0F1EDC3A6232B76275FD%3AFG%3D1--1-3-0--2--wapp_1550479814077_11/" + res);
			}
		}
		return list;
	}

	private String getMore(String content) {
		Document doc = Jsoup.parse(content);
		Elements links = doc.select("a[href]");
		for (Element link : links) {
			String res = link.attr("href");
			if (res.endsWith("favorite")) {
				return res;
			}
		}
		return null;
	}

	@Override
	public void run() {
		execute(username);
	}
}








