package com.api.utilities;

import org.json.JSONObject;

import com.api.listeners.ExtentListeners;

public class TestUtil {

	public static boolean jsonHashKey(String json,String key) {

		JSONObject jsonobject=new JSONObject(json);

		ExtentListeners.testReport.get().info("Validating the presence of key : "+key);

		return jsonobject.has(key);
	}


	public static String getJsonKeyValue(String json,String key) {
		JSONObject jsonobject=new JSONObject(json);

		ExtentListeners.testReport.get().info("Validating the value of presence of key : "+key);

		return jsonobject.get(key).toString();
	}
}
