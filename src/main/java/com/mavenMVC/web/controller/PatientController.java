// package com.mavenMVC.web.controller;
//
// import com.mavenMVC.authorization.annotation.CurrentUser;
// import com.mavenMVC.entity.Doctor;
// import com.mavenMVC.entity.Patient;
// import com.mavenMVC.entity.User;
// import com.mavenMVC.service.IPatientService;
// import com.mavenMVC.util.RequestManager;
// import com.wordnik.swagger.annotations.ApiImplicitParam;
// import com.wordnik.swagger.annotations.ApiImplicitParams;
// import com.wordnik.swagger.annotations.ApiOperation;
// import net.sf.json.JSONArray;
// import net.sf.json.JSONObject;
// import org.apache.log4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.util.Assert;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.multipart.MultipartFile;
//
// import javax.servlet.http.HttpServletRequest;
// import java.io.File;
// import java.util.ArrayList;
// import java.util.Calendar;
// import java.util.List;
//
/// **
// * Created by lizai on 15/6/25.
// */
// @Controller
// @RequestMapping("/patient")
// public class PatientController {
//
// protected final Logger logger =
// Logger.getLogger(String.valueOf(PatientController.class));
//
// @Autowired
// private IPatientService iPatientService;
//
// @Autowired
// private HttpServletRequest request;
//
// @RequestMapping(value = "/getMyPatients", produces = "text/json;
// charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
//// @Authorization
// @ApiOperation(value = "获取当前登录用户（患者）的病人列表")
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
// String getMyPatients(
// @CurrentUser Object currentUser,
// @RequestParam(value = "start") Integer start,
// @RequestParam(value = "offset") Integer offset,
// @RequestParam(value = "receivedIds", required = false) List<Long>
// receivedIds) {
// logger.info("Dealing with getMyPatients Action...");
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// Assert.notNull(currentUser, "未登录系统");
// Assert.notNull(start, "查询起始不能为空");
// Assert.notNull(offset, "查询终止不能为空");
// if (start < 0 || offset <= 0 || start > offset) {
// throw new Exception("查询条件有误");
// }
// Long userId = null,doctorId = null;
// List<Patient> patients = new ArrayList<Patient>();
// if (currentUser instanceof User) {
// userId = ((User) currentUser).getUserId();
// patients = iPatientService.getPatientsByUserId(userId, start, offset,
// receivedIds);
// } else if(currentUser instanceof Doctor){
// doctorId = ((Doctor) currentUser).getDoctorId();
// patients = iPatientService.getPatientsByDoctorId(doctorId, start, offset,
// receivedIds);
// }else {
// throw new Exception("当前用户类型错误");
// }
// result = JSONArray.fromObject(patients);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done getMyPatients Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/addOrUpdatePatient", produces = "text/json;
// charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
//// @Authorization
// @ApiOperation(value = "为当前登录用户（患者）添加或修改一个病人")
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
// String addOrUpdatePatient(
// @CurrentUser User currentUser,
// @RequestParam(value = "patientId") Long patientId,
// @RequestParam(value = "patientName") String patientName,
// @RequestParam(value = "patientSex") Integer patientSex,
// @RequestParam(value = "patientBirthDay") String patientBirthDay,
// @RequestParam(value = "patientCertificateType") Integer
// patientCertificateType,
// @RequestParam(value = "patientCertificateDetail") String
// patientCertificateDetail,
// @RequestParam(value = "patientCellphone") String patientCellphone) {
// logger.info("Dealing with addOrUpdatePatient Action...");
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
//// Assert.notNull(patientName, "patientName can not be empty");
//// Assert.notNull(patientSex, "patientSex can not be empty");
//// Assert.notNull(patientBirthDay, "patientBirthDay can not be empty");
//// Assert.notNull(patientCertificateType, "patientCertificateType can not be
// empty");
//// Assert.notNull(patientCertificateDetail, "patientCertificateDetail can not
// be empty");
//// Assert.notNull(patientCellphone, "patientCellphone can not be empty");
// Assert.notNull(currentUser, "未登录系统");
// Patient patient;
// if (patientId != null) {
// patient = iPatientService.getPatientById(patientId);
// if ((patient == null) || (patient.getPatientUserId() !=
// currentUser.getUserId())) {
// throw new Exception("patientId unvalid");
// }
// } else {
// patient = new Patient();
// patient.setPatientUserId(currentUser.getUserId());
// }
// if ((patientName != null) && (!patientName.trim().equals("")))
// patient.setPatientName(patientName);
// if ((patientSex != null) && (patientSex >= 0))
// patient.setPatientSex(patientSex);
// if ((patientCertificateType != null) && (patientCertificateType > 0))
// patient.setPatientCertificateType(patientCertificateType);
// if ((patientCertificateDetail != null) &&
// (!patientCertificateDetail.trim().equals("")))
// patient.setPatientCertificateDetail(patientCertificateDetail);
// if ((patientCellphone != null) && (!patientCellphone.trim().equals("")))
// patient.setPatientCellphone(patientCellphone);
// if ((patientBirthDay != null) && (!patientBirthDay.trim().equals("")))
// patient.setPatientBirthday(patientBirthDay);
// iPatientService.saveOrUpdatePatient(patient);
// result = JSONObject.fromObject(patient);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done addOrUpdatePatient Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/getPatientInfo", produces = "text/json;
// charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
//// @Authorization
// @ApiOperation(value = "获取病人详细信息")
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
// String getPatientInfo(
// @RequestParam(value = "patientId") Long patientId) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with getPatientInfo Action...");
// Assert.notNull(patientId, "patientId can not be null");
// Patient patient = iPatientService.getPatientById(patientId);
// result = JSONObject.fromObject(patient);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done getPatientInfo Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/updatePatientHeadImage", produces = "text/json;
// charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
// // @Authorization
// @ApiOperation(value = "修改病人头像")
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
// String updatePatientHeadImage(
// @RequestParam(value = "file") MultipartFile file,
// @RequestParam(value = "patientId") Long patientId) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with updatePatientHeadImage Action...");
// Assert.notNull(file, "头像图片不能为空");
// Assert.notNull(patientId, "未登录系统");
// logger.info(request);
// String path = request.getSession().getServletContext().getRealPath("");
// path = path.substring(0, path.lastIndexOf("/")) + "/files/" + "patient" +
// patientId + "/";
// logger.info("@@@@ " + path);
// new File(path).mkdir();
// // 得到上传的文件的文件名
// String filename = "headImage_" + Calendar.getInstance().getTimeInMillis() +
// ".png";
// path += filename;
// file.transferTo(new File(path));
// String fileNameUrl = request.getScheme() + "://" + request.getServerName() +
// ":"
// + request.getServerPort() + "/files/" + "patient" + patientId + "/" +
// filename;
// Patient patient = iPatientService.getPatientById(patientId);
// patient.setPatientHeadimage(fileNameUrl);
// iPatientService.saveOrUpdatePatient(patient);
// result = JSONObject.fromObject(patient);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done updatePatientHeadImage Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// }
//
