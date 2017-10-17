package com.mavenMVC.service;

/**
 * Created by lizai on 15/6/12.
 */
public interface IVerifyCodeService {

    public boolean useVerifyCode(String cellphone, int verifyCode, int type);

}
