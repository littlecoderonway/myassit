package com.assit.Servcie;

public interface DeathCounterService {
	/**
	 * 获取剩余时间秒
	 * @param userId
	 * @return
	 * @throws ParseException 
	 */
	long getLeftSeconds(String userId);
	
	boolean setBirthDay(String userId,String birthDay);
}
