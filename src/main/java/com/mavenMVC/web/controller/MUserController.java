package com.mavenMVC.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mavenMVC.entity.BuyerUser;
import com.mavenMVC.entity.MUserInfo;
import com.mavenMVC.service.IMUserService;
import com.mavenMVC.util.RequestManager;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/MUserController")
public class MUserController {
	
	protected final Logger logger = Logger.getLogger(String.valueOf(MUserController.class));
	
	@Autowired
	IMUserService MUserService;
	
	@RequestMapping(value = "/login", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
			RequestMethod.GET })
	// @Authorization
	@ApiOperation(value = "用户登录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
	public @ResponseBody String login(@RequestParam(value = "cellphone", required = false) String cellphone,
			@RequestParam(value = "password", required = false) String password) {
		logger.info("Dealing with login Action...");
		RequestManager requestManager = new RequestManager();
		JSONObject result = new JSONObject();
		try {
			Assert.notNull(cellphone, "手机号不能为空");
			Assert.notNull(password, "密码不能为空");
			MUserInfo userInfo = MUserService.login(cellphone, password);
			Assert.notNull(userInfo, "该手机号还未注册或密码有误");
			result = JSONObject.fromObject(userInfo);
		} catch (Exception e) {
			requestManager.putErrorMessage(e.getMessage());
			e.printStackTrace();
		} finally {
			logger.info("Done login Action!");
			return requestManager.printJson(result).toString();
		}
	}
	
}
