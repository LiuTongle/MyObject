//package com.mavenMVC.web.controller;
//
//import java.io.File;
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
//@RequestMapping("/commodity")
//public class CommodityController {
//	protected final Logger logger = Logger.getLogger(String.valueOf(CommodityController.class));
//	@Autowired
//	private ICommodityService commodityService;
//	@Autowired
//	private ICompeteService competeService;
//	@Autowired
//	private HttpServletRequest request;
//
//	@RequestMapping(value = "/getCommoditys", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "获取商品")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String getCommoditys(@CurrentUser Object currentUser,
//			@RequestParam(value = "offset") Integer offset,
//			@RequestParam(value = "receivedIds", required = false) List<Long> receivedIds) {
//		RequestManager requestManager = new RequestManager();
//		JSONArray result = new JSONArray();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			List<Commodity> commoditys = commodityService.getCommoditys(0, offset, receivedIds);
//			result = JSONArray.fromObject(commoditys);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//	
//	@RequestMapping(value = "/getCommoditysByTab", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "根据类型获取商品")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String getCommoditysByTab(@CurrentUser Object currentUser,
//			@RequestParam(value = "commodityTab", required = false) Integer commodityTab,
//			@RequestParam(value = "offset") Integer offset,
//			@RequestParam(value = "receivedIds", required = false) List<Long> receivedIds) {
//		RequestManager requestManager = new RequestManager();
//		JSONArray result = new JSONArray();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			List<Commodity> commoditys = commodityService.getCommoditysByTab(commodityTab, 0, offset, receivedIds);
//			result = JSONArray.fromObject(commoditys);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/searchCommoditys", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "搜索买家发布的需求商品")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String searchCommoditys(@CurrentUser Object currentUser,
//			@RequestParam(value = "query", required = false) String query,
//			@RequestParam(value = "offset") Integer offset,
//			@RequestParam(value = "receivedIds", required = false) List<Long> receivedIds) {
//		RequestManager requestManager = new RequestManager();
//		JSONArray result = new JSONArray();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			List<Commodity> commoditys = commodityService.searchCommodity(query, 0, offset, receivedIds);
//			result = JSONArray.fromObject(commoditys);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/getCommoditysByBuyerUser", produces = "text/json; charset=utf-8", method = {
//			RequestMethod.POST, RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "获取卖家参与的竞标")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String getCommoditysByBuyerUser(
//			@CurrentUser BuyerUser currentUser,
//			@RequestParam(value = "offset") Integer offset,
//			@RequestParam(value = "receivedIds", required = false) List<Long> receivedIds ) {
//		RequestManager requestManager = new RequestManager();
//		JSONArray result = new JSONArray();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(currentUser, "未登录系统");
//			List<Commodity> commoditys = commodityService.getCommoditysByBuyerUser(currentUser.getUserId(), 0, offset,
//					receivedIds);
//			for(Commodity commodity : commoditys){
//				JSONObject jsonObject = new JSONObject();
//				Long commodityId = commodity.getCommodityId();
//				List<Compete> competesByCommodity = competeService.getCompetesByCommodity(commodityId);
//				jsonObject.put("commodity", commodity);
//				jsonObject.put("compete", competesByCommodity);
//				result.add(jsonObject);
//			}
////			result = JSONArray.fromObject(commoditys);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/saveCommodity", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "买家上传商品")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String saveCommodity(@CurrentUser BuyerUser currentUser,
//			@RequestParam(value = "commodityTitle", required = false) String commodityTitle,
//			@RequestParam(value = "commodityDetailed", required = false) String commodityDetailed,
//			@RequestParam(value = "commodityMoney", required = false) String commodityMoney,
//			@RequestParam(value = "commodityTab", required = false) Integer commodityTab,
//			@RequestParam(value = "commodityLength", required = false) Integer commodityLength,
//			@RequestParam(value = "commodityLocation", required = false) String commodityLocation,
//			@RequestParam(value = "commodityImages", required = false) MultipartFile[] commodityImages) {
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			logger.info("Dealing with updateUserInfo Action...");
//			Assert.notNull(commodityImages, "图片不能为空");
//			Assert.notNull(commodityTitle, "商品标题不能为空");
//			Assert.notNull(commodityDetailed, "商品详情不能为空");
//			Assert.notNull(commodityTab, "商品类型不能为空");
//			Assert.notNull(commodityLength, "商品数量不能为空");
//			Assert.notNull(commodityLocation, "商品收货地址不能为空");
//			Assert.notNull(currentUser, "未登录系统");
//			Commodity commodity = new Commodity();
//			commodity.setBuyerUserId(currentUser.getUserId());
//			commodity.setCommodityTitle(commodityTitle);
//			commodity.setCommodityDetailed(commodityDetailed);
//			commodity.setCommodityMoney(commodityMoney);
//			commodity.setCommodityTab(commodityTab);
//			commodity.setCommodityLength(commodityLength);
//			commodity.setCommodityLocation(commodityLocation);
//			String images = "";
//			logger.info(request);
//			String path = request.getSession().getServletContext().getRealPath("");
//			path = path.substring(0, path.lastIndexOf("/"));
//			path = path + "/files/";
//			for (int i = 0; i < commodityImages.length; i++) {
//				String filename = "commodity_" + (i + 1) + "_" + Calendar.getInstance().getTimeInMillis() + ".png";
//				String imgPath = path + filename;
//				commodityImages[i].transferTo(new File(imgPath));
//				String fileNameUrl = "files/" + filename;
//				if (i == 0) {
//					images = images + fileNameUrl;
//				} else {
//					images = images + "," + fileNameUrl;
//				}
//			}
//			commodity.setCommodityImages(images);
//			commodityService.saveCommodity(commodity);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done updateUserInfo Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//}
