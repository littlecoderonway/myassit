package com.assit.dao;

public interface RedisDao {
	boolean setBirthday(String userId,String birthday);
	String getBirthday(String userId);
}
