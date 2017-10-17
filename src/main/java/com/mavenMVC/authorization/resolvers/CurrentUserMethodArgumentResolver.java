package com.mavenMVC.authorization.resolvers;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.mavenMVC.authorization.annotation.CurrentUser;
import com.mavenMVC.service.IBuyerUserService;
import com.mavenMVC.service.IMUserService;
import com.mavenMVC.service.ISellerUserService;
import com.mavenMVC.util.Code;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 * 
 * @see com.mavenMVC.authorization.annotation.CurrentUser
 * @author hcd
 * @date 2016/4/16.
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	IMUserService MUserService;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 如果参数类型是User并且有CurrentUser注解则支持
		if (parameter.hasParameterAnnotation(CurrentUser.class)) {
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// 取出鉴权时存入的登录用户token
		String token = webRequest.getHeader(Code.TOKEN);
		Iterator<String> s = webRequest.getHeaderNames();
		if (token != null) {
			// 从数据库中查询并返回
			return MUserService.loginByToken(token);
		} else {
			return null;
		}
	}

}
