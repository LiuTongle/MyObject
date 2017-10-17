// package com.mavenMVC.web.controller;
//
// import org.apache.log4j.Logger;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.ResponseBody;
//
// import com.mavenMVC.util.RequestManager;
// import com.wordnik.swagger.annotations.ApiImplicitParam;
// import com.wordnik.swagger.annotations.ApiImplicitParams;
// import com.wordnik.swagger.annotations.ApiOperation;
//
// import net.sf.json.JSONArray;
//
// @Controller
// @RequestMapping("/hcd")
// public class HcdController {
// protected final Logger logger =
// Logger.getLogger(String.valueOf(CommodityController.class));
//
// @RequestMapping(value = "/getCommoditys", produces = "text/json;
// charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "获取买家发布的商品")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String getCommoditys() {
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// logger.info("Dealing with updateUserInfo Action...");
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done updateUserInfo Action!");
// return requestManager.printJson(result).toString();
// }
// }
// }
