package com.assit.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.assit.dao.RedisDao;

@Repository
public class RedisDaoImpl implements RedisDao {
	
	private static final String ASSIT_REDIS_KEY_PREFIX = "assit_";
	private static final String BIRTHDAY_PREFIX = ASSIT_REDIS_KEY_PREFIX+"birth_";
	@Autowired
	private StringRedisTemplate template;
	
	
	@Override
	public boolean setBirthday(String userId, String birthday) {
		template.opsForValue().set(BIRTHDAY_PREFIX+userId, birthday);
		return true;
	}

	@Override
	public String getBirthday(String userId) {
		return template.opsForValue().get(BIRTHDAY_PREFIX+userId);
	}

}
