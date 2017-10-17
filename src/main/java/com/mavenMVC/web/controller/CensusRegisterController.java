package com.mavenMVC.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mavenMVC.authorization.annotation.CurrentUser;
import com.mavenMVC.entity.CensusRegister;
import com.mavenMVC.entity.MUserInfo;
import com.mavenMVC.service.ICensusRegisterService;
import com.mavenMVC.util.RequestManager;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/CensusRegister", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
		RequestMethod.GET })
public class CensusRegisterController {
	
	protected final Logger logger = Logger.getLogger(String.valueOf(CensusRegisterController.class));
	
	@Autowired
	ICensusRegisterService censusRegisterService;
	
	@RequestMapping(value = "/upload", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
			RequestMethod.GET })
	// @Authorization
	@ApiOperation(value = "上传户籍人口采集")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
	public @ResponseBody String login(@CurrentUser MUserInfo currentUser,
			@RequestBody(required = true) CensusRegister censusRegister) {
		logger.info("Dealing with login Action...");
		RequestManager requestManager = new RequestManager();
		JSONObject result = new JSONObject();
		try {
			Assert.notNull(censusRegister, "录入信息不能为空");
			Assert.notNull(currentUser, "用户未登录");
			censusRegister.setUserId(currentUser.getUserId());
			censusRegisterService.upload(censusRegister);
		} catch (Exception e) {
			requestManager.putErrorMessage(e.getMessage());
			e.printStackTrace();
		} finally {
			logger.info("Done login Action!");
			return requestManager.printJson(result).toString();
		}
	}
	@RequestMapping(value="/getInfo")
	@ResponseBody
	@ApiOperation(value = "获取人口信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
	public String getInfo(@CurrentUser MUserInfo currentUser,@RequestParam(value="idNumber",required=true)String idNumber){
		logger.info("Dealing with login Action...");
		RequestManager requestManager = new RequestManager();
		JSONObject result = new JSONObject();
		try {
			Assert.notNull(idNumber, "身份证号不能为空");
			Assert.notNull(currentUser, "用户未登录");
		CensusRegister censusRegister = 	censusRegisterService.getByIdNumber(idNumber);
		if (censusRegister!=null) {
			result  = 	JSONObject.fromObject(censusRegister);
		}
		} catch (Exception e) {
			requestManager.putErrorMessage(e.getMessage());
			e.printStackTrace();
		} finally {
			logger.info("Done login Action!");
			return requestManager.printJson(result).toString();
		}
	}
}
