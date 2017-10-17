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
//import com.mavenMVC.entity.SellerUser;
//import com.mavenMVC.service.ICommodityService;
//import com.mavenMVC.service.ICompeteService;
//import com.mavenMVC.util.RequestManager;
//import com.wordnik.swagger.annotations.ApiImplicitParam;
//import com.wordnik.swagger.annotations.ApiImplicitParams;
//import com.wordnik.swagger.annotations.ApiOperation;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//@Controller
//@RequestMapping("/compete")
//public class CompeteController {
//	protected final Logger logger = Logger.getLogger(String.valueOf(CompeteController.class));
//	@Autowired
//	private ICompeteService competeService;
//	@Autowired
//	private ICommodityService commodityService;
//	@Autowired
//	private HttpServletRequest request;
//
//	@RequestMapping(value = "/getCompetes", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "获取卖家发起的竞标")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String getCompetes(@CurrentUser Object currentUser,
//			@RequestParam(value = "offset") Integer offset,
//			@RequestParam(value = "receivedIds", required = false) List<Long> receivedIds) {
//		RequestManager requestManager = new RequestManager();
//		JSONArray result = new JSONArray();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			List<Compete> competes = competeService.getCompetes(0, offset, receivedIds);
//			for (Compete compete : competes) {
//				compete.setCompeteCollect(
//						competeService.competeIfCollect(compete.getCompeteId(), ((BuyerUser) currentUser).getUserId())
//								? 1 : 0);
//			}
//			result = JSONArray.fromObject(competes);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/getCompetesByTab", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "根据分类获取卖家发起的竞标")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String getCompetesByTab(@CurrentUser Object currentUser,
//			@RequestParam(value = "competeTab", required = false) Integer competeTab,
//			@RequestParam(value = "offset") Integer offset,
//			@RequestParam(value = "receivedIds", required = false) List<Long> receivedIds) {
//		RequestManager requestManager = new RequestManager();
//		JSONArray result = new JSONArray();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			List<Compete> competes = competeService.getCompetesByTab(competeTab, 0, offset, receivedIds);
//			for (Compete compete : competes) {
//				compete.setCompeteCollect(
//						competeService.competeIfCollect(compete.getCompeteId(), ((BuyerUser) currentUser).getUserId())
//								? 1 : 0);
//			}
//			result = JSONArray.fromObject(competes);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/searchCompetes", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "搜索卖家发起的竞标")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String searchCompetes(@CurrentUser Object currentUser,
//			@RequestParam(value = "query", required = false) String query,
//			@RequestParam(value = "offset") Integer offset,
//			@RequestParam(value = "receivedIds", required = false) List<Long> receivedIds) {
//		RequestManager requestManager = new RequestManager();
//		JSONArray result = new JSONArray();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			int userType = 0;
//			if (currentUser instanceof BuyerUser) {
//				userType = 0;
//			} else if (currentUser instanceof SellerUser) {
//				userType = 1;
//			} else {
//				requestManager.putErrorMessage("用户类型错误");
//			}
//			List<Compete> competes = competeService.searchCompete(query, 0, offset, receivedIds);
//			for (Compete compete : competes) {
//				compete.setCompeteCollect(
//						competeService.competeIfCollect(compete.getCompeteId(), ((BuyerUser) currentUser).getUserId())
//								? 1 : 0);
//			}
//			result = JSONArray.fromObject(competes);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/saveCompete", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "卖家发起竞标")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String saveCompete(@CurrentUser SellerUser currentUser,
//			@RequestParam(value = "competeTitle", required = false) String competeTitle,
//			@RequestParam(value = "competeDetailed", required = false) String competeDetailed,
//			@RequestParam(value = "competeMoney", required = false) Double competeMoney,
//			@RequestParam(value = "competeFreight", required = false) Double competeFreight,
//			@RequestParam(value = "competeNumber", required = false) Integer competeNumber,
//			@RequestParam(value = "competeTab", required = false) Integer competeTab,
//			@RequestParam(value = "competeTime", required = false) String competeTime,
//			@RequestParam(value = "competeImages", required = false) MultipartFile[] competeImages,
//			@RequestParam(value = "commodityId", required = false) Long commodityId) {
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(competeImages, "头像图片不能为空");
//			Assert.notNull(competeTitle, "商品标题不能为空");
//			Assert.notNull(competeDetailed, "商品详情不能为空");
//			Assert.notNull(competeMoney, "商品金额不能为空");
//			Assert.notNull(competeFreight, "商品运费不能为空");
//			Assert.notNull(competeNumber, "商品数量不能为空");
//			Assert.notNull(competeTab, "商品标签不能为空");
//			Assert.notNull(competeTime, "商品周期不能为空");
//			Assert.notNull(commodityId, "买家商品ID不能为空");
//			Assert.notNull(currentUser, "未登录系统");
//			if (currentUser.getUserStatus()!=2) {
//				requestManager.putErrorMessage("通过认证后才可以上传商品哦~");
//			}else{
//				if (competeService.getCompetesByCommodity(commodityId).size() > 5) {
//					requestManager.putErrorMessage("商品竞标数量已到达上线");
//				} else {
//					Compete Compete = new Compete();
//					Compete.setCommodityId(commodityId);
//					Compete.setCompeteTitle(competeTitle);
//					Compete.setCompeteDetailed(competeDetailed);
//					Compete.setSellerUserId(currentUser.getUserId());
//					Compete.setCompeteMoney(competeMoney);
//					Compete.setCompeteDealNumber(0);
//					Compete.setCompeteStatus(0);
//					Compete.setCompeteTab(competeTab);
//					Compete.setCompeteTime(competeTime);
//					Compete.setCompeteFreight(competeFreight);
//					Compete.setCompeteNumber(competeNumber);
//					String images = "";
//					logger.info(request);
//					String path = request.getSession().getServletContext().getRealPath("");
//					path = path.substring(0, path.lastIndexOf("/"));
//					path = path + "/files/";
//					for (int i = 0; i < competeImages.length; i++) {
//						String filename = "compete_" + (i + 1) + "_" + Calendar.getInstance().getTimeInMillis() + ".png";
//						String imgPath = path + filename;
//						competeImages[i].transferTo(new File(imgPath));
//						String fileNameUrl = "files/" + filename;
//						if (i == 0) {
//							images = images + fileNameUrl;
//						} else {
//							images = images + "," + fileNameUrl;
//						}
//					}
//					Compete.setCompeteImages(images);
//					competeService.saveCompete(Compete);
//				}
//			}
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/getCompetesByCommodity", produces = "text/json; charset=utf-8", method = {
//			RequestMethod.POST, RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "获取商品的竞标")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String getCompetesByCommodity(@CurrentUser Object currentUser,
//			@RequestParam(value = "offset") Integer offset,
//			@RequestParam(value = "receivedIds", required = false) List<Long> receivedIds) {
//		RequestManager requestManager = new RequestManager();
//		JSONArray result = new JSONArray();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			Long userId = null;
//			if (currentUser instanceof BuyerUser) {
//				userId = ((BuyerUser)currentUser).getUserId();
//			}else if(currentUser instanceof SellerUser){
//				userId = ((SellerUser)currentUser).getUserId();
//			}else{
//				requestManager.putErrorMessage("用户类型错误");
//			}
//			List<Compete> Competes = competeService.getCompetesByCommodity(userId, 0, offset, receivedIds);
//			for (Compete compete : Competes) {
//				JSONObject jsonObject = new JSONObject();
//				Commodity commodity = commodityService.getById(compete.getCommodityId());
//				jsonObject.put("compete", compete);
//				jsonObject.put("commodity", commodity);
//				result.add(jsonObject);
//			}
////			result = JSONArray.fromObject(Competes);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/getCompeteCountByTab", produces = "text/json; charset=utf-8", method = {
//			RequestMethod.POST, RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "获取商品的数量")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String getCompeteCountByTab(
//			@RequestParam(value = "competeTab", required = false) Integer competeTab) {
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(competeTab, "商品类型不能为空");
//			Integer count = competeService.getCompeteCountByTab(competeTab);
//			result.put("count", count);
//			return requestManager.printJson(result).toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/collectCommodity", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "收藏/取消收藏商品")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String collectCommodity(@CurrentUser BuyerUser currentUser,
//			@RequestParam(value = "competeId", required = false) Long competeId) {
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			Assert.notNull(competeId, "竞标商品ID不能为空");
//			if (competeService.collectCompete(competeId, currentUser.getUserId())) {
//				// 收藏成功
//				// result = JSONObject.fromObject("0");
//			} else {
//				// 取消收藏成功
//				// result = JSONObject.fromObject("1");
//			}
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//	
//	@RequestMapping(value = "/getCompeteByCollect", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "买家获取收藏的商品")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String getCompeteByCollect(@CurrentUser Object currentUser,
//			@RequestParam(value = "offset") Integer offset,
//			@RequestParam(value = "receivedIds", required = false) List<Long> receivedIds) {
//		RequestManager requestManager = new RequestManager();
//		JSONArray result = new JSONArray();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			Long userId = null;
//			if (currentUser instanceof BuyerUser) {
//				userId = ((BuyerUser)currentUser).getUserId();
//			}else if(currentUser instanceof SellerUser){
//				userId = ((SellerUser)currentUser).getUserId();
//			}else{
//				requestManager.putErrorMessage("用户类型错误");
//			}
//			List<Collect> byCollect = commodityService.getCommoditysByCollect(userId.longValue(), 0,offset,receivedIds);
//			for (Collect collect : byCollect) {
//				Compete compete = competeService.getById(collect.getCollectContentId());
//				result.add(compete);
//			}
//			result = JSONArray.fromObject(result);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//}
