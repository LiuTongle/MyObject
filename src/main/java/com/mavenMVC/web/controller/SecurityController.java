//package com.mavenMVC.web.controller;
//
//import java.util.ArrayList;
//import java.util.List;
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
//import com.mavenMVC.entity.Security;
//import com.mavenMVC.entity.SellerUser;
//import com.mavenMVC.service.IBuyerUserService;
//import com.mavenMVC.service.ISecurityService;
//import com.mavenMVC.service.ISellerUserService;
//import com.mavenMVC.util.RequestManager;
//import com.wordnik.swagger.annotations.ApiImplicitParam;
//import com.wordnik.swagger.annotations.ApiImplicitParams;
//import com.wordnik.swagger.annotations.ApiOperation;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//@Controller
//@RequestMapping("/security")
//public class SecurityController {
//	@Autowired
//	private ISecurityService securityService;
//
//	@Autowired
//	private ISellerUserService sellerUserService;
//
//	@Autowired
//	private IBuyerUserService buyerUserService;
//
//	protected final Logger logger = Logger.getLogger(String.valueOf(SecurityController.class));
//
//	@RequestMapping(value = "/saveSecurity", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "上传密保")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String saveSecurity(@CurrentUser Object currentUser,
//			@RequestParam(value = "securityProblem", required = false) String securityProblem,
//			@RequestParam(value = "securityResult", required = false) String securityResult) {
//		logger.info("Dealing with login Action...");
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			Assert.notNull(currentUser, "未登录系统");
//			Assert.notNull(securityProblem, "问题不能为空");
//			Assert.notNull(securityResult, "答案不能为空");
//			Security security = new Security();
//			if (currentUser instanceof BuyerUser) {
//				security.setUserType(0);
//				security.setUserId(((BuyerUser) currentUser).getUserId());
//			} else if (currentUser instanceof SellerUser) {
//				security.setUserType(1);
//				security.setUserId(((SellerUser) currentUser).getUserId());
//			} else {
//				throw new Exception("当前用户类型错误");
//			}
//			security.setSecurityProblem(securityProblem);
//			security.setSecurityResult(securityResult);
//			securityService.saveSecurity(security);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//			result = requestManager.printJson(null);
//		} finally {
//			logger.info("Done login Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/updateSecurity", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "修改密保")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String updateSecurity(@CurrentUser Object currentUser,
//			@RequestParam(value = "securityId", required = false) Long securityId,
//			@RequestParam(value = "securityProblem", required = false) String securityProblem,
//			@RequestParam(value = "securityResult", required = false) String securityResult) {
//		logger.info("Dealing with login Action...");
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			Assert.notNull(currentUser, "未登录系统");
//			Assert.notNull(securityProblem, "问题不能为空");
//			Assert.notNull(securityResult, "答案不能为空");
//			Security security = new Security();
//			if (currentUser instanceof BuyerUser) {
//				security.setUserType(0);
//				security.setUserId(((BuyerUser) currentUser).getUserId());
//			} else if (currentUser instanceof SellerUser) {
//				security.setUserType(1);
//				security.setUserId(((SellerUser) currentUser).getUserId());
//			} else {
//				throw new Exception("当前用户类型错误");
//			}
//			security.setSecurityId(securityId);
//			security.setSecurityProblem(securityProblem);
//			security.setSecurityResult(securityResult);
//			securityService.updateSecurityByUser(security);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//			result = requestManager.printJson(null);
//		} finally {
//			logger.info("Done login Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/getSecurity", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "获取用户所有密保")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String getSecuritys(@RequestParam(value = "cellphone", required = false) String cellphone,
//			@RequestParam(value = "userType", required = false) Integer userType) {
//		logger.info("Dealing with login Action...");
//		RequestManager requestManager = new RequestManager();
//		JSONArray result = new JSONArray();
//		try {
//			Assert.notNull(userType, "用户类型不能为空");
//			Assert.notNull(cellphone, "手机号不能为空");
//			Long userId;
//			if (userType == 0) {
//				// 买家
//				userType = 0;
//				userId = buyerUserService.getUserByCellphone(cellphone).getUserId();
//			} else if (userType == 1) {
//				// 卖家
//				userType = 1;
//				userId = sellerUserService.getUserByCellphone(cellphone).getUserId();
//			} else {
//				throw new Exception("当前用户类型错误");
//			}
//			List<Security> securityListByUser2 = securityService.getSecurityListByUser(userType, userId);
//			if (securityListByUser2 == null || securityListByUser2.size() == 0) {
//				requestManager.putErrorMessage("您还没有上传密保");
//			} else {
//				List<Security> securityListByUser = new ArrayList<Security>();
//				securityListByUser.add(securityListByUser2.get(0));
//				result = JSONArray.fromObject(securityListByUser);
//			}
//
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//			e.printStackTrace();
//		} finally {
//			logger.info("Done login Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//}
