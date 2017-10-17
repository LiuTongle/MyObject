// package com.mavenMVC.web.controller;
//
// import com.mavenMVC.authorization.annotation.CurrentUser;
// import com.mavenMVC.entity.*;
// import com.mavenMVC.service.*;
// import com.mavenMVC.util.*;
// import com.pingplusplus.model.Event;
// import com.pingplusplus.model.EventData;
// import com.pingplusplus.model.PingppObject;
// import com.pingplusplus.model.Webhooks;
// import com.wordnik.swagger.annotations.ApiImplicitParam;
// import com.wordnik.swagger.annotations.ApiImplicitParams;
// import com.wordnik.swagger.annotations.ApiOperation;
// import com.wordnik.swagger.annotations.ApiParam;
// import net.sf.json.JSONArray;
// import net.sf.json.JSONObject;
// import org.apache.log4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.util.Assert;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;
//
// import javax.servlet.http.HttpServletRequest;
// import java.io.File;
// import java.util.ArrayList;
// import java.util.Calendar;
// import java.util.List;
//
/// *** Created by lizai on 15/6/25. */
//
// @Controller
// @RequestMapping("/doctor")
// public class DoctorController {
//
// protected final Logger logger =
// Logger.getLogger(String.valueOf(DoctorController.class));
//
// @Autowired
// private IDoctorService iDoctorService;
//
// @Autowired
// private IVerifyCodeService iVerifyCodeService;
//
// @Autowired
// private IHospitalService iHospitalService;
//
// @Autowired
// private IDepartmentService iDepartmentService;
//
// @Autowired
// private ITitleService iTitleService;
//
// @Autowired
// private IOrderService iOrderService;
//
// @Autowired
// private IPatientService iPatientService;
//
// @Autowired
// private IScheduleService iScheduleService;
//
// @Autowired
// private IPayService iPayService;
//
// @Autowired
// private ISeeCashService iSeeCashService;
//
// @Autowired
// private HttpServletRequest request;
//
// @RequestMapping(value = "/login", produces = "text/json; charset=utf-8",
// method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "用户登录")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String login(@RequestParam(value = "cellphone", required
// = false) String cellphone,
// @RequestParam(value = "password", required = false) String password) {
// logger.info("Dealing with login Action...");
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// Assert.notNull(cellphone, "手机号不能为空");
// Assert.notNull(password, "密码不能为空");
// Doctor doctorEntity = iDoctorService.loginValid(cellphone, password);
// Assert.notNull(doctorEntity, "该手机号还未注册或密码有误");
// if (doctorEntity.getDoctorImToken() == null ||
// doctorEntity.getDoctorImToken().trim().equals("")) {
// String jsonString =
// HttpRequestUtil.neteaseIm(doctorEntity.getDoctorCellphone());
// JSONObject jo = JSONObject.fromObject(jsonString);
// if ((jo.get("info") != null) &&
// (JSONObject.fromObject(jo.get("info")).get("token") != null)) {
// String connectionToken =
// JSONObject.fromObject(jo.get("info")).get("token").toString();
// doctorEntity.setDoctorImToken(connectionToken);
// iDoctorService.updateDoctor(doctorEntity);
// }
// }
// result = JSONObject.fromObject(doctorEntity);
// fillInHospital(result, doctorEntity);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// e.printStackTrace();
// } finally {
// logger.info("Done login Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/logout", produces = "text/json; charset=utf-8",
// method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "退出登录")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String logout(@CurrentUser Doctor currentUser) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with logout Action...");
// Assert.notNull(currentUser, "未登录系统");
// if (currentUser.getDoctorLogin() == 1) {
// currentUser.setDoctorLogin(0);
// iDoctorService.updateDoctor(currentUser);
// } else {
// throw new Exception("该用户未登录");
// }
// result = JSONObject.fromObject(currentUser);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done logout Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/register", produces = "text/json; charset=utf-8",
// method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "新用户注册")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String register(@RequestParam(value = "cellphone",
// required = false) String cellphone,
// @RequestParam(value = "password", required = false) String password,
// @RequestParam(value = "verifyCode", required = false) Integer verifyCode) {
// logger.info("Dealing with register Action...");
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// Assert.notNull(cellphone, "cellphone can not be empty");
// Assert.notNull(password, "password can not be empty");
// if (iDoctorService.ifDoctorCellphoneRegisted(cellphone)) {
// requestManager.putErrorMessage("该手机号已注册");
// } else {
// Doctor doctor = iDoctorService.registerDoctor(null, password, cellphone);
// String param = "cellphone=" + cellphone + "&password=" + password;
// HttpRequestUtil.sendGet("http://122.114.52.243:8088/publicCAS/user/doctor/sync",
// param);
// result = JSONObject.fromObject(doctor);
// fillInHospital(result, doctor);
// }
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done register Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/resetPassword", produces = "text/json;
// charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
//// @Authorization
// @ApiOperation(value = "重置密码")
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
// String resetPassword(
// @RequestParam(value = "cellphone", required = false) String cellphone,
// @RequestParam(value = "password", required = false) String password,
// @RequestParam(value = "verifyCode", required = false) Integer verifyCode) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with resetPassword Action...");
// Assert.notNull(cellphone, "cellphone can not be empty");
// Assert.notNull(password, "password can not be empty");
// if (iDoctorService.ifDoctorCellphoneRegisted(cellphone)) {
// if(iVerifyCodeService.useVerifyCode(cellphone,verifyCode,1)){
// if (iDoctorService.resetPassword(cellphone, password)) {
// } else {
// requestManager.putErrorMessage("修改密码失败");
// }
// }else{
// requestManager.putErrorMessage("验证码无效或过期(两分钟)");
// }
// } else {
// requestManager.putErrorMessage("该手机号未注册过，请先完成注册");
// }
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// result = requestManager.printJson(null);
// } finally {
// logger.info("Done resetPassword Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/getDoctorInfo"
// ,produces="text/json;charset=utf-8", method = {RequestMethod.POST,
// RequestMethod.GET})
// // @Authorization
// @ApiOperation(value="获取用户信息")@ApiImplicitParams({@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="sig",value="sig",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="requestTime",value="requestTime",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="tokenType",value="tokenType",required=true,dataType="string",paramType="header"),})public
// @ResponseBody String getDoctorInfo(
// @CurrentUser Doctor currentDoctor) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with getDoctorInfo Action...");
// Assert.notNull(currentDoctor, "未登录系统");
// result = JSONObject.fromObject(currentDoctor);
// fillInHospital(result, currentDoctor);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done getDoctorInfo Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/updateDoctorInfo"
// ,produces="text/json;charset=utf-8", method = {RequestMethod.POST,
// RequestMethod.GET})
// // @Authorization
// @ApiOperation(value="修改用户信息")@ApiImplicitParams({@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="sig",value="sig",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="requestTime",value="requestTime",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="tokenType",value="tokenType",required=true,dataType="string",paramType="header"),})public
// @ResponseBody String updateDoctorInfo(
// @CurrentUser Doctor currentDoctor, @RequestParam(value = "doctorName",
// required = false) String doctorName,
// @RequestParam(value = "doctorSex", required = false) Integer doctorSex,
// @RequestParam(value = "doctorHospital", required = false) String
// doctorHospital,
// @RequestParam(value = "doctorDepartment", required = false) String
// doctorDepartment,
// @RequestParam(value = "doctorTitle", required = false) String doctorTitle,
// @RequestParam(value = "doctorIntroduction", required = false) String
// doctorIntroduction,
// @RequestParam(value = "doctorPrice", required = false) Integer doctorPrice) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with updateDoctorInfo Action...");
// Assert.notNull(currentDoctor, "未登录系统");
// if ((doctorName != null) && (!doctorName.trim().equals(""))) {
// currentDoctor.setDoctorName(doctorName);
// }
// if (doctorSex > -1 && doctorSex < 2) {
// currentDoctor.setDoctorSex(doctorSex);
// }
// if (doctorIntroduction != null) {
// currentDoctor.setDoctorIntroduction(doctorIntroduction);
// }
// if ((doctorHospital != null) && (!doctorHospital.trim().equals(""))) {
// Hospital hospital = iHospitalService.searchHospitalByName(doctorHospital);
// if (hospital != null) {
// currentDoctor.setDoctorHospital(hospital.getHospitalId());
// } else {
// throw new Exception("医院输入有误");
// }
// }
// if ((doctorDepartment != null) && (!doctorDepartment.trim().equals(""))) {
// Department department =
// iDepartmentService.searchDepartmentByName(doctorDepartment);
// if (department != null) {
// currentDoctor.setDoctorDepartment(department.getDepartmentId());
// } else {
// throw new Exception("科室输入有误");
// }
// }
// if ((doctorTitle != null) && (!doctorTitle.trim().equals(""))) {
// Title title = iTitleService.searchTitleByName(doctorTitle);
// if (title != null) {
// currentDoctor.setDoctorTitle(title.getTitleId());
// } else {
// throw new Exception("职称输入有误");
// }
// }
// if ((doctorPrice != null) && (doctorPrice > 0)) {
// currentDoctor.setDoctorPrice(doctorPrice);
// }
// iDoctorService.updateDoctor(currentDoctor);
// result = JSONObject.fromObject(currentDoctor);
// fillInHospital(result, currentDoctor);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done updateDoctorInfo Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/updateDoctorCellphone"
// ,produces="text/json;charset=utf-8", method = {RequestMethod.POST,
// RequestMethod.GET})
// // @Authorization
// @ApiOperation(value="修改用户手机号")@ApiImplicitParams({@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="sig",value="sig",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="requestTime",value="requestTime",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="tokenType",value="tokenType",required=true,dataType="string",paramType="header"),})public
// @ResponseBody String updateDoctorCellphone(
// @CurrentUser Doctor currentDoctor, @RequestParam(value = "verifyCode",
// required = false) Integer verifyCode,
// @RequestParam(value = "cellphone", required = false) String cellphone) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with updateDoctorCellphone Action...");
// // Assert.notNull(verifyCode,"验证码不能为空");
// Assert.notNull(currentDoctor, "未登录系统");
// Assert.notNull(cellphone, "新手机号不能为空");
// if (iVerifyCodeService.useVerifyCode(cellphone, verifyCode, 1)) {
// currentDoctor.setDoctorCellphone(cellphone);
// iDoctorService.updateDoctor(currentDoctor);
// } else {
// requestManager.putErrorMessage("验证码无效或过期(两分钟)");
// }
// result = JSONObject.fromObject(currentDoctor);
// fillInHospital(result, currentDoctor);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done updateDoctorCellphone Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/updateDoctorHeadImage"
// ,produces="text/json;charset=utf-8", method = {RequestMethod.POST,
// RequestMethod.GET})
// // @Authorization
// @ApiOperation(value="修改用户头像")@ApiImplicitParams({@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="sig",value="sig",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="requestTime",value="requestTime",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="tokenType",value="tokenType",required=true,dataType="string",paramType="header"),})public
// @ResponseBody String updateDoctorHeadImage(
// @CurrentUser Doctor currentDoctor, @RequestParam(value = "file", required =
// false) MultipartFile file) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with updateDoctorHeadImage Action...");
// Assert.notNull(file, "头像图片不能为空");
// Assert.notNull(currentDoctor, "未登录系统");
// logger.info(request);
// String path = request.getSession().getServletContext().getRealPath("");
// path = path.substring(0, path.lastIndexOf("/")) + "/files/" +
// currentDoctor.getDoctorToken() + "/";
// logger.info("@@@@ " + path);
// new File(path).mkdir();
// // 得到上传的文件的文件名
// String filename = "headImage_" + Calendar.getInstance().getTimeInMillis() +
// ".png";
// path += filename;
// file.transferTo(new File(path));
// String fileNameUrl = Code.SERVER_ADDRESS + "/files/" +
// currentDoctor.getDoctorToken() + "/" + filename;
// currentDoctor.setDoctorHeadimage(fileNameUrl);
// iDoctorService.updateDoctor(currentDoctor);
// result = JSONObject.fromObject(currentDoctor);
// fillInHospital(result, currentDoctor);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done updateDoctorHeadImage Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/verifyDoctor"
// ,produces="text/json;charset=utf-8", method = {RequestMethod.POST,
// RequestMethod.GET})
// // @Authorization
// @ApiOperation(value="认证医生")@ApiImplicitParams({@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="sig",value="sig",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="requestTime",value="requestTime",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="tokenType",value="tokenType",required=true,dataType="string",paramType="header"),})public
// @ResponseBody String verifyDoctor(
// @CurrentUser Doctor currentDoctor, @RequestParam(value = "idNum", required =
// false) String idNum,
// @RequestParam(value = "file", required = false) MultipartFile[] files) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with verifyDoctor Action...");
// Assert.notNull(idNum, "身份证号码不能为空");
// Assert.notNull(files, "认证图片不能为空");
// Assert.notNull(currentDoctor, "未登录系统");
// if (currentDoctor.getDoctorStatus() == DoctorCode.DOCTOR_VERIFIED) {
// throw new Exception("该医生已经认证");
// }
// currentDoctor.setDoctorIDNum(idNum);
// String path = request.getSession().getServletContext().getRealPath("");
// path = path.substring(0, path.lastIndexOf("/")) + "/files/" +
// currentDoctor.getDoctorToken() + "/";
// new File(path).mkdir();
// int i = 0;
// String fileUrl = "";
// for (MultipartFile file : files) {
// // 得到上传的文件的文件名
// String filename = "verify_" + i + "_" +
// Calendar.getInstance().getTimeInMillis() + ".png";
// path += filename;
// file.transferTo(new File(path));
// String fileNameUrl = request.getScheme() + "://" + request.getServerName() +
// ":"
// + request.getServerPort() + "/files/" + currentDoctor.getDoctorToken() + "/"
// + filename;
// i++;
// fileUrl += fileNameUrl + ",";
// }
// // currentDoctor.setDoctorCertificateType(2);
// currentDoctor.setDoctorCertificateDetail(fileUrl.toString());
// iDoctorService.updateDoctor(currentDoctor);
// result = JSONObject.fromObject(currentDoctor);
// fillInHospital(result, currentDoctor);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done verifyDoctor Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/queryDoctors"
// ,produces="text/json;charset=utf-8", method = {RequestMethod.POST,
// RequestMethod.GET})
// // @Authorization
// @ApiOperation(value="搜索医生信息")@ApiImplicitParams({@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="sig",value="sig",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="requestTime",value="requestTime",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="tokenType",value="tokenType",required=true,dataType="string",paramType="header"),})public
// @ResponseBody String queryDoctors(
// @CurrentUser Object currentDoctor, @RequestParam(value = "query") String
// query,
// @RequestParam(value = "start") Integer start, @RequestParam(value = "offset")
// Integer offset,
// @RequestParam(value = "receivedIds", required = false) List<Long>
// receivedIds) {
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// logger.info("Dealing with queryDoctors Action...");
// Assert.notNull(currentDoctor, "未登录系统");
// List<Doctor> doctorList = iDoctorService.searchDoctors(query, start, offset,
// receivedIds);
// List<JSONObject> data = new ArrayList<JSONObject>();
// List<Long> doctorIds = null;
// if (currentDoctor instanceof User) {
// doctorIds = iDoctorService.getMyCollectionDoctorIds(((User)
// currentDoctor).getUserId());
// }
// if (doctorList != null && doctorList.size() > 0) {
// for (Doctor doctor : doctorList) {
// JSONObject jo = doctor.turnJson();
// fillInHospital(jo, doctor);
// if (doctorIds != null && doctorIds.contains(doctor.getDoctorId())) {
// jo.put("doctorCollected", true);
// } else {
// jo.put("doctorCollected", false);
// }
// data.add(jo);
// }
// }
// result = JSONArray.fromObject(data);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done queryDoctors Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/getDoctors", produces = "text/json; charset=utf-8",
// method = {RequestMethod.POST, RequestMethod.GET})
//// @Authorization
// @ApiOperation(value = "云诊所首页获取医生信息")
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
// String getDoctors(
//// @CurrentUser Object currentDoctor,
// @ApiParam(name = "orderType", value = "0为综合排序，1为评分排序")
// @RequestParam(value = "orderType") Integer orderType,
// @RequestParam(value = "start") Integer start,
// @RequestParam(value = "offset") Integer offset,
// @RequestParam(value = "receivedIds", required = false) List<Long>
// receivedIds) {
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// logger.info("Dealing with getDoctors Action...");
//// Assert.notNull(currentDoctor, "未登录系统");
// List<Doctor> doctorList = iDoctorService.getDoctorsByOrderType(orderType,
// start, offset, receivedIds);
// List<JSONObject> data = new ArrayList<JSONObject>();
//// List<Long> doctorIds = null;
//// if (currentDoctor instanceof User) {
//// doctorIds = iDoctorService.getMyCollectionDoctorIds(((User)
// currentDoctor).getUserId());
//// }
// if (doctorList != null && doctorList.size() > 0) {
// for (Doctor doctor : doctorList) {
// JSONObject jo = doctor.turnJson();
// fillInHospital(jo, doctor);
//// if (doctorIds != null && doctorIds.contains(doctor.getDoctorId())) {
//// jo.put("doctorCollected", true);
//// } else {
//// jo.put("doctorCollected", false);
//// }
// data.add(jo);
// }
// }
// result = JSONArray.fromObject(data);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// e.printStackTrace();
// } finally {
// logger.info("Done getDoctors Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/collectDoctor"
// ,produces="text/json;charset=utf-8", method = {RequestMethod.POST,
// RequestMethod.GET})
// // @Authorization
// @ApiOperation(value="收藏医生")@ApiImplicitParams({@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="sig",value="sig",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="requestTime",value="requestTime",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="tokenType",value="tokenType",required=true,dataType="string",paramType="header"),})public
// @ResponseBody String collectDoctor(
// @CurrentUser Object currentUser, @RequestParam(value = "doctorId") Long
// doctorId) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with collectDoctor Action...");
// Assert.notNull(currentUser, "未登录系统");
// Long userId;
// if (currentUser instanceof User) {
// userId = ((User) currentUser).getUserId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// iDoctorService.collectDoctor(userId, doctorId);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done collectDoctor Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/getMyDoctors"
// ,produces="text/json;charset=utf-8", method = {RequestMethod.POST,
// RequestMethod.GET})
// // @Authorization
// @ApiOperation(value="获取我的收藏医生")@ApiImplicitParams({@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="sig",value="sig",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="requestTime",value="requestTime",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="tokenType",value="tokenType",required=true,dataType="string",paramType="header"),})public
// @ResponseBody String getMyDoctors(
// @CurrentUser Object currentUser, @RequestParam(value = "start") Integer
// start,
// @RequestParam(value = "offset") Integer offset,
// @RequestParam(value = "receivedIds", required = false) List<Long>
// receivedIds) {
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// logger.info("Dealing with getMyDoctors Action...");
// Assert.notNull(currentUser, "未登录系统");
// Long userId;
// if (currentUser instanceof User) {
// userId = ((User) currentUser).getUserId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// List<Doctor> doctors = iDoctorService.getMyOrderedDoctors(userId, start,
// offset, receivedIds);
// List<Long> doctorIds = iDoctorService.getMyCollectionDoctorIds(userId);
// List<JSONObject> data = new ArrayList<JSONObject>();
// if (doctors != null && doctors.size() > 0) {
// for (Doctor doctor : doctors) {
// JSONObject jo = doctor.turnJson();
// fillInHospital(jo, doctor);
// if (doctorIds != null && doctorIds.contains(doctor.getDoctorId())) {
// jo.put("doctorCollected", true);
// } else {
// jo.put("doctorCollected", false);
// }
// data.add(jo);
// }
// }
// result = JSONArray.fromObject(data);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// e.printStackTrace();
// } finally {
// logger.info("Done getMyDoctors Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/getDoctorComments", produces = "text/json;
// charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
//// @Authorization
// @ApiOperation(value = "获取医生评论")
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
// String getDoctorComments(@CurrentUser Object currentUser,
// @RequestParam(value = "doctorId") Long doctorId,
// @RequestParam(value = "start") Integer start,
// @RequestParam(value = "offset") Integer offset,
// @RequestParam(value = "receivedIds", required = false) List<Long>
// receivedIds) {
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// logger.info("Dealing with getDoctorComments Action...");
// Assert.notNull(currentUser, "未登录系统");
// Assert.notNull(doctorId, "doctorId不能为空");
//// Long userId;
//// if (currentUser instanceof User) {
//// userId = ((User) currentUser).getUserId();
//// } else {
//// throw new Exception("当前用户类型错误");
//// }
// List<Integer> statuses = new ArrayList<Integer>();
// List<Integer> payStatues = new ArrayList<Integer>();
// statuses.add(OrderCode.ORDER_NORMAL_FINISHED);
// payStatues.add(OrderCode.ORDER_REFUNDED);
// payStatues.add(OrderCode.ORDER_SETTLED);
//// List<Order> orders = iOrderService.getOrdersByDoctorId(doctorId, statuses,
// payStatues, start, offset, receivedIds);
// List<Order> orders = iOrderService.getCommentedOrdersByDoctorId(doctorId,
// start, offset, receivedIds);
// System.out.println(orders.size());
//// List<Order> dataOrders = new ArrayList<Order>();
//// for (Order order : orders) {
//// if (order.getOrderComment() != null &&
// !order.getOrderComment().trim().equals("")) {
//// dataOrders.add(order);
//// }
//// }
// System.out.println(orders.size());
// List<JSONObject> data = new ArrayList<JSONObject>();
// if (orders != null && orders.size() > 0) {
// for (Order order : orders) {
// JSONObject jo = order.commentJSON();
// Patient patient = iPatientService.getPatientById(order.getOrderPid());
// jo.put("patientCellphone", patient.getPatientCellphone());
// jo.put("patientName", patient.getPatientName());
// data.add(jo);
// }
// }
// result = JSONArray.fromObject(data);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done getDoctorComments Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/addOrUpdateDoctorSchedule", produces = "text/json;
// charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
//// @Authorization
// @ApiOperation(value = "添加或修改医生日程")
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
// String addOrUpdateDoctorSchedule(@CurrentUser Object currentUser,
// @RequestParam(value = "scheduleId",required = false) Long scheduleId,
// @RequestParam(value = "scheduleTitle") String scheduleTitle,
// @RequestParam(value = "scheduleDay") String scheduleDay,
// @RequestParam(value = "scheduleStartTime") String scheduleStartTime,
// @RequestParam(value = "scheduleEndTime") String scheduleEndTime,
// @RequestParam(value = "scheduleAlert") boolean scheduleAlert) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with addOrUpdateDoctorSchedule Action...");
// Long doctorId;
// Assert.notNull(currentUser, "未登录系统");
// if (currentUser instanceof Doctor) {
// doctorId = ((Doctor) currentUser).getDoctorId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// Schedule schedule = null;
// if(scheduleId != null){
// schedule = iScheduleService.getSchedulesById(scheduleId);
// if(schedule == null){
// schedule = new Schedule();
// }
// }else{
// schedule = new Schedule();
// }
// schedule.setScheduleDid(doctorId);
// schedule.setScheduleAlert(scheduleAlert);
// schedule.setScheduleDay(scheduleDay);
// schedule.setScheduleTitle(scheduleTitle);
// schedule.setScheduleStartTime(scheduleStartTime);
// schedule.setScheduleEndTime(scheduleEndTime);
// iScheduleService.saveOrUpdateSchedule(schedule);
// result = JSONObject.fromObject(schedule);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done addOrUpdateDoctorSchedule Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/deleteDoctorSchedule"
// ,produces="text/json;charset=utf-8", method = {RequestMethod.POST,
// RequestMethod.GET})
// // @Authorization
// @ApiOperation(value="删除医生日程")@ApiImplicitParams({@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="sig",value="sig",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="requestTime",value="requestTime",required=true,dataType="string",paramType="header"),@ApiImplicitParam(name="tokenType",value="tokenType",required=true,dataType="string",paramType="header"),})public
// @ResponseBody String deleteDoctorSchedule(
// @CurrentUser Object currentUser, @RequestParam(value = "scheduleId") Long
// scheduleId) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with deleteDoctorSchedule Action...");
// Long doctorId;
// Assert.notNull(currentUser, "未登录系统");
// if (currentUser instanceof Doctor) {
// doctorId = ((Doctor) currentUser).getDoctorId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// Schedule schedule = iScheduleService.getSchedulesById(scheduleId);
// if (schedule.getScheduleDid() != doctorId) {
// throw new Exception("当前用户无权删除该日程");
// }
// iScheduleService.deleteSchedules(schedule);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done deleteDoctorSchedule Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/switchDoctor", produces =
// "text/json;charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "是否接单开关")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String switchDoctor(@CurrentUser Object currentUser,
// @RequestParam(value = "switchDoctor") boolean switchDoctor) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with switchDoctor Action...");
// Long doctorId;
// Assert.notNull(currentUser, "未登录系统");
// if (currentUser instanceof Doctor) {
// doctorId = ((Doctor) currentUser).getDoctorId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// ((Doctor) currentUser).setDoctorSwitch(switchDoctor);
// iDoctorService.updateDoctor((Doctor) currentUser);
// result = JSONObject.fromObject(currentUser);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done switchDoctor Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/getDoctorSchedules", produces =
// "text/json;charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "获取医生日程")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String getDoctorSchedules(@CurrentUser Object
// currentUser,
// @RequestParam(value = "scheduleDay") String scheduleDay) {
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// logger.info("Dealing with getDoctorSchedules Action...");
// Long doctorId;
// Assert.notNull(currentUser, "未登录系统");
// if (currentUser instanceof Doctor) {
// doctorId = ((Doctor) currentUser).getDoctorId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// List<Schedule> schedules =
// iScheduleService.getSchedulesByDoctorAndDay(doctorId, scheduleDay);
// result = JSONArray.fromObject(schedules);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done getDoctorSchedules Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/withDrawCash", produces =
// "text/json;charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "医生提现")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String withDrawCash(@CurrentUser Object currentUser,
// @ApiParam(name = "amount", value = "提现金额") @RequestParam(value = "amount")
// Integer amount,
// @ApiParam(name = "channel", value =
// "提现渠道wx微信(需填微信相关参数)或unionpay银联(需填银行卡相关参数)") @RequestParam(value = "channel")
// String channel,
// @ApiParam(name = "bankNo", value = "银行卡号") @RequestParam(value = "bankNo",
// required = false) String bankNo,
// @ApiParam(name = "bankUserName", value = "银行卡持有人姓名") @RequestParam(value =
// "bankUserName", required = false) String bankUserName,
// @ApiParam(name = "openBank", value = "银行卡所属行名称") @RequestParam(value =
// "openBank", required = false) String openBank,
// @ApiParam(name = "bankProv", value = "银行卡开户行所在省") @RequestParam(value =
// "bankProv", required = false) String bankProv,
// @ApiParam(name = "bankCity", value = "银行卡开户行所在市") @RequestParam(value =
// "bankCity", required = false) String bankCity,
// @ApiParam(name = "wxUserOpenId", value = "微信用户openId") @RequestParam(value =
// "wxUserOpenId", required = false) String wxUserOpenId,
// @ApiParam(name = "wxUserName", value = "微信用户名称(非必填)") @RequestParam(value =
// "wxUserName", required = false) String wxUserName) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with withDrawCash Action...");
// Assert.notNull(currentUser, "未登录系统");
// if (currentUser instanceof User) {
// throw new Exception("当前用户类型错误");
// }
// String exceptionDetail = iPayService.transferOrder(((Doctor) currentUser),
// amount, channel, bankNo,
// bankUserName, openBank, bankProv, bankCity, wxUserOpenId, wxUserName);
// if (exceptionDetail != null) {
// throw new Exception(exceptionDetail);
// }
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done withDrawCash Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/transferOrderSuccess", produces =
// "text/json;charset=utf-8", method = {
// RequestMethod.POST, RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "医生提现成功")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String transferOrderSuccess(@RequestBody String Webhook)
// {
// logger.info("Done transferOrderSuccess Action!");
// try {
// Event event = Webhooks.eventParse(Webhook);
// if (event != null) {
// if ("transfer.succeeded".equals(event.getType())) {
// EventData eventData = event.getData();
// PingppObject transferRsObj = eventData.getObject();
// String objectString = transferRsObj.toString();
// logger.info(objectString);
// JSONObject jsonObject = JSONObject.fromObject(objectString);
// String seeCashIdT = jsonObject.getString("order_no");
// Long seeCashId = Long.valueOf(seeCashIdT.substring(8, seeCashIdT.length()));
// SeeCash seeCash = iSeeCashService.getSeeCashById(seeCashId);
// seeCash.setSeeCashStatus(PayCode.SUCCESS);
// iSeeCashService.saveOrUpdateSeeCash(seeCash);
// Integer paidMoney = jsonObject.getInt("amount");
// Doctor doctor = iDoctorService.getDoctorById(seeCash.getDoctorId());
// if (doctor != null) {
// doctor.setDoctorMoney(doctor.getDoctorMoney() - paidMoney);
// iDoctorService.updateDoctor(doctor);
// }
// }
// }
// } catch (Exception e) {
// e.printStackTrace();
// } finally {
// logger.info("Done transferOrderSuccess Action!");
// return null;
// }
// }
//
// @RequestMapping(value = "/getDoctorSeeCashes", produces =
// "text/json;charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "获取医生提现列表")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String getDoctorSeeCashes(@CurrentUser Object
// currentDoctor,
// @RequestParam(value = "start") Integer start, @RequestParam(value = "offset")
// Integer offset,
// @RequestParam(value = "receivedIds", required = false) List<Long>
// receivedIds) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with getDoctorSeeCashes Action...");
// Assert.notNull(currentDoctor, "未登录系统");
// Long doctorId = null;
// if (currentDoctor instanceof Doctor) {
// doctorId = ((Doctor) currentDoctor).getDoctorId();
// }
// List<SeeCash> seeCashs = iSeeCashService.getSeeCashByDoctorId(doctorId,
// start, offset, receivedIds);
// List<Integer> statuses = new ArrayList<Integer>();
// List<Integer> payStatues = new ArrayList<Integer>();
// statuses.add(OrderCode.ORDER_NORMAL_FINISHED);
// payStatues.add(OrderCode.ORDER_REFUNDED);
// payStatues.add(OrderCode.ORDER_SETTLED);
// List<Order> orders = iOrderService.getOrdersByDoctorId(doctorId, statuses,
// payStatues, null, start, offset,
// receivedIds);
// List<JSONObject> orderMoneys = new ArrayList<JSONObject>();
// for (Order order : orders) {
// if (order.getOrderSettledMoney() != null && order.getOrderSettledMoney() > 0)
// {
// orderMoneys.add(order.moneyJSON());
// }
// }
// Integer sumOrderMoney = iOrderService.getTotleOrdersMoneyByDoctorId(doctorId,
// statuses, payStatues);
// Integer sumSeeCashMoney =
// iSeeCashService.getTotleSeeCashMoneyByDoctorId(doctorId);
// result.put("seeCashs", seeCashs);
// result.put("orders", orderMoneys);
// result.put("sumOrderMoney", sumOrderMoney);
// result.put("sumSeeCashMoney", sumSeeCashMoney);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// e.printStackTrace();
// } finally {
// logger.info("Done getDoctorSeeCashes Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// private void fillInHospital(JSONObject jo, Doctor doctor) {
// if (doctor.getDoctorHospital() != null) {
// Hospital hospital =
// iHospitalService.getHospitalById(doctor.getDoctorHospital());
// if (hospital != null) {
// jo.put("doctorHospitalName", hospital.getHospitalName());
// }
// }
// if (doctor.getDoctorDepartment() != null) {
// Department department =
// iDepartmentService.getDepartmentById(doctor.getDoctorDepartment());
// if (department != null) {
// jo.put("doctorDepartmentName", department.getDepartmentName());
// }
// }
// if (doctor.getDoctorTitle() != null) {
// Title title = iTitleService.getTitleById(doctor.getDoctorTitle());
// if (title != null) {
// jo.put("doctorTitleName", title.getTitleName());
// }
// }
// }
//
// }
