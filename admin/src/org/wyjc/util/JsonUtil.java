package org.wyjc.util;

import java.util.List;
import java.util.Map;

import org.wyjc.gap.PageData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	// private JsonObjectPool jsonObject = JsonObjectPool.getInstance();
	// private Map<String, String> jsonMap = new HashMap<String, String>();
	private static String resultStr;

	public static String returnAjax(String statusCode, String message, String navTabId, String callbackType,
			String forwardUrl) {
		JSONObject json = new JSONObject();
		json.accumulate("statusCode", statusCode);
		json.accumulate("message", message);
		json.accumulate("navTabId", navTabId);
		json.accumulate("rel", "");
		json.accumulate("callbackType", callbackType);
		json.accumulate("forwardUrl", forwardUrl);
		resultStr = json.toString();
		return resultStr;
	}

	public static String returnDefaultAjax(Map map) {
		resultStr = JSONObject.fromObject(map).toString();
		return resultStr;
	}

	public static String objToJson(PageData data) {
		JSONObject jsonObject = JSONObject.fromObject(data);
		return jsonObject.toString();
	}

	public static String arrayToJson(List list) {
		String json = JSONArray.fromObject(list).toString();
		return json;
	}
	public static String arrayToJson(String[] list) {
		String json = JSONArray.fromObject(list).toString();
		return json;
	}
}
