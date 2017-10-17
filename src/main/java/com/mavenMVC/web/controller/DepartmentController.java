// package com.mavenMVC.web.controller;
//
/// **
// * Created by lizai on 15/4/29.
// */
//
// import com.mavenMVC.entity.Department;
// import com.mavenMVC.service.IDepartmentService;
// import com.mavenMVC.util.RequestManager;
// import com.wordnik.swagger.annotations.ApiImplicitParam;
// import com.wordnik.swagger.annotations.ApiImplicitParams;
// import com.wordnik.swagger.annotations.ApiOperation;
// import net.sf.json.JSONArray;
// import org.apache.log4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.ResponseBody;
//
// import java.util.List;
//
//
/// **
// * Created by lizai on 2014/7/25.
// */
// @Controller
// @RequestMapping("/department")
// public class DepartmentController {
//
// protected final Logger logger =
// Logger.getLogger(String.valueOf(DepartmentController.class));
//
// @Autowired
// private IDepartmentService iDepartmentService;
//
// @RequestMapping(value = "/getAllDepartments", produces = "text/json;
// charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
//// @Authorization
// @ApiOperation(value = "获取所有科室信息")
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
// String getAllDepartments() {
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// logger.info("Dealing with getAllDepartments Action...");
// List<Department> departments = iDepartmentService.getAllDepartments();
// result = JSONArray.fromObject(departments);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done getAllDepartments Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// }
