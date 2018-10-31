package com.service;

import com.dao.SignDao;
import com.po.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Chang
 * @Date: 2018/10/4
 */
@Service
public class UseCheck {

	@Autowired
	SignDao dao;

	public List<Record> selectRecord(String username) {

		List<Record> res = dao.selectRecord(username);
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








