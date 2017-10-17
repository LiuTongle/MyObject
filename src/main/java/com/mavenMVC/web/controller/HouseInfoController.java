package com.mavenMVC.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mavenMVC.authorization.annotation.CurrentUser;
import com.mavenMVC.entity.HouseInfo;
import com.mavenMVC.entity.MUserInfo;
import com.mavenMVC.service.IHouseInfoService;
import com.mavenMVC.util.RequestManager;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/houseInfo",produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
			RequestMethod.GET})
public class HouseInfoController {
	@Autowired
	IHouseInfoService houseInfoService;
	
	protected final Logger logger = Logger.getLogger(String.valueOf(HouseInfoController.class));
	
	@RequestMapping(value = "/upload", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ApiOperation(value = "上传房屋出租采集")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
	@ResponseBody
	public String upload(@CurrentUser MUserInfo currentUser,
			@RequestBody(required = true) HouseInfo houseInfo){
		logger.info("Dealing with login Action...");
		RequestManager requestManager = new RequestManager();
		JSONObject result = new JSONObject();
		try {
			Assert.notNull(houseInfo, "录入信息不能为空");
			Assert.notNull(currentUser, "用户未登录");
			houseInfo.setUserId(currentUser.getUserId());
			houseInfoService.upload(houseInfo);
		} catch (Exception e) {
			requestManager.putErrorMessage(e.getMessage());
			e.printStackTrace();
		} finally {
			logger.info("Done login Action!");
			return requestManager.printJson(result).toString();
		}
	}
}