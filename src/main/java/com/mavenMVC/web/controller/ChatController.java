// package com.mavenMVC.web.controller;
//
/// **
// * Created by lizai on 15/4/29.
// */
//
// import com.mavenMVC.authorization.annotation.CurrentUser;
// import com.mavenMVC.dao.IPatientDao;
// import com.mavenMVC.entity.Doctor;
// import com.mavenMVC.entity.Patient;
// import com.mavenMVC.entity.User;
// import com.mavenMVC.service.IDoctorService;
// import com.mavenMVC.service.IUserService;
// import com.mavenMVC.util.Code;
// import com.mavenMVC.util.HttpRequestUtil;
// import com.mavenMVC.util.RequestManager;
// import com.wordnik.swagger.annotations.ApiImplicitParam;
// import com.wordnik.swagger.annotations.ApiImplicitParams;
// import com.wordnik.swagger.annotations.ApiOperation;
// import net.sf.json.JSONObject;
// import org.apache.log4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.util.Assert;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
//
// import java.util.Calendar;
//
//
/// **
// * Created by lizai on 2014/7/25.
// */
// @Controller
// @RequestMapping("/chat")
// public class ChatController {
//
// protected final Logger logger =
// Logger.getLogger(String.valueOf(ChatController.class));
//
// @Autowired
// private IDoctorService iDoctorService;
//
// @Autowired
// private IPatientDao iPatientDao;
//
// @Autowired
// private IUserService iUserService;
//
// @RequestMapping(value = "/getChatChannel", produces = "text/json;
// charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
//// @Authorization
// @ApiOperation(value = "获取医生对患者视频聊天channel")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"),
// })
// public
// @ResponseBody
// String getChatChannel(@CurrentUser Object currentUser,
// @RequestParam(value = "patientId") Long patientId) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with getChatChannel Action...");
// Assert.notNull(currentUser, "未登录系统");
// if(currentUser instanceof User){
// throw new Exception("当前用户类型错误");
// }
// Patient patient = iPatientDao.getById(patientId);
// User user = iUserService.getUserById(patient.getPatientUserId());
// if(user.getUserInstallationId()!=null){
// String channelId = String.valueOf(Calendar.getInstance().getTimeInMillis());
// JSONObject jo = new JSONObject();
// JSONObject data = new JSONObject();
// if(user.getUserOsType() == 0){
// data.put("action","com.cloud.clinic.push");
// }
// data.put("alert", channelId);
// data.put("doctorName",((Doctor)currentUser).getDoctorName());
// data.put("doctorId",((Doctor) currentUser).getDoctorId());
// data.put("doctorHeadimage",((Doctor) currentUser).getDoctorHeadimage());
// data.put("patientId",patientId);
// data.put("type","video");
// JSONObject where = new JSONObject();
// if(user.getUserOsType()==0){
// where.put("installationId",user.getUserInstallationId());
// }else if(user.getUserOsType()==1){
// where.put("deviceToken",user.getUserInstallationId());
// jo.put("prod", Code.IOS_PROD);
// }
// jo.put("where",where);
// jo.put("data", data);
// HttpRequestUtil.sendPost(Code.MESSAGE_URL,jo.toString(),0);
// result.put("channelId", channelId);
// }else{
// throw new Exception("要推送用户未注册到服务中");
// }
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done getChatChannel Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/hangUpChat", produces = "text/json; charset=utf-8",
// method = {RequestMethod.POST, RequestMethod.GET})
//// @Authorization
// @ApiOperation(value = "挂断通话")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"),
// })
// public
// @ResponseBody
// String hangUpChat(@CurrentUser Object currentUser,
// @RequestParam(value = "patientId") Long patientId,
// @RequestParam(value = "doctorId") Long doctorId) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with hangUpChat Action...");
// Assert.notNull(currentUser, "未登录系统");
// if(currentUser instanceof User){
// Assert.notNull(doctorId, "未指定挂断对象");
// Doctor doctor = iDoctorService.getDoctorById(doctorId);
// if(doctor.getDoctorInstallationId()!=null){
// JSONObject jo = new JSONObject();
// JSONObject data = new JSONObject();
// if(doctor.getDoctorOsType() == 0){
// data.put("action","com.cloud.clinic.push");
// }
// data.put("type","hangup");
// JSONObject where = new JSONObject();
// if(doctor.getDoctorOsType()==0){
// where.put("installationId",doctor.getDoctorInstallationId());
// }else if(doctor.getDoctorOsType()==1){
// where.put("deviceToken",doctor.getDoctorInstallationId());
// jo.put("prod", Code.IOS_PROD);
// }
// jo.put("where",where);
// jo.put("data", data);
// HttpRequestUtil.sendPost(Code.MESSAGE_URL,jo.toString(),0);
// }else{
// throw new Exception("要推送用户未注册到服务中");
// }
// }else if(currentUser instanceof Doctor){
// Assert.notNull(patientId,"未指定挂断对象");
// Patient patient = iPatientDao.getById(patientId);
// User user = iUserService.getUserById(patient.getPatientUserId());
// if(user.getUserInstallationId()!=null){
// JSONObject jo = new JSONObject();
// JSONObject data = new JSONObject();
// if(user.getUserOsType() == 0){
// data.put("action","com.cloud.clinic.push");
// }
// data.put("type","hangup");
// JSONObject where = new JSONObject();
// if(user.getUserOsType()==0){
// where.put("installationId",user.getUserInstallationId());
// }else if(user.getUserOsType()==1){
// where.put("deviceToken",user.getUserInstallationId());
// jo.put("prod", Code.IOS_PROD);
// }
// jo.put("where",where);
// jo.put("data", data);
// HttpRequestUtil.sendPost(Code.MESSAGE_URL, jo.toString(),0);
// }else{
// throw new Exception("要推送用户未注册到服务中");
// }
// }else{
// throw new Exception("当前用户类型错误");
// }
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done hangUpChat Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/installDeviceChat", produces = "text/json;
// charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
//// @Authorization
// @ApiOperation(value = "安装设备installationId到服务中")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"),
// })
// public
// @ResponseBody
// String installDeviceChat(@CurrentUser Object currentUser,
// @RequestParam(value = "installationId") String installationId,
// @RequestParam(value = "osType") Integer osType) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with installDeviceChat Action...");
// Assert.notNull(currentUser, "未登录系统");
// if(currentUser instanceof Doctor){
// if(installationId!=null && !installationId.trim().equals("")){
// if ((((Doctor) currentUser).getDoctorInstallationId() == null) ||
// !(installationId.trim().equals(((Doctor)
// currentUser).getDoctorInstallationId()))){
// ((Doctor) currentUser).setDoctorInstallationId(installationId);
// ((Doctor) currentUser).setDoctorOsType(osType);
// JSONObject jo = new JSONObject();
// if(osType == 0){
// jo.put("deviceType","android");
// jo.put("installationId",installationId);
// }else{
// jo.put("deviceType","ios");
// jo.put("deviceToken",installationId);
// }
// String[] channels = {"public","protected","private"};
// jo.put("channels", channels);
// HttpRequestUtil.sendPost(Code.DEVICE_INSTALL_URL,jo.toString(),0);
// iDoctorService.updateDoctor((Doctor) currentUser);
// }
// }
// }else if(currentUser instanceof User){
// if(installationId!=null && !installationId.trim().equals("")){
// if ((((User) currentUser).getUserInstallationId() == null) ||
// !(installationId.trim().equals(((User)
// currentUser).getUserInstallationId()))){
// ((User) currentUser).setUserInstallationId(installationId);
// ((User) currentUser).setUserOsType(osType);
// JSONObject jo = new JSONObject();
// if(osType == 0){
// jo.put("deviceType","android");
// jo.put("installationId",installationId);
// }else{
// jo.put("deviceType","ios");
// jo.put("deviceToken",installationId);
// }
// String[] channels = {"public","protected","private"};
// jo.put("channels", channels);
// HttpRequestUtil.sendPost(Code.DEVICE_INSTALL_URL, jo.toString(),0);
// iUserService.updateUser((User) currentUser);
// }
// }
// }else{
// throw new Exception("当前用户类型错误");
// }
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done installDeviceChat Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// }
