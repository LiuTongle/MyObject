// package com.mavenMVC.web.controller;
//
// import com.mavenMVC.entity.Title;
// import com.mavenMVC.service.ITitleService;
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
// @RequestMapping("/title")
// public class TitleController {
//
// protected final Logger logger =
// Logger.getLogger(String.valueOf(TitleController.class));
//
// @Autowired
// private ITitleService iTitleService;
//
// @RequestMapping(value = "/getAllTitles", produces = "text/json;
// charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
//// @Authorization
// @ApiOperation(value = "获取所有职称信息")
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
// String getAllTitles() {
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// logger.info("Dealing with getAllTitles Action...");
// List<Title> titles = iTitleService.getAllTitles();
// result = JSONArray.fromObject(titles);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done getAllTitles Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// }
