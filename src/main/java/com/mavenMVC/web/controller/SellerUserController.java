//package com.mavenMVC.web.controller;
//
//import java.io.File;
//import java.util.Calendar;
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
//import com.mavenMVC.entity.SellerUser;
//import com.mavenMVC.service.ISellerUserService;
//import com.mavenMVC.util.RequestManager;
//import com.wordnik.swagger.annotations.ApiImplicitParam;
//import com.wordnik.swagger.annotations.ApiImplicitParams;
//import com.wordnik.swagger.annotations.ApiOperation;
//
//import net.sf.json.JSONObject;
//
//@Controller
//@RequestMapping("/sellerUser")
//public class SellerUserController {
//
//	protected final Logger logger = Logger.getLogger(String.valueOf(SellerUserController.class));
//
//	@Autowired
//	private ISellerUserService sellerUserService;
//
//	@Autowired
//	private HttpServletRequest request;
//
//	@RequestMapping(value = "/login", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "用户登录")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String login(@RequestParam(value = "cellphone", required = false) String cellphone,
//			@RequestParam(value = "password", required = false) String password) {
//		logger.info("Dealing with login Action...");
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			Assert.notNull(cellphone, "手机号不能为空");
//			Assert.notNull(password, "密码不能为空");
//			SellerUser userEntity = sellerUserService.loginValid(cellphone, password);
//			Assert.notNull(userEntity, "该手机号还未注册或密码有误");
//			result = JSONObject.fromObject(userEntity);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done login Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/register", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "新用户注册")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String register(@RequestParam(value = "cellphone", required = false) String cellphone,
//			@RequestParam(value = "password", required = false) String password) {
//		logger.info("Dealing with register Action...");
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			Assert.notNull(cellphone, "cellphone can not be empty");
//			Assert.notNull(password, "password can not be empty");
//			if (sellerUserService.ifUserCellphoneRegisted(cellphone)) {
//				requestManager.putErrorMessage("该手机号已注册");
//			} else {
//				SellerUser user = sellerUserService.registerUser(null, password, cellphone);
//				result = JSONObject.fromObject(user);
//			}
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done register Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/registerByWx", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "微信新用户注册")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String registerByWx(@RequestParam(value = "wxid", required = false) String wxid,
//			@RequestParam(value = "userName", required = false) String userName) {
//		logger.info("Dealing with register Action...");
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			Assert.notNull(wxid, "微信ID不能为空");
//			Assert.notNull(userName, "用户名不能为空");
//			SellerUser sellerUser = sellerUserService.ifUserWxIdRegisted(wxid);
//			if (sellerUser!=null) {
//				result = JSONObject.fromObject(sellerUser);
//			} else {
//				SellerUser user = sellerUserService.registerUserByWx(userName, wxid);
//				result = JSONObject.fromObject(user);
//			}
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done register Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/logout", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "退出登录")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String logout(@CurrentUser SellerUser currentUser) {
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			logger.info("Dealing with logout Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			if (currentUser.getUserLogin() == 1) {
//				currentUser.setUserLogin(0);
//				sellerUserService.updateUser(currentUser);
//			} else {
//				throw new Exception("该用户未登录");
//			}
//			result = JSONObject.fromObject(currentUser);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done logout Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/resetPassword", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "重置密码")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String resetPassword(@RequestParam(value = "cellphone", required = false) String cellphone,
//			@RequestParam(value = "password", required = false) String password) {
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			logger.info("Dealing with resetPassword Action...");
//			Assert.notNull(cellphone, "cellphone can not be empty");
//			Assert.notNull(password, "password can not be empty");
//			if (sellerUserService.ifUserCellphoneRegisted(cellphone)) {
//				if (sellerUserService.resetPassword(cellphone, password)) {
//				} else {
//					requestManager.putErrorMessage("修改密码失败");
//				}
//			} else {
//				requestManager.putErrorMessage("该手机号未注册过，请先完成注册");
//			}
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//			result = requestManager.printJson(null);
//		} finally {
//			logger.info("Done resetPassword Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/getUserInfo", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "获取用户信息")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String getUserInfo(@CurrentUser SellerUser currentUser) {
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			logger.info("Dealing with getUserInfo Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			result = JSONObject.fromObject(currentUser);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done getUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/updateUserInfo", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "修改用户信息")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String updateUserInfo(@CurrentUser SellerUser currentUser,
//			@RequestParam(value = "userName", required = false) String userName) {
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			if ((userName != null) && (!userName.trim().equals(""))) {
//				currentUser.setUserName(userName);
//			}
//			sellerUserService.updateUser(currentUser);
//			result = JSONObject.fromObject(currentUser);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/updateUserCellphone", produces = "text/json; charset=utf-8", method = {
//			RequestMethod.POST, RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "修改用户手机号")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String updateUserCellphone(@CurrentUser SellerUser currentUser,
//			@RequestParam(value = "cellphone", required = false) String cellphone) {
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			logger.info("Dealing with updateUserCellphone Action...");
//			// Assert.notNull(verifyCode,"验证码不能为空");
//			Assert.notNull(currentUser, "未登录系统");
//			Assert.notNull(cellphone, "新手机号不能为空");
//			currentUser.setUserCellphone(cellphone);
//			sellerUserService.updateUser(currentUser);
//			result = JSONObject.fromObject(currentUser);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserCellphone Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/uploadVerify", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "用户上传身份认证")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String uploadVerify(@CurrentUser SellerUser currentUser,
//			@RequestParam(value = "userName", required = false) String userName,
//			@RequestParam(value = "cellphone", required = false) String cellphone,
//			@RequestParam(value = "email", required = false) String email,
//			@RequestParam(value = "city", required = false) String city,
//			@RequestParam(value = "address", required = false) String address,
//			@RequestParam(value = "verifyImg", required = false) MultipartFile verifyImg) {
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			logger.info("Dealing with updateUserCellphone Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			Assert.notNull(verifyImg, "认证图片不能为空");
//			Assert.notNull(userName, "用户名不能为空");
//			Assert.notNull(email, "邮箱不能为空");
//			Assert.notNull(city, "城市不能为空");
//			Assert.notNull(address, "收货地址不能为空");
//			currentUser.setUserName(userName);
//			if (cellphone != null) {
//				currentUser.setUserCellphone(cellphone);
//			}
//			currentUser.setUserEmail(email);
//			currentUser.setUserCity(city);
//			currentUser.setUserAddress(address);
//			currentUser.setUserStatus(1);
//
//			logger.info(request);
//			String path = request.getSession().getServletContext().getRealPath("");
//			path = path.substring(0, path.lastIndexOf("/"));
//			// path = path.substring(0, path.lastIndexOf("/"));
//			// path = path.substring(0, path.lastIndexOf("/")) + "/files/" +
//			// currentUser.getUserToken() + "/";
//			path = path + "/files/";
//			logger.info("@@@@ " + path);
//			new File(path).mkdir();
//			// 得到上传的文件的文件名
//			String filename = "verifyimg_" + Calendar.getInstance().getTimeInMillis() + ".png";
//			path += filename;
//			verifyImg.transferTo(new File(path));
//			String fileNameUrl = "files/" + filename;
//			currentUser.setUserCertificateImages(fileNameUrl);
//			sellerUserService.updateUser(currentUser);
//			result = JSONObject.fromObject(currentUser);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserCellphone Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/updateUserHeadImage", produces = "text/json; charset=utf-8", method = {
//			RequestMethod.POST, RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "修改用户头像")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String updateUserHeadImage(@CurrentUser SellerUser currentUser,
//			@RequestParam(value = "file", required = false) MultipartFile file) {
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			logger.info("Dealing with updateUserHeadImage Action...");
//			Assert.notNull(file, "头像图片不能为空");
//			Assert.notNull(currentUser, "未登录系统");
//			logger.info(request);
//			String path = request.getSession().getServletContext().getRealPath("");
//			path = path.substring(0, path.lastIndexOf("/"));
//			// path = path.substring(0, path.lastIndexOf("/")) + "/files/" +
//			// currentUser.getUserToken() + "/";
//			path = path + "/files/";
//			logger.info("@@@@ " + path);
//			new File(path).mkdir();
//			// 得到上传的文件的文件名
//			String filename = "headImage_" + Calendar.getInstance().getTimeInMillis() + ".png";
//			path += filename;
//			file.transferTo(new File(path));
//			String fileNameUrl = "files/" + filename;
//			currentUser.setUserHeadImage(fileNameUrl);
//			sellerUserService.updateUser(currentUser);
//			result = JSONObject.fromObject(currentUser);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserHeadImage Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//}
