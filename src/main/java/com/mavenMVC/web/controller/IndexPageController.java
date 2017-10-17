// package com.mavenMVC.web.controller;
//
// import com.mavenMVC.authorization.annotation.Authorization;
//
/// **
// * Created by lizai on 15/4/29.
// */
//
// import com.mavenMVC.authorization.annotation.CurrentUser;
// import com.mavenMVC.entity.*;
// import com.mavenMVC.service.*;
// import com.mavenMVC.util.RequestManager;
// import com.wordnik.swagger.annotations.ApiImplicitParam;
// import com.wordnik.swagger.annotations.ApiImplicitParams;
// import com.wordnik.swagger.annotations.ApiOperation;
// import net.sf.json.JSONObject;
// import org.apache.log4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.ResponseBody;
//
// import java.util.ArrayList;
// import java.util.List;
//
//
/// **
// * Created by lizai on 2014/7/25.
// */
// @Controller
// @RequestMapping("/index")
// public class IndexPageController {
//
// protected final Logger logger =
// Logger.getLogger(String.valueOf(IndexPageController.class));
//
// @Autowired
// private IArticleService iArticleService;
//
// @Autowired
// private IDoctorService iDoctorService;
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
// @RequestMapping(value = "/getIndexContent", produces = "text/json;
// charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
//// @Authorization
// @ApiOperation(value = "获取首页内容")
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
// String getIndexContent(@CurrentUser Object currentUser) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with getIndexContent Action...");
//// Assert.notNull(currentUser, "未登录系统");
// List<Long> doctorIds = null;
// List<Long> articleIds = null;
// if (currentUser != null && currentUser instanceof User) {
// doctorIds = iDoctorService.getMyCollectionDoctorIds(((User)
// currentUser).getUserId());
// articleIds = iArticleService.getMyCollectionArticleIds(((User)
// currentUser).getUserId());
// }
// List<Article> topArticles = iArticleService.getAllTopArticles(0, 3, null);
// if (topArticles != null && topArticles.size() > 0) {
// List<JSONObject> topData = new ArrayList<JSONObject>();
// for (Article article : topArticles) {
// JSONObject jo = JSONObject.fromObject(article);
// if (articleIds != null && articleIds.contains(article.getArticleId())) {
// jo.put("articleCollected", true);
// } else {
// jo.put("articleCollected", false);
// }
// topData.add(jo);
// }
// result.put("topArticles", topData);
// }
// List<Article> unTopArticles = iArticleService.getAllUnTopArticles(0, 3,
// null);
// if (unTopArticles != null && unTopArticles.size() > 0) {
// List<JSONObject> untopData = new ArrayList<JSONObject>();
// for (Article article : unTopArticles) {
// JSONObject jo = JSONObject.fromObject(article);
// if (articleIds != null && articleIds.contains(article.getArticleId())) {
// jo.put("articleCollected", true);
// } else {
// jo.put("articleCollected", false);
// }
// untopData.add(jo);
// }
// result.put("unTopArticles", untopData);
// }
// List<Doctor> topDoctors = iDoctorService.getTopDoctors(0, 3, null);
// if (topDoctors != null && topDoctors.size() > 0) {
// List<JSONObject> topData1 = new ArrayList<JSONObject>();
// for (Doctor doctor : topDoctors) {
// JSONObject jo = JSONObject.fromObject(doctor);
// if (doctorIds != null && doctorIds.contains(doctor.getDoctorId())) {
// jo.put("doctorCollected", true);
// } else {
// jo.put("doctorCollected", false);
// }
// fillInHospital(jo, doctor);
// topData1.add(jo);
// }
// result.put("topDoctors", topData1);
// }
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// e.printStackTrace();
// } finally {
// logger.info("Done getIndexContent Action!");
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
