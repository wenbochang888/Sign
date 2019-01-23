package com.service;

import com.dao.SignDao;
import com.google.common.collect.Lists;
import com.po.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Chang
 * @Date: 2018/10/4
 */
@Service
@Slf4j
public class UseCheck {

	@Autowired
	private SignDao dao;

	public List<Record> selectRecord(String username) {
		List<Record> res = Lists.newArrayList();
		try {
			 res = dao.selectRecord(username);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		res.sort((a, b) -> {
			String x = a.getDate().substring(0, 10);
			String y = b.getDate().substring(0, 10);
			if (x.equals(y)) return a.getDate().substring(11).compareTo(b.getDate().substring(11));
			return -x.compareTo(y);
		});
		return res;
	}

	public String checkUseName(String username) {
		String res = dao.checkUseName(username);
		return res;
	}
}








