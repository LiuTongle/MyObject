//package com.mavenMVC.web.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.mavenMVC.authorization.annotation.CurrentUser;
//import com.mavenMVC.entity.BuyerUser;
//import com.mavenMVC.entity.Commodity;
//import com.mavenMVC.entity.Compete;
//import com.mavenMVC.entity.OrderForm;
//import com.mavenMVC.entity.SellerUser;
//import com.mavenMVC.service.ICommodityService;
//import com.mavenMVC.service.ICompeteService;
//import com.mavenMVC.service.IOrderFormService;
//import com.mavenMVC.service.IOrderService;
//import com.mavenMVC.service.IPayService;
//import com.mavenMVC.service.ISellerUserService;
//import com.mavenMVC.util.OrderCode;
//import com.mavenMVC.util.RequestManager;
//import com.mavenMVC.util.RequestUtil;
//import com.pingplusplus.model.Charge;
//import com.pingplusplus.model.Event;
//import com.pingplusplus.model.EventData;
//import com.pingplusplus.model.PingppObject;
//import com.pingplusplus.model.Webhooks;
//import com.wordnik.swagger.annotations.ApiImplicitParam;
//import com.wordnik.swagger.annotations.ApiImplicitParams;
//import com.wordnik.swagger.annotations.ApiOperation;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//@Controller
//@RequestMapping("/orderForm")
//public class OrderFormController {
//	protected final Logger logger = Logger.getLogger(String.valueOf(OrderFormController.class));
//
//	@Autowired
//	private HttpServletRequest request;
//
//	@Autowired
//	private IPayService iPayService;
//
//	@Autowired
//	private IOrderFormService orderFormService;
//
//	@Autowired
//	private ICompeteService competeService;
//	
//	@Autowired
//	private ICommodityService commodityService;
//	
//	@Autowired
//	private ISellerUserService selectUserService;
//
//	@RequestMapping(value = "/getOrderFormById", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "id获取订单")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String login(@CurrentUser Object currentUser,
//			@RequestParam(value = "orderFormId", required = false) Long orderFormId) {
//		logger.info("Dealing with login Action...");
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			Assert.notNull(currentUser, "未登录系统");
//			Assert.notNull(orderFormId, "订单ID不能为空");
//			OrderForm orderForm = orderFormService.getOrderFormById(orderFormId);
//			result = JSONObject.fromObject(orderForm);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done login Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/getOrderFormByUser", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "获取订单")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String getOrderFormByUser(@CurrentUser Object currentUser,
//			@RequestParam(value = "offset") Integer offset,
//			@RequestParam(value = "receivedIds", required = false) List<Long> receivedIds) {
//		logger.info("Dealing with login Action...");
//		RequestManager requestManager = new RequestManager();
//		JSONArray result = new JSONArray();
//		try {
//			Assert.notNull(currentUser, "未登录系统");
//			if (currentUser instanceof BuyerUser) {
//				result = JSONArray
//						.fromObject(orderFormService.getOrderFormsByBuyerId(((BuyerUser) currentUser).getUserId(),0, offset, receivedIds));
//			} else if (currentUser instanceof SellerUser) {
//				result = JSONArray
//						.fromObject(orderFormService.getOrderFormsBySellerId(((SellerUser) currentUser).getUserId(),0, offset, receivedIds));
//			} else {
//				requestManager.putErrorMessage("用户类型错误");
//			}
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done login Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/updateOrderFormStatus", produces = "text/json; charset=utf-8", method = {
//			RequestMethod.POST, RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "修改订单状态")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String updateOrderFormStatus(@CurrentUser Object currentUser,
//			@RequestParam(value = "orderFormId", required = false) Long orderFormId,
//			@RequestParam(value = "orderFormStatus", required = false) Integer orderFormStatus) {
//		logger.info("Dealing with login Action...");
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			Assert.notNull(currentUser, "未登录系统");
//			Assert.notNull(orderFormId, "订单ID不能为空");
//			Assert.notNull(orderFormStatus, "订单状态不能为空");
//			OrderForm orderForm = orderFormService.getOrderFormById(orderFormId);
//			if (orderForm != null) {
//				orderForm.setOrderformStatus(orderFormStatus);
//				orderFormService.saveOrUpdateOrderForm(orderForm);
//				result = JSONObject.fromObject(orderForm);
//			} else {
//				requestManager.putErrorMessage("没有这个订单");
//			}
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done login Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/orderPay", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "订单支付")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String orderPay(@CurrentUser Object currentUser, @RequestParam(value = "orderId") Long orderId,
//			@RequestParam(value = "orderMoney") Integer orderMoney,
//			@RequestParam(value = "orderChannel") String orderChannel) {
//		logger.info("Dealing with login Action...");
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			Assert.notNull(currentUser, "未登录系统");
//			Assert.notNull(orderId, "订单ID不能为空");
//			Assert.notNull(orderMoney, "订单金额不能为空");
//			Assert.notNull(orderChannel, "订单支付类型不能为空");
//			Long userId;
//			if (currentUser instanceof BuyerUser) {
//				userId = ((BuyerUser) currentUser).getUserId();
//			} else {
//				throw new Exception("当前用户类型错误");
//			}
//			OrderForm orderForm = orderFormService.getOrderFormById(orderId);
//			if (orderForm.getOrderformStatus() == OrderCode.ORDER_UNPAID) {
//				String ip = RequestUtil.getIpAddress(request);
//				Charge charge = iPayService.payOrder(orderId, orderMoney, orderChannel, ip);
//				logger.info(charge);
//				orderForm.setOrderChargeId(charge.getId());
//				orderForm.setOrderPayChannel(orderChannel);
//				// orderForm.setOrderformStatus(OrderCode.ORDER_PAID);
//				orderFormService.saveOrUpdateOrderForm(orderForm);
//				String resultString = "{\"error_code\":0,\"error_message\":\"\",\"data\":" + charge.toString() + "}";
//				return resultString;
//			} else {
//				throw new Exception("订单已支付");
//			}
//		} catch (Exception e) {
//			logger.info("Done payOrder Action!");
//			requestManager.putErrorMessage(e.getMessage());
//			e.printStackTrace();
//			return requestManager.printJson(result).toString();
//		}
//		// finally {
//		// logger.info("Done login Action!");
//		// return requestManager.printJson(result).toString();
//		// }
//	}
//
//	@RequestMapping(value = "/payOrderSuccess", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "支付订单成功")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String payOrderSuccess(@RequestBody String Webhook) {
//		logger.info("Done payOrderSuccess Action!");
//		try {
//			Event event = Webhooks.eventParse(Webhook);
//			if (event != null) {
//				if ("charge.succeeded".equals(event.getType())) {
//					EventData eventData = event.getData();
//					PingppObject chargeRsObj = eventData.getObject();
//					String objectString = chargeRsObj.toString();
//					logger.info(objectString);
//					JSONObject jsonObject = JSONObject.fromObject(objectString);
//					Long orderId = jsonObject.getLong("order_no");
//					Integer paidMoney = jsonObject.getInt("amount");
//					OrderForm orderForm = orderFormService.getOrderFormById(orderId);
//					if (orderForm != null && orderForm.getOrderformStatus() == OrderCode.ORDER_UNPAID) {
//						orderForm.setOrderformStatus(OrderCode.ORDER_PAID);
//						orderForm.setOrderPaidMoney(paidMoney);
//						orderFormService.saveOrUpdateOrderForm(orderForm);
//						SellerUser userById = selectUserService.getUserById(orderForm.getSellerUserId());
//						userById.setUserMoney(userById.getUserMoney()+paidMoney);
//						selectUserService.updateUser(userById);
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			logger.info("Done payOrderSuccess Action!");
//			return null;
//		}
//	}
//
//	@RequestMapping(value = "/orderFormSendOutGoods", produces = "text/json; charset=utf-8", method = {
//			RequestMethod.POST, RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "订单发货")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String orderFormSendOutGoods(@CurrentUser Object currentUser,
//			@RequestParam(value = "orderFormId", required = false) Long orderFormId,
//			@RequestParam(value = "orderFormFastMail", required = false) String orderFormFastMail,
//			@RequestParam(value = "orderFormFastMailNumber", required = false) String orderFormFastMailNumber) {
//		logger.info("Dealing with login Action...");
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			Assert.notNull(currentUser, "未登录系统");
//			Assert.notNull(orderFormId, "订单ID不能为空");
//			OrderForm orderForm = orderFormService.getOrderFormById(orderFormId);
//			if (orderForm != null) {
//				orderForm.setOrderformStatus(2);
//				orderForm.setOrderformFastMail(orderFormFastMail);
//				orderForm.setOrderformFastMailNumber(orderFormFastMailNumber);
//				orderFormService.saveOrUpdateOrderForm(orderForm);
//				result = JSONObject.fromObject(orderForm);
//			} else {
//				requestManager.putErrorMessage("没有这个订单");
//			}
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done login Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//
//	@RequestMapping(value = "/saveOrderForm", produces = "text/json; charset=utf-8", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	// @Authorization
//	@ApiOperation(value = "创建订单")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "sig", value = "sig", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "requestTime", value = "requestTime", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "tokenType", value = "tokenType", required = true, dataType = "string", paramType = "header"), })
//	public @ResponseBody String saveOrderForm(@CurrentUser BuyerUser currentUser,
//			@RequestParam(value = "competeId", required = false) Long competeId,
//			@RequestParam(value = "commodityId", required = false) Long commodityId,
//			@RequestParam(value = "sellerId", required = false) Long sellerId,
//			@RequestParam(value = "number", required = false) Integer number,
//			@RequestParam(value = "address", required = false) String address) {
//		logger.info("Dealing with login Action...");
//		Assert.notNull(currentUser, "未登录系统");
//		Assert.notNull(competeId, "竞标ID不能为空");
//		Assert.notNull(sellerId, "卖家ID不能为空");
//		Assert.notNull(number, "购买数量不能为空");
//		Assert.notNull(address, "收获地址不能为空");
//		RequestManager requestManager = new RequestManager();
//		JSONObject result = new JSONObject();
//		try {
//			
//			if (commodityId!=null) {
//				Commodity commodity = commodityService.getById(commodityId);
//				commodity.setCommodityStatus(1);
//				commodityService.saveCommodity(commodity);
//			}
//			
//			Compete compete = competeService.getById(competeId);
//			compete.setCompeteStatus(1);
//			compete.setCompeteDealNumber(compete.getCompeteDealNumber().intValue()+1);
//			competeService.saveCompete(compete);
//			
//			OrderForm orderForm = new OrderForm();
//			orderForm.setOrderformId(RequestUtil.genItemId());
//			orderForm.setBuyeruserId(currentUser.getUserId());
//			orderForm.setSellerUserId(sellerId);
//			orderForm.setCompeteId(competeId);
//			orderForm.setOrderformNumber(number);
//			orderForm.setOrderformDetailed(compete.getCompeteDetailed());
//			orderForm.setOrderformFreight(compete.getCompeteFreight());
//			orderForm.setOrderformMoney(compete.getCompeteMoney());
//			orderForm.setOrderformImages(compete.getCompeteImages());
//			orderForm.setOrderformTitle(compete.getCompeteTitle());
//			orderForm.setOrderTab(compete.getCompeteTab());
//			orderForm.setOrderformStatus(0);
//			orderFormService.saveOrUpdateOrderForm(orderForm);
//			OrderForm orderFormById = orderFormService.getOrderFormById(orderForm.getOrderformId());
//			result = JSONObject.fromObject(orderFormById);
//		} catch (Exception e) {
//			requestManager.putErrorMessage(e.getMessage());
//		} finally {
//			logger.info("Done login Action!");
//			return requestManager.printJson(result).toString();
//		}
//	}
//}
