package com.assit.Servcie.impl;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assit.Servcie.DeathCounterService;
import com.assit.dao.RedisDao;

@Service
public class DeathCounterServiceImpl implements DeathCounterService {
	
	@Autowired
	private RedisDao dao;
	private static final int LIFE = 70;
	private static final Logger log = LoggerFactory.getLogger("DeathCounterServiceImpl");
	@Override
	public long getLeftSeconds(String userId) {
		String birthDay = dao.getBirthday(userId);
		Date date = null;
		try {
			date = DateUtils.parseDate(birthDay, "yyyyMMdd");
		} catch (ParseException e) {
			log.debug(e.getMessage(),e);
			return -1;
		}
		Date death = DateUtils.addYears(date, LIFE);
		long seconds = ((death.getTime()-new Date().getTime())/1000);
		if(seconds<0){
			return 0;
		}
		return seconds;
	}

	@Override
	public boolean setBirthDay(String userId, String birthDay) {
		return dao.setBirthday(userId, birthDay);
	}

}
