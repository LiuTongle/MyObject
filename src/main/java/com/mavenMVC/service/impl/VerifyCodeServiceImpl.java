// package com.mavenMVC.service.impl;
//
// import com.mavenMVC.service.IVerifyCodeService;
// import com.mavenMVC.util.HttpRequestUtil;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
/// **
// * Created by lizai on 15/6/12.
// */
// @Service("VerifyCodeServiceImpl")
// @Transactional
// public class VerifyCodeServiceImpl implements IVerifyCodeService {
//
// @Override
// public boolean useVerifyCode(String cellphone, int verifyCode, int type) {
// if((cellphone!=null)&&(verifyCode>0)){
// String result =
// HttpRequestUtil.sendPost("https://api.leancloud.cn/1.1/verifySmsCode/" +
// verifyCode + "?mobilePhoneNumber=" + cellphone, "",type);
// if(result.equals("{}"))
// return true;
// else
// return false;
// }else{
// return false;
// }
// }
// }
