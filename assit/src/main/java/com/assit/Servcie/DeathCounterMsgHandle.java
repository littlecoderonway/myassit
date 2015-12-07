package com.assit.Servcie;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sd4324530.fastweixin.handle.MessageHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
public class DeathCounterMsgHandle implements MessageHandle<TextReqMsg> {

	private DeathCounterService counterService;

	private static final int minuteSeconds = 60;
	private static final int hourSeconds = 60*minuteSeconds;
	private static final int DaySeconds = 24*hourSeconds;
	private static final int MonthSeconds = 30*DaySeconds;
	private static final int yearSeconds = 12*MonthSeconds;
	@Override
	public BaseMsg handle(TextReqMsg message) {
		String content = message.getContent().substring(1);
		if("life".equalsIgnoreCase(content)){
			long seconds = counterService.getLeftSeconds(message.getFromUserName());
			if(seconds == -1){
				return new TextMsg("请先告诉我你的生日哦，");
			}else if(seconds == 0){
				return new TextMsg("你还没挂吗。。？(⊙ˍ⊙)(⊙ˍ⊙)");
			}else{
				long year = seconds/yearSeconds;
				long month = (seconds%yearSeconds)/MonthSeconds;
				long days = (seconds%MonthSeconds)/DaySeconds;
				long hours = (seconds%DaySeconds)/hourSeconds;
				long minute = (seconds%hourSeconds)/minuteSeconds;
				long second = (seconds%minuteSeconds);
				return new TextMsg("你还能活"+year+"年"+month+"个月"+days+"天"+hours+"小时"+minute+"分钟"+second+"秒");
			}
		}else{
			if (StringUtils.isAlphanumeric(content)&&content.length()==8) {
				counterService.setBirthDay(message.getFromUserName(), content);
				return new TextMsg("好的我已经记下你的生日了");
			}else{
				return new TextMsg("格式不对哦，生日格式如#19891022");
			}
		}
	}

	@Override
	public boolean beforeHandle(TextReqMsg message) {
		if(message.getContent().startsWith("#")){
			return true;
		}
		return false;
	}

	public DeathCounterService getCounterService() {
		return counterService;
	}

	public void setCounterService(DeathCounterService counterService) {
		this.counterService = counterService;
	}
}
