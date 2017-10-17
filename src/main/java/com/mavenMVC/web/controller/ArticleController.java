// package com.mavenMVC.web.controller;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import org.apache.log4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.util.Assert;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
//
/// **
// * Created by lizai on 15/4/29.
// */
//
// import com.mavenMVC.authorization.annotation.CurrentUser;
// import com.mavenMVC.entity.Article;
// import com.mavenMVC.entity.Collection;
// import com.mavenMVC.entity.User;
// import com.mavenMVC.service.IArticleService;
// import com.mavenMVC.util.RequestManager;
// import com.wordnik.swagger.annotations.ApiImplicitParam;
// import com.wordnik.swagger.annotations.ApiImplicitParams;
// import com.wordnik.swagger.annotations.ApiOperation;
//
// import net.sf.json.JSONArray;
// import net.sf.json.JSONObject;
//
/// **
// * Created by lizai on 2014/7/25.
// */
// @Controller
// @RequestMapping("/article")
// public class ArticleController {
//
// protected final Logger logger =
// Logger.getLogger(String.valueOf(ArticleController.class));
//
// @Autowired
// private IArticleService iArticleService;
//
// @RequestMapping(value = "/getTopArticles", produces = "text/json;
// charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "获取首页置顶文章")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String getTopArticles(@CurrentUser Object currentUser,
// @RequestParam(value = "start") Integer start, @RequestParam(value = "offset")
// Integer offset,
// @RequestParam(value = "receivedIds", required = false) List<Long>
// receivedIds) {
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// logger.info("Dealing with getTopArticles Action...");
// Assert.notNull(currentUser, "未登录系统");
// List<Article> articles = iArticleService.getAllTopArticles(start, offset,
// receivedIds);
// if (currentUser instanceof User) {
// List<JSONObject> data = new ArrayList<JSONObject>();
// List<Long> collectedArticleIds = iArticleService
// .getMyCollectionArticleIds(((User) currentUser).getUserId());
// for (Article article : articles) {
// JSONObject jo = JSONObject.fromObject(article);
// if (collectedArticleIds.contains(article.getArticleId())) {
// jo.put("articleCollected", true);
// } else {
// jo.put("articleCollected", false);
// }
// data.add(jo);
// }
// result = JSONArray.fromObject(data);
// } else {
// result = JSONArray.fromObject(articles);
// }
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done getTopArticles Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/getTypeArticles", produces = "text/json;
// charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "分类表获取文章")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String getTypeArticles(@CurrentUser Object currentUser,
// @RequestParam(value = "type") Integer type, @RequestParam(value = "start")
// Integer start,
// @RequestParam(value = "offset") Integer offset,
// @RequestParam(value = "receivedIds", required = false) List<Long>
// receivedIds) {
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// logger.info("Dealing with getTypeArticles Action...");
// // Assert.notNull(currentUser, "未登录系统");
// List<Article> articles = iArticleService.getArticlesByType(type, start,
// offset, receivedIds);
// if (currentUser != null && currentUser instanceof User) {
// List<Long> collectedArticleIds = iArticleService
// .getMyCollectionArticleIds(((User) currentUser).getUserId());
// for (Article article : articles) {
// JSONObject jo = JSONObject.fromObject(article);
// if (collectedArticleIds != null &&
// collectedArticleIds.contains(article.getArticleId())) {
// jo.put("articleCollected", true);
// } else {
// jo.put("articleCollected", false);
// }
// result.add(jo);
// }
// } else {
// result = JSONArray.fromObject(articles);
// }
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done getTypeArticles Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/getMyArticles", produces = "text/json;
// charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "获取我的收藏文章")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String getMyArticles(@CurrentUser Object currentUser,
// @RequestParam(value = "start") Integer start, @RequestParam(value = "offset")
// Integer offset,
// @RequestParam(value = "receivedIds", required = false) List<Long>
// receivedIds) {
// RequestManager requestManager = new RequestManager();
// JSONArray result = new JSONArray();
// try {
// logger.info("Dealing with getMyArticles Action...");
// Assert.notNull(currentUser, "未登录系统");
// Long userId;
// if (currentUser instanceof User) {
// userId = ((User) currentUser).getUserId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// List<Article> articles = iArticleService.getMyCollectionArticles(userId,
// start, offset, receivedIds);
// result = JSONArray.fromObject(articles);
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// e.printStackTrace();
// } finally {
// logger.info("Done getMyArticles Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/getArticleDetail", produces = "text/json;
// charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "获取文章详情")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String getArticleDetail(@CurrentUser Object currentUser,
// @RequestParam(value = "articleId") Long articleId) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with getArticleDetail Action...");
// Assert.notNull(currentUser, "未登录系统");
// Article articles = iArticleService.getArticleDetail(articleId);
// result = JSONObject.fromObject(articles);
// Long userId;
// if (currentUser instanceof User) {
// userId = ((User) currentUser).getUserId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// Collection collection = iArticleService.getCollectArticleAndUser(articleId,
// userId);
// if (collection != null) {
// result.put("articleCollected", true);
// } else {
// result.put("articleCollected", false);
// }
// } catch (Exception e) {
// requestManager.putErrorMessage(e.getMessage());
// e.printStackTrace();
// } finally {
// logger.info("Done getArticleDetail Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/collectArticle", produces = "text/json;
// charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "收藏文章")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String collectArticle(@CurrentUser Object currentUser,
// @RequestParam(value = "articleId") Long articleId) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with collectArticle Action...");
// Assert.notNull(currentUser, "未登录系统");
// Long userId;
// if (currentUser instanceof User) {
// userId = ((User) currentUser).getUserId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// iArticleService.collectArticle(userId, articleId);
// } catch (Exception e) {
// e.printStackTrace();
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done collectArticle Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// @RequestMapping(value = "/unCollectArticle", produces = "text/json;
// charset=utf-8", method = { RequestMethod.POST,
// RequestMethod.GET })
// // @Authorization
// @ApiOperation(value = "取消收藏文章")
// @ApiImplicitParams({
// @ApiImplicitParam(name = "token", value = "token", required = true, dataType
// = "string", paramType = "header"),
// @ApiImplicitParam(name = "sig", value = "sig", required = true, dataType =
// "string", paramType = "header"),
// @ApiImplicitParam(name = "requestTime", value = "requestTime", required =
// true, dataType = "string", paramType = "header"),
// @ApiImplicitParam(name = "tokenType", value = "tokenType", required = true,
// dataType = "string", paramType = "header"), })
// public @ResponseBody String unCollectArticle(@CurrentUser Object currentUser,
// @RequestParam(value = "articleId") Long articleId) {
// RequestManager requestManager = new RequestManager();
// JSONObject result = new JSONObject();
// try {
// logger.info("Dealing with unCollectArticle Action...");
// Assert.notNull(currentUser, "未登录系统");
// Long userId;
// if (currentUser instanceof User) {
// userId = ((User) currentUser).getUserId();
// } else {
// throw new Exception("当前用户类型错误");
// }
// iArticleService.unCollectArticle(userId, articleId);
// } catch (Exception e) {
// e.printStackTrace();
// requestManager.putErrorMessage(e.getMessage());
// } finally {
// logger.info("Done unCollectArticle Action!");
// return requestManager.printJson(result).toString();
// }
// }
//
// }
