package com.ai.order;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@RequestMapping("/order")
public class OrderController {
	@Value("${userNames}")
    private String userNames;
	
	@RequestMapping("/initUserData")
	public Map initUserData(){
		Map result = new HashMap();
		String[] userName = userNames.split("，");
		for (int i = 0; i < userName.length; i++) {
			userName[i] = userName[i]+"("+PinyinUtil.getPinYin(userName[i])+")";
		}
		//当天的预定数据
		LinkedHashMap<String, UserInfo> linkedHashMap = getTodayOrders();
		if (linkedHashMap == null) {
			result.put("orderNum", 0);
		}else {
			result.put("orderUsers", linkedHashMap.values());
			result.put("orderNum", linkedHashMap.size());
		}
		result.put("userList", Arrays.asList(userName));
		return result;
	}
	
	@RequestMapping("/addOrder")
	public Map addOrder(String userName){
		//当天的预定数据，如果暂无当天数据创建对应Map
		LinkedHashMap<String, UserInfo> linkedHashMap = getTodayOrders();
		if(linkedHashMap == null){
			linkedHashMap = new LinkedHashMap();
			Constant.BOOKING_STAFF.put(Constant.TODAY, linkedHashMap);
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map result = new HashMap();
		try {
			Calendar cal = Calendar.getInstance();
			int hour=cal.get(Calendar.HOUR_OF_DAY);
			//超出预定时间，不能预定
			if ( hour >= 17 ) {
				result.put("result", "failed");
				result.put("promptMsg", "超过订餐时间，预定失败！");
			}else {
				linkedHashMap.put(userName, new UserInfo(userName, simpleDateFormat.format(new Date())));
				result.put("result", "success");
				result.put("promptMsg", "预定成功。");
			}
			result.put("orderUsers", linkedHashMap.values());
			result.put("orderNum", linkedHashMap.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("/delOrder")
	public Map delOrder(String userName){
		Map result = new HashMap();
		try {
			Thread.sleep(500);
			//当天的预定数据
			LinkedHashMap<String, UserInfo> linkedHashMap = getTodayOrders();
			if(linkedHashMap != null){
				Calendar cal = Calendar.getInstance();
				int hour=cal.get(Calendar.HOUR_OF_DAY);
				//超出预定时间，不能取消
				if ( hour >= 17 ) {
					result.put("result", "failed");
					result.put("promptMsg", "超过订餐时间，取消失败！");
				}else {
					linkedHashMap.remove(userName);
					result.put("result", "success");
					result.put("promptMsg", "取消成功。");
				}
			}
			result.put("orderUsers", linkedHashMap.values());
			result.put("orderNum", linkedHashMap.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	
	public LinkedHashMap<String, UserInfo> getTodayOrders(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = simpleDateFormat.format(new Date());
		Constant.TODAY = today;
		//当天的预定数据
		LinkedHashMap<String, UserInfo> linkedHashMap = Constant.BOOKING_STAFF.get(today);
		return linkedHashMap;
	}
	

}
