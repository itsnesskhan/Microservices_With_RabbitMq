package com.all.security.library.payloads;

import java.util.HashMap;
import java.util.Map;

public class SharedData {

	static Map<String, String> dataMap = new HashMap<>();
	
	public static  void setData(String key, String value) {
		dataMap.put(key, value);
	}
	
	public static Map<String, String> getSharedDataMap() {
		return dataMap;
	}
	
}