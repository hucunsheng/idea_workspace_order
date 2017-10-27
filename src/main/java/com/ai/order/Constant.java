package com.ai.order;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Constant {
	//记录已预订人员
	public static Map<String, LinkedHashMap<String, UserInfo>> BOOKING_STAFF = new HashMap<String, LinkedHashMap<String, UserInfo>>();
	
	//当天时间
	public static String TODAY = "";

}
