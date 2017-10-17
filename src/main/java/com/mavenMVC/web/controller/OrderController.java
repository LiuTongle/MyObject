// package com.mavenMVC.web.controller;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import javax.servlet.http.HttpServletRequest;
//
// import org.apache.log4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.util.Assert;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
//
// import com.mavenMVC.authorization.annotation.CurrentUser;
// import com.mavenMVC.entity.Doctor;
// import com.mavenMVC.entity.Order;
// import com.mavenMVC.entity.Patient;
// import com.mavenMVC.entity.Record;
// import com.mavenMVC.entity.User;
// import com.mavenMVC.service.IDoctorService;
// import com.mavenMVC.service.IOrderService;
// import com.mavenMVC.service.IPatientService;
// import com.mavenMVC.service.IPayService;
// import com.mavenMVC.service.IRecordService;
// import com.mavenMVC.service.IUserService;
// import com.mavenMVC.util.Code;
// import com.mavenMVC.util.HttpRequestUtil;
// import com.mavenMVC.util.OrderCode;
// import com.mavenMVC.util.RequestManager;
// import com.mavenMVC.util.RequestUtil;
// import com.pingplusplus.model.Charge;
// import com.pingplusplus.model.Event;
// import com.pingplusplus.model.EventData;
// import com.pingplusplus.model.PingppObject;
// import com.pingplusplus.model.Webhooks;
// import com.wordnik.swagger.annotations.ApiImplicitParam;
// import com.wordnik.swagger.annotations.ApiImplicitParams;
// import com.wordnik.swagger.annotations.ApiOperation;
// import com.wordnik.swagger.annotations.ApiParam;
//
// import net.sf.json.JSONArray;
// import net.sf.json.JSONObject;
//
/// *** Created by lizai on 15/6/25. */
//
// @Controller
// @RequestMapping("/order")
// public class OrderController {
//
// protected final Logger logger =
// Logger.getLogger(String.valueOf(OrderController.class));
//
// @Autowired
// private IOrderService iOrderService;
//
// @Autowired
// private IPatientService iPatientService;
//
// @Autowired
// private IDoctorService iDoctorService;
//
// @Autowired
// private IUserService iUserService;
//
// @Autowired
// private IPayService iPayService;
//
// @Autowired
// private IRecordService iRecordService;
//
// @Autowired
// private HttpServletRequest request;
//
// @RequestMapping(value = "/getMyOrders", produces = "text/json;charset=utf-8",
// method = { RequestMethod.POST,
// RequestMethod.GET })
//
// // @Authorization
// @ApiOperation(value = "获取当前登录用户（医生或者患者）的订单")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String getMyOrders(@CurrentUser Object currentUser,
// @ApiParam(name = "type", value = "0:当前订单,1:历史") @RequestParam(value = "type",
// required = false) Integer type,
// @RequestParam(value = "start") Integer start, @RequestParam(value = "offset")
// Integer offset,
// @RequestParam(value = "receivedIds", required = false) List<Long>
// receivedIds,
// @RequestParam(value = "orderType", required = false) Integer orderType) {
// logger.info("Dealing with getMyOrders Action...");
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// Assert.notNull(currentUser, "未登录系统");
// Assert.notNull(start, "查询起始不能为空");
// Assert.notNull(offset, "查询终止不能为空");
// if (start < 0 || offset <= 0 || start > offset) {
// throw new Exception("查询条件有误");
// }
// Long userId, doctorId;
// List<Order> orders;
// if (currentUser instanceof Doctor) {
// doctorId = ((Doctor) currentUser).getDoctorId();
// List<Integer> payStatuses = new ArrayList<Integer>();
// payStatuses.add(OrderCode.ORDER_PAID);
// payStatuses.add(OrderCode.ORDER_SETTLED);
// payStatuses.add(OrderCode.ORDER_REFUNDED);
// orders = iOrderService.getOrdersByDoctorId(doctorId, null, payStatuses,
// orderType, start, offset,
// receivedIds);
// } else if (currentUser instanceof User) {
// userId = ((User) currentUser).getUserId();
// List<Integer> statuses = new ArrayList<Integer>();
// if (type == 0) {
// statuses.add(OrderCode.ORDER_DOC_ESTABLISHED);
// statuses.add(OrderCode.ORDER_NEW);
// statuses.add(OrderCode.ORDER_USER_CANCELED);
// } else if (type == 1) {
// statuses.add(OrderCode.ORDER_NORMAL_FINISHED);
// statuses.add(OrderCode.ORDER_ABNORMAL_FINISHED);
// statuses.add(OrderCode.ORDER_DOC_UNESTABLISHED);
// }
// orders = iOrderService.getOrdersByUserId(userId, statuses, null, orderType,
// start, offset, receivedIds);
// } else {
// throw new Exception("当前用户类型错误");
// }
// List<JSONObject> orderDetails = new ArrayList<JSONObject>();
// if (orders != null && orders.size() > 0) {
// for (Order order : orders) {
// JSONObject o = JSONObject.fromObject(order);
// Doctor doctor = iDoctorService.getDoctorById(order.getOrderDid());
// o.put("orderDoctor", doctor);
// Patient patient = iPatientService.getPatientById(order.getOrderPid());
// User user = iUserService.getUserById(patient.getPatientUserId());
// o.put("orderUserCellphone", user.getUserCellphone());
// o.put("orderPatient", patient);
// Record record = iRecordService.getLastRecordByPatientId(order.getOrderPid(),
// 0);
// o.put("orderRecord", record);
// orderDetails.add(o);
// }
// }
// result = JSONArray.fromObject(orderDetails);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done getMyOrders Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/getDoctorScheduleOrders", produces =
// "text/json;charset=utf-8", method = {
// RequestMethod.POST, RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "按时间段获取医生订单")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String getDoctorScheduleOrders(@CurrentUser Object
// currentUser,
// @RequestParam(value = "startTime") Long startTime, @RequestParam(value =
// "endTime") Long endTime) {
// logger.info("Dealing with getDoctorScheduleOrders Action...");
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// Assert.notNull(currentUser, "未登录系统");
// Assert.notNull(startTime, "查询起始不能为空");
// Assert.notNull(endTime, "查询终止不能为空");
// if (startTime <= 0 || endTime <= 0 || endTime < startTime) {
// throw new Exception("查询条件有误");
// }
// // if(orderType == null){
// // orderType = 0;
// // }
// Long userId;
// List<Order> orders;
// if (currentUser instanceof Doctor) {
// userId = ((Doctor) currentUser).getDoctorId();
// List<Integer> payStatuses = new ArrayList<Integer>();
// payStatuses.add(OrderCode.ORDER_PAID);
// payStatuses.add(OrderCode.ORDER_SETTLED);
// payStatuses.add(OrderCode.ORDER_REFUNDED);
// orders = iOrderService.getOrdersByDoctorId(userId, null, payStatuses,
// startTime, endTime);
// } else {
// throw new Exception("当前用户类型错误");
// }
// result = JSONArray.fromObject(orders);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done getDoctorScheduleOrders Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/createOrder", produces = "text/json;charset=utf-8",
// method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "创建订单")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String createOrder(@CurrentUser Object currentUser,
// @RequestParam(value = "orderDoctorId") Long orderDoctorId,
// @RequestParam(value = "orderPatientId") Long orderPatientId,
// @RequestParam(value = "orderType", required = false) Integer orderType,
// @RequestParam(value = "orderRecordId", required = false) Long orderRecordId)
// {
// logger.info("Dealing with createOrder Action...");
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// Assert.notNull(currentUser, "未登录系统");
// Assert.notNull(orderDoctorId, "orderDoctorId can not be empty");
// Assert.notNull(orderPatientId, "orderPatientId can not be empty");
// Long userId;
// if (currentUser instanceof User) {
// userId = ((User) currentUser).getUserId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// if (orderType == null) {
// orderType = 0;
// }
// Doctor doctor = iDoctorService.getDoctorById(orderDoctorId);
// if (doctor != null && doctor.getDoctorSwitch() == true) {
// Patient patient = iPatientService.getPatientById(orderPatientId);
// if ((patient != null) && (patient.getPatientUserId() == userId)) {
// Order order = new Order();
// order.setOrderDid(orderDoctorId);
// order.setOrderPid(orderPatientId);
// order.setOrderStatus(OrderCode.ORDER_NEW);
// order.setOrderPayStatus(OrderCode.ORDER_UNPAID);
// order.setOrderDateTime((long) 0);
// order.setOrderType(orderType);
// order.setOrderRid(orderRecordId);
// iOrderService.saveOrUpdateOrder(order);
// result = JSONObject.fromObject(order);
// } else {
// throw new Exception("orderPatientId错误");
// }
// } else {
// throw new Exception("该医生最近停止接单了");
// }
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done createOrder Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/establishOrder", produces =
// "text/json;charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "医生确认订单")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String establishOrder(@CurrentUser Object currentUser,
// @RequestParam(value = "orderId") Long orderId, @RequestParam(value =
// "orderDateTime") Long orderDateTime) {
// logger.info("Dealing with establishOrder Action...");
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// Assert.notNull(currentUser, "未登录系统");
// Assert.notNull(orderId, "orderId can not be empty");
// Assert.notNull(orderDateTime, "orderDateTime can not be empty");
// Long doctorId;
// if (currentUser instanceof Doctor) {
// doctorId = ((Doctor) currentUser).getDoctorId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// Order order = iOrderService.getOrderById(orderId);
// if (order == null || order.getOrderDid() != doctorId) {
// throw new Exception("当前医生无权操作该订单");
// }
// if (order.getOrderPayStatus() != OrderCode.ORDER_PAID ||
// order.getOrderStatus() != OrderCode.ORDER_NEW) {
// throw new Exception("订单状态不正确");
// }
// order.setOrderDateTime(orderDateTime);
// order.setOrderStatus(OrderCode.ORDER_DOC_ESTABLISHED);
// iOrderService.saveOrUpdateOrder(order);
// Patient patient = iPatientService.getPatientById(order.getOrderPid());
// User user = iUserService.getUserById(patient.getPatientUserId());
// if (user != null && user.getUserInstallationId() != null) {
// JSONObject jo = new JSONObject();
// JSONObject data = new JSONObject();
// if (user.getUserOsType() == 0) {
// data.put("action", "com.cloud.clinic.push");
// }
// data.put("type", "order");
// data.put("content", "订单已被确认");
// JSONObject where = new JSONObject();
// if (user.getUserOsType() == 0) {
// where.put("installationId", user.getUserInstallationId());
// } else if (user.getUserOsType() == 1) {
// where.put("deviceToken", user.getUserInstallationId());
// jo.put("prod", Code.IOS_PROD);
// }
// jo.put("where", where);
// jo.put("data", data);
// HttpRequestUtil.sendPost(Code.MESSAGE_URL, jo.toString(), 0);
// } else {
// throw new Exception("要推送用户未注册到服务中");
// }
// result = JSONObject.fromObject(order);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done establishOrder Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/refuseOrder", produces = "text/json;charset=utf-8",
// method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "医生拒绝订单")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String denyOrder(@CurrentUser Object currentUser,
// @RequestParam(value = "orderId") Long orderId,
// @RequestParam(value = "orderRefuseReason") String orderRefuseReason) {
// logger.info("Dealing with refuseOrder Action...");
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// Assert.notNull(currentUser, "未登录系统");
// Assert.notNull(orderId, "orderId can not be empty");
// Long doctorId;
// if (currentUser instanceof Doctor) {
// doctorId = ((Doctor) currentUser).getDoctorId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// Order order = iOrderService.getOrderById(orderId);
// if (order == null || order.getOrderDid() != doctorId ||
// order.getOrderStatus() != OrderCode.ORDER_NEW) {
// throw new Exception("当前医生无权操作该订单或该订单已非新建状态");
// }
// if (order.getOrderPayStatus() != OrderCode.ORDER_PAID ||
// order.getOrderStatus() != OrderCode.ORDER_NEW) {
// throw new Exception("订单状态不正确");
// }
// order.setOrderStatus(OrderCode.ORDER_DOC_UNESTABLISHED);
// order.setOrderRefuseReason(orderRefuseReason);
// iOrderService.saveOrUpdateOrder(order);
// result = JSONObject.fromObject(order);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done refuseOrder Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/finishOrder", produces = "text/json;charset=utf-8",
// method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "结束订单")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String finishOrder(@CurrentUser Object currentUser,
// @RequestParam(value = "orderId") Long orderId,
// @RequestParam(value = "isExceptionFinished", required = false) Integer
// isExceptionFinished,
// @RequestParam(value = "orderProceedTime") Long orderProceedTime) {
// logger.info("Dealing with finishOrder Action...");
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// Assert.notNull(currentUser, "未登录系统");
// Assert.notNull(orderId, "orderId can not be empty");
// Long doctorId, userId;
// Order order = iOrderService.getOrderById(orderId);
// if (order.getOrderStatus() == OrderCode.ORDER_DOC_ESTABLISHED
// && order.getOrderPayStatus() == OrderCode.ORDER_PAID) {
// if (currentUser instanceof Doctor) {
// doctorId = ((Doctor) currentUser).getDoctorId();
// if (doctorId != order.getOrderDid()) {
// throw new Exception("当前医生无权操作该订单");
// }
// } else if (currentUser instanceof User) {
// userId = ((User) currentUser).getUserId();
// Patient patient = iPatientService.getPatientById(order.getOrderPid());
// if (patient == null || patient.getPatientUserId() != userId) {
// throw new Exception("当前用户无权操作该订单");
// }
// } else {
// throw new Exception("当前用户类型错误");
// }
// if (isExceptionFinished != null && isExceptionFinished > 0) {
// order.setOrderStatus(OrderCode.ORDER_ABNORMAL_FINISHED);
// } else {
// order.setOrderStatus(OrderCode.ORDER_NORMAL_FINISHED);
// }
// order.setOrderProceedTime(orderProceedTime);
// iOrderService.saveOrUpdateOrder(order);
// result = JSONObject.fromObject(order);
// } else {
// throw new Exception("当前订单未确认或未支付");
// }
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done finishOrder Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/payOrder", produces = "text/json; charset=utf-8",
// method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "支付订单")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String payOrder(@CurrentUser Object currentUser,
// @RequestParam(value = "orderId") Long orderId,
// @RequestParam(value = "orderMoney") Integer orderMoney,
// @RequestParam(value = "orderChannel") String orderChannel) {
// logger.info("Dealing with payOrder Action...");
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// Assert.notNull(currentUser, "未登录系统");
// Long userId;
// if (currentUser instanceof User) {
// userId = ((User) currentUser).getUserId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// Order order = iOrderService.getOrderById(orderId);
// if (order.getOrderPayStatus() == OrderCode.ORDER_UNPAID) {
// String ip = RequestUtil.getIpAddress(request);
// Charge charge = iPayService.payOrder(orderId, orderMoney, orderChannel, ip);
// logger.info(charge);
// order.setOrderChargeId(charge.getId());
// order.setOrderPayChannel(orderChannel);
// iOrderService.saveOrUpdateOrder(order);
// String resultString = "{\"error_code\":0,\"error_message\":\"\",\"data\":" +
// charge.toString() + "}";
// logger.info("Done payOrder Action!");
// return resultString;
// } else {
// throw new Exception("订单已支付");
// }
// } catch (Exception e) {
// logger.info("Done payOrder Action!");
// requestManager.putErrorMessage(e.getMessage());
// e.printStackTrace();
// return requestManager.printJson(result).toString();
// }
// // finally {
// // logger.info("Done payOrder Action!");
// //
// // }
// }
//
// @RequestMapping(value = "/payOrderSuccess", produces =
// "text/json;charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "支付订单成功")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String payOrderSuccess(@RequestBody String Webhook) {
// logger.info("Done payOrderSuccess Action!");
// try {
// Event event = Webhooks.eventParse(Webhook);
// if (event != null) {
// if ("charge.succeeded".equals(event.getType())) {
// EventData eventData = event.getData();
// PingppObject chargeRsObj = eventData.getObject();
// String objectString = chargeRsObj.toString();
// logger.info(objectString);
// JSONObject jsonObject = JSONObject.fromObject(objectString);
// Long orderId = jsonObject.getLong("order_no");
// Integer paidMoney = jsonObject.getInt("amount");
// Order order = iOrderService.getOrderById(orderId);
// if (order != null && order.getOrderPayStatus() == OrderCode.ORDER_UNPAID) {
// order.setOrderPayStatus(OrderCode.ORDER_PAID);
// order.setOrderPaidMoney(paidMoney);
// iOrderService.saveOrUpdateOrder(order);
// }
// }
// }
// } catch (Exception e) {
// e.printStackTrace();
// } finally {
// logger.info("Done payOrderSuccess Action!");
// return null;
// }
// }
//
// @RequestMapping(value = "/commentOrder", produces =
// "text/json;charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "评论订单")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String commentOrder(@CurrentUser Object currentUser,
// @RequestParam(value = "orderId") Long orderId, @RequestParam(value =
// "orderScore") Integer orderScore,
// @RequestParam(value = "orderComment") String orderComment) {
// logger.info("Dealing with commentOrder Action...");
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// Assert.notNull(currentUser, "未登录系统");
// Assert.notNull(orderId, "orderDoctorId can not be empty");
// Assert.notNull(orderScore, "orderScore can not be empty");
// Assert.notNull(orderComment, "orderComment can not be empty");
// Long userId;
// if (currentUser instanceof User) {
// userId = ((User) currentUser).getUserId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// Order order = iOrderService.getOrderById(orderId);
// if (order != null) {
// if (order.getOrderStatus() == OrderCode.ORDER_NORMAL_FINISHED
// && order.getOrderPayStatus() == OrderCode.ORDER_PAID) {
// Patient patient = iPatientService.getPatientById(order.getOrderPid());
// User user = iUserService.getUserById(patient.getPatientUserId());
// if (userId != user.getUserId()) {
// throw new Exception("当前用户不能评论该订单");
// }
// order.setOrderScore(orderScore);
// order.setOrderComment(orderComment);
// iOrderService.saveOrUpdateOrder(order);
// // 给医生评分
// Doctor doctor = iDoctorService.getDoctorById(order.getOrderDid());
// if (doctor != null) {
// // 给用户返现并记录医生所得款
// iPayService.refundOrder(order);
// List<Integer> statues = new ArrayList<Integer>();
// statues.add(OrderCode.ORDER_NORMAL_FINISHED);
// List<Integer> pstatues = new ArrayList<Integer>();
// pstatues.add(OrderCode.ORDER_SETTLED);
// pstatues.add(OrderCode.ORDER_REFUNDED);
// List<Order> orders = iOrderService.getOrdersByDoctorId(doctor.getDoctorId(),
// statues, pstatues,
// null, 0, 100, null);
// int score = 0;
// for (Order order1 : orders) {
// if (order1.getOrderScore() != null) {
// score += order1.getOrderScore();
// }
// }
// score += orderScore;
// score = score / (orders.size() + 1);
// doctor.setDoctorScore(score);
// iDoctorService.updateDoctor(doctor);
// }
// } else {
// throw new Exception("订单不可评论");
// }
// }
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done commentOrder Action!");
// return requestManager.printJson(result).toString();
// }
// }
// }
