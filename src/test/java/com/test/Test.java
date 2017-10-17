package com.test;

import java.util.Calendar;

import net.sf.json.JSONObject;

public class Test {
	public static void main(
			String[] args) {/*
							 * JSONObject jo = new JSONObject(); JSONObject data
							 * = new JSONObject(); JSONObject where = new
							 * JSONObject(); data.put("alert", "消息内容");
							 * data.put("title", "显示在通知栏的标题");
							 * where.put("installationId",
							 * "9b3f1d6c1d8aa64d42ad3c6a1baf1a6dfb452d527cb205e8acb26fc5a5a07371"
							 * ); jo.put("where",where); jo.put("data", data);
							 * jo.put("prod", Code.IOS_PROD);
							 * HttpRequestUtil.sendPost(Code.MESSAGE_URL,jo.
							 * toString(),0);
							 */

		String channelId = String.valueOf(Calendar.getInstance().getTimeInMillis());
		JSONObject jo = new JSONObject();
		JSONObject data = new JSONObject();
		data.put("alert", channelId);
		data.put("doctorName", "doctorName");
		data.put("doctorId", "doctorId");
		data.put("patientId", "patientId");
		data.put("type", "video");
		JSONObject where = new JSONObject();
		where.put("deviceToken", "9B3F1D6C1D8AA64D42AD3C6A1BAF1A6DFB452D527CB205E8ACB26FC5A5A07371");
		jo.put("where", where);
		jo.put("data", data);
		jo.put("prod", "dev");
		System.out.println(jo.toString());
		// HttpRequestUtil.sendPost(Code.MESSAGE_URL,jo.toString(),0);

	}
}
