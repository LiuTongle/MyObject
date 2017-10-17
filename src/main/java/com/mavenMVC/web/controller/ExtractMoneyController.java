//package com.mavenMVC.web.controller;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.mavenMVC.authorization.annotation.CurrentUser;
//import com.mavenMVC.entity.BuyerUser;
//import com.mavenMVC.entity.ExtractMoney;
//import com.mavenMVC.entity.SellerUser;
//import com.mavenMVC.service.IExtractMoneyService;
//import com.mavenMVC.util.RequestManager;
//import com.wordnik.swagger.annotations.ApiImplicitParam;
//import com.wordnik.swagger.annotations.ApiImplicitParams;
//import com.wordnik.swagger.annotations.ApiOperation;
//
//import net.sf.json.JSONObject;
//
//@Controller
//@RequestMapping("/extractMoney")
//public class ExtractMoneyController {
//
//	protected final Logger logger = Logger.getLogger(String.valueOf(ExtractMoneyController.class));	
//	
//	@Autowired
//	IExtractMoneyService extractMoneyService;
//	
//	@RequestMapping(value = "/extracy", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "申请提现")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String login(@CurrentUser Object currentUser,
//			@RequestParam(value = "wxId", required = false) String wxId,
//			@RequestParam(value = "money", required = false) Double money) {
//		logger.info("Dealing with login Action...");
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			Assert.notNull(wxId, "微信号不能为空");
//			Assert.notNull(money, "金额不能为空");
//			ExtractMoney extractMoney = new ExtractMoney();
//			if (currentUser instanceof SellerUser) {
//				extractMoney.setUserId(((SellerUser)currentUser).getUserId());
//				extractMoney.setWxId(wxId);
//				extractMoney.setMoney(money);
//				extractMoneyService.extractMoney(extractMoney);
//			}else{
//				requestManager.putErrorMessage("用户类型错误");
//			}
//			
////			result = JSONObject.fromObject(userEntity);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done login Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//	
//}
