package com.assit.Servcie.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.assit.Servcie.TulingService;

@Service
public class TulingServiceImpl implements TulingService {
	
	private static final Logger log = LoggerFactory.getLogger("TulingServiceImpl");
	/*图灵机器人key*/
	final static String APIKEY = "44a9f8cd307255e6e0374c68c47dc4b8";
	
	@Override
	public String autoAnswer(String request) {
		try {
			return recho(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ohoo,服务好像有点故障了..";
	}
	
	private String recho(String content) throws IOException{
		String INFO = URLEncoder.encode(content, "utf-8");
		String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY
				+ "&info=" + INFO;
		URL getUrl = new URL(getURL);
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.connect();

		// 取得输入流，并使用Reader读取
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		// 断开连接
		connection.disconnect();
		log.info("机器人回应消息:"+sb);
		String ret = sb.toString();
		JSONObject json = JSON.parseObject(ret);
		String code = json.getString("code");
		//文本类型
		if(Integer.parseInt(code)==100000){
			ret = json.getString("text");
		}
		log.info("回复消息是:"+ret);
		return ret;
	}

}
