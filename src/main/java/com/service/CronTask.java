package com.service;

import com.dao.SignDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: Chang
 * @Date: 2018/10/3
 */
@Service
public class CronTask  {

	@Autowired
	private SignDao dao;
	@Autowired
	private TiebaSign sign;

	@Scheduled(cron = "0 30 3 * * ?")
	public void cronTask() {
		List<String> users = dao.selectAllUse();
		ExecutorService service = Executors.newCachedThreadPool();
		for (String use : users) {
			sign.setUsername(use);
			service.submit(sign);
		}
	}
}





