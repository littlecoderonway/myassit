package com.assit.common;

import java.io.File;

import com.github.sd4324530.fastweixin.api.MaterialAPI;
import com.github.sd4324530.fastweixin.api.response.UploadMaterialResponse;

public class TestUploadFile {
	public static void main(String []args){
		MaterialAPI materialAPI = new MaterialAPI(ApiConfigSingleton.getConfig());
		File file = new File("E:/pic/1.jpg");
		UploadMaterialResponse response = materialAPI.uploadMaterialFile(file);
		response.getMediaId();
	}
}
