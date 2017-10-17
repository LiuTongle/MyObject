//package com.mavenMVC.web.controller;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.mavenMVC.authorization.annotation.CurrentUser;
//import com.mavenMVC.entity.BuyerUser;
//import com.mavenMVC.entity.Collect;
//import com.mavenMVC.entity.Commodity;
//import com.mavenMVC.entity.Compete;
//import com.mavenMVC.entity.FeedBack;
//import com.mavenMVC.entity.SellerUser;
//import com.mavenMVC.service.ICommodityService;
//import com.mavenMVC.service.ICompeteService;
//import com.mavenMVC.service.IFeedBackService;
//import com.mavenMVC.util.RequestManager;
//import com.wordnik.swagger.annotations.ApiImplicitParam;
//import com.wordnik.swagger.annotations.ApiImplicitParams;
//import com.wordnik.swagger.annotations.ApiOperation;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//@Controller
//@RequestMapping("/feedback")
//public class FeedBackController {
//	protected final Logger logger = Logger.getLogger(String.valueOf(FeedBackController.class));
//
//	@Autowired
//	IFeedBackService feedBackService;
//	
//	@RequestMapping(value = "/getCompetes", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "上传意见反馈")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String getCompetes(@CurrentUser Object currentUser,
//			@RequestParam(value = "content") String content) {
//		RequestManager requestManager = new RequestManager();
//		JSONArray result = new JSONArray();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			Assert.notNull(content, "意见反馈内容不能为空");
//			FeedBack feedBack = new FeedBack();
//			long userid = 0;
//			int userType = 0;
//			if (currentUser instanceof BuyerUser) {
//				userType = 0;
//				userid = ((BuyerUser)currentUser).getUserId();
//			}else if(currentUser instanceof SellerUser){
//				userType = 1;
//				userid = ((SellerUser)currentUser).getUserId();
//			}
//			feedBack.setUserType(userType);
//			feedBack.setUserId(userid);
//			feedBack.setContent(content);
//			feedBackService.saveFeedBack(feedBack);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//}
