// package com.mavenMVC.web.controller;
//
// import com.mavenMVC.entity.Version;
// import com.mavenMVC.service.IVersionService;
// import com.mavenMVC.util.RequestManager;
// import net.sf.json.JSONObject;
// import org.apache.log4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
//
// import javax.servlet.http.HttpServletRequest;
//
/// **
// * Created by lizai on 15/6/11.
// */
//
// @Controller
// @RequestMapping(value = "/version")
// public class VersionController {
//
// protected final Logger logger =
// Logger.getLogger(String.valueOf(VersionController.class));
//
// @Autowired
// private IVersionService iVersionService;
//
// @Autowired
// private HttpServletRequest request;
//
// @RequestMapping(value = "/get", produces = "text/json; charset=utf-8", method
// = {RequestMethod.POST, RequestMethod.GET})
// public @ResponseBody
// String getVersionCode(@RequestParam(value = "versionCode") Integer
// versionCode) {
// RequestManager requestManager = new RequestManager();
// JSONObject jo = new JSONObject();
// try {
// logger.info("Dealing with getVersionCode Action...");
// if (versionCode != null) {
// Long maxId = iVersionService.getMaxId();
// Version version = iVersionService.getVersionById(maxId);
// if(versionCode<version.getVersionCode()){
// jo = JSONObject.fromObject(version);
// jo.put("newVersion",true);
// }else{
// jo = JSONObject.fromObject(version);
// jo.put("newVersion",false);
// }
// } else {
// requestManager.putErrorMessage("没有versionCode");
// }
// } catch (Exception e) {
// logger.error(e);
// e.printStackTrace();
// } finally {
// logger.info("Done getVersionCode Action!");
// return requestManager.printJson(jo).toString();
// }
// }
//
// }
