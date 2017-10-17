package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mavenMVC.util.MD5;

public class Test1 {
	public static void main(String[] args) {
//		String token="a51d8cba44986c276abf4cbaf90d212f";
//		String time = "1472539815082";
//		System.out.println(MD5.GetMD5Code(token + "qianYongCloudHospital" + time));
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d =sdf.parse("2016-09-18 09:00:00");
			long moing = d.getTime();
			d =sdf.parse("2016-09-18 18:00:00");
			long after = d.getTime();
			long random = (long)(moing+(Math.random()*(after-moing+1)));
			System.out.println(random);
			Date dddd = new Date(random);
			System.out.println(sdf.format(dddd));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	public void a(){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date d =sdf.parse("2016-09-18 09:00:00");
			long moing = d.getTime();
			d =sdf.parse("2016-09-18 18:00:00");
			long after = d.getTime();
			System.out.println();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
