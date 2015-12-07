package com.assit.common;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;

public class ApiConfigSingleton {
	private static final String AppId = "wx81f4d10e779b3afe";
	private static final String AppSecret = "5b667f6d9834446162168fceb33f89e6";
	private static final ApiConfig config = new ApiConfig(AppId,AppSecret);
	public static ApiConfig getConfig() {
		return config;
	}
	
}
