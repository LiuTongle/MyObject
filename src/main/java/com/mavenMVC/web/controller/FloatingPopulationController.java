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
import com.mavenMVC.entity.FloatingPopulation;
import com.mavenMVC.entity.MUserInfo;
import com.mavenMVC.service.IFloatingPopulationService;
import com.mavenMVC.util.RequestManager;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="/floatingPopulation", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
		RequestMethod.GET })
public class FloatingPopulationController {
	protected final Logger logger = Logger.getLogger(String.valueOf(FloatingPopulationController.class));
	
	@Autowired
	IFloatingPopulationService floatingPopulationService;
	
	@RequestMapping(value = "/upload", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ApiOperation(value = "上传流动人口采集")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
	@ResponseBody
	public String upload(@CurrentUser MUserInfo currentUser,
			@RequestBody(required = true) FloatingPopulation floatingPopulation){
		logger.info("Dealing with login Action...");
		RequestManager requestManager = new RequestManager();
		JSONObject result = new JSONObject();
		try {
			Assert.notNull(floatingPopulation, "录入信息不能为空");
			Assert.notNull(currentUser, "用户未登录");
			floatingPopulation.setUserId(currentUser.getUserId());
			floatingPopulationService.upload(floatingPopulation);
		} catch (Exception e) {
			requestManager.putErrorMessage(e.getMessage());
			e.printStackTrace();
		} finally {
			logger.info("Done login Action!");
			return requestManager.printJson(result).toString();
		}
	}
	@RequestMapping(value="/getInfo")
	@ApiOperation(value = "根据身份证查询流动人口信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
	@ResponseBody
	public String getInfoByIdNumber(
			@CurrentUser MUserInfo currentUser,
			@RequestParam(value="idNumber",required=true)String idNumber){
		logger.info("Dealing with login Action...");
		RequestManager requestManager = new RequestManager();
		JSONObject result = new JSONObject();
		try {
			Assert.notNull(idNumber, "没有填写身份证号");
			Assert.notNull(currentUser, "用户未登录");
			 FloatingPopulation floatingPopulation =  	floatingPopulationService.getByIdNumber(idNumber);
			 if (floatingPopulation!=null) {
				 result = JSONObject.fromObject(floatingPopulation);
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
