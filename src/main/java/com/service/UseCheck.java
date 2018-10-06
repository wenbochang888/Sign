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
		return dao.selectRecord(username);
	}

	public String checkUseName(String username) {
		String res = dao.checkUseName(username);
		return res;
	}
}








