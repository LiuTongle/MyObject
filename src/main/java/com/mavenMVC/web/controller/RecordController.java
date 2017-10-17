// package com.mavenMVC.web.controller;
//
// import java.io.File;
// import java.util.Calendar;
// import java.util.List;
//
// import javax.servlet.http.HttpServletRequest;
//
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
// import com.mavenMVC.entity.Patient;
// import com.mavenMVC.entity.Record;
// import com.mavenMVC.service.IPatientService;
// import com.mavenMVC.service.IRecordService;
// import com.mavenMVC.util.Code;
// import com.mavenMVC.util.RequestManager;
// import com.wordnik.swagger.annotations.ApiImplicitParam;
// import com.wordnik.swagger.annotations.ApiImplicitParams;
// import com.wordnik.swagger.annotations.ApiOperation;
//
// import net.sf.json.JSONArray;
// import net.sf.json.JSONObject;
//
/// *** Created by lizai on 15/6/25. */
//
// @Controller
// @RequestMapping("/record")
// public class RecordController {
//
// protected final Logger logger =
// Logger.getLogger(String.valueOf(RecordController.class));
//
// @Autowired
// private IPatientService iPatientService;
//
// @Autowired
// private IRecordService iRecordService;
//
// @Autowired
// private HttpServletRequest request;
//
// @RequestMapping(value = "/getPatientRecordById", produces =
// "text/json;charset=utf-8", method = {
// RequestMethod.POST, RequestMethod.GET })
//
// // @Authorization
// @ApiOperation(value = "获取病人所有病历信息")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String getPatientRecordById(@RequestParam(value =
// "recordId") Long recordId) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with getPatientRecordById Action...");
// Assert.notNull(recordId, "patientId can not be null");
// Record record = iRecordService.getRecordById(recordId);
// result = JSONObject.fromObject(record);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done getPatientRecordById Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/getPatientRecords", produces =
// "text/json;charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "获取病人所有病历信息")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String getPatientRecords(@RequestParam(value =
// "patientId") Long patientId,
// @RequestParam(value = "recordType", required = false) Integer recordType) {
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// logger.info("Dealing with getPatientRecords Action...");
// Assert.notNull(patientId, "patientId can not be null");
// if (recordType == null) {
// recordType = 0;
// }
// List<Record> records = iRecordService.getAllRecordsByPatientId(patientId,
// recordType);
// result = JSONArray.fromObject(records);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done getPatientRecords Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/addPatientRecord", produces =
// "text/json;charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "为病人添加病历")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String addPatientRecord(@RequestParam(value =
// "recordId", required = false) Long recordId,
// @RequestParam(value = "patientId", required = false) Long patientId,
// @RequestParam(value = "recordDiagnosis", required = false) String
// recordDiagnosis,
// @RequestParam(value = "recordName", required = false) String recordName,
// @RequestParam(value = "recordDescription", required = false) String
// recordDescription,
// @RequestParam(value = "fileUrls", required = false) String fileUrls,
// @RequestParam(value = "recordType", required = false) Integer recordType) {
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// logger.info("Dealing with addPatientRecord Action...");
// Assert.notNull(fileUrls, "病历图片不能为空");
// Assert.notNull(patientId, "patientId不能为空");
// Assert.notNull(recordDiagnosis, "recordDiagnosis不能为空");
// Assert.notNull(recordName, "recordName不能为空");
// Assert.notNull(recordDescription, "recordDescription不能为空");
// Patient patient = iPatientService.getPatientById(patientId);
// Assert.notNull(patient, "patientId有误");
// if (recordType == null) {
// recordType = 0;
// }
// Record record;
// if (recordId != null) {
// record = iRecordService.getRecordById(recordId);
// } else {
// record = new Record();
// }
// record.setRecordPatientId(patientId);
// record.setRecordDiagnosis(recordDiagnosis);
// record.setRecordName(recordName);
// record.setRecordDescription(recordDescription);
// record.setRecordImage(fileUrls);
// record.setRecordType(recordType);
// record.setRecordStatus(0);
// iRecordService.saveOrUpdateRecord(record);
// List<Record> records = iRecordService.getAllRecordsByPatientId(patientId,
// recordType);
// result = JSONArray.fromObject(records);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// e.printStackTrace();
// } finally {
// logger.info("Done addPatientRecord Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/deletePatientRecord", produces =
// "text/json;charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "删除病历")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String deletePatientRecord(@RequestParam(value =
// "recordId", required = false) Long recordId) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with deletePatientRecord Action...");
// Record record = iRecordService.getRecordById(recordId);
// record.setRecordStatus(1);
// iRecordService.saveOrUpdateRecord(record);
// // result = JSONArray.fromObject(records);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// e.printStackTrace();
// } finally {
// logger.info("Done deletePatientRecord Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/addPatientRecordImage", produces =
// "text/json;charset=utf-8", method = {
// RequestMethod.POST, RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "为病人添加病历")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String addPatientRecordImage(
// @RequestParam(value = "patientId", required = false) Long patientId,
// @RequestParam(value = "file", required = false) MultipartFile file) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with addPatientRecordImage Action...");
// Assert.notNull(file, "病历图片不能为空");
// Assert.notNull(patientId, "patientId不能为空");
// Patient patient = iPatientService.getPatientById(patientId);
// Assert.notNull(patient, "patientId有误");
// String path = request.getSession().getServletContext().getRealPath("");
// path = path.substring(0, path.lastIndexOf("/")) + "/files/" + "patient" +
// patientId + "/";
// // 得到上传的文件的文件名
// String filename = "record_" + Calendar.getInstance().getTimeInMillis() +
// ".png";
// path += filename;
// File f = new File(path);
// if (!f.getParentFile().exists())
// f.getParentFile().mkdirs();
// if (!f.exists())
// f.createNewFile();
// file.transferTo(f);
// String fileNameUrl = Code.SERVER_ADDRESS + "/files/" + "patient" + patientId
// + "/" + filename;
// result.put("imageUrl", fileNameUrl);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// e.printStackTrace();
// } finally {
// logger.info("Done addPatientRecordImage Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// }
