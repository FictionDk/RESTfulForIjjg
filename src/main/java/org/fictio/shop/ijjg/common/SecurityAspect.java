package org.fictio.shop.ijjg.common;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.fictio.shop.ijjg.pojo.RequestData;
import org.fictio.shop.ijjg.pojo.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

/**
 * 切面类,检查token
 * @author dk
 *
 */
public class SecurityAspect {
	private static final Logger log = LoggerFactory.getLogger(SecurityAspect.class);
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	private static final String[] ALLOW_ACTION = {"/ijjg/view","/ijjg/token",
			"/ijjg/user/login","/ijjg/user/getRegisteCode","/ijjg/user/registeAct"};
	private TokenManager tokenManage;
	
	@SuppressWarnings("unused")
	private String tokenName;
	
	public void setTokenManage(TokenManager tokenManage) {
		this.tokenManage = tokenManage;
	}
	public void setTokenName(String tokenName) {
		System.out.println("tokenName = "+tokenName);
		this.tokenName = tokenName;
	}
	
	public <T> Object excute(ProceedingJoinPoint pjp) throws Throwable{
		String url = request.getRequestURI();
		String json = request.getParameter("json");
		if(isTokenNeed(url)){
			boolean checkResult = checkTokenByJSON(json);
			if(!checkResult){
				checkResult = checkTokenByCookie();
				if(!checkResult){
					ResponseData<String> result = new ResponseData<>();
					result.setMessage("登录状态失效,请重新登录");
					Gson gson = new Gson();
					response.setContentType("application/json; charset=UTF-8");
					try {
						response.getWriter().println(gson.toJson(result));
					} catch (IOException e){
						e.printStackTrace();
					}
					return null;
				}
			}
		}
		return pjp.proceed();
	}
	
	/**
	 * 通过json数据检查token
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> boolean checkTokenByJSON(String json){
		log.info("json="+json);
		Gson gson = new Gson();
		RequestData<T> reqData = new RequestData<>();
		reqData = gson.fromJson(json, reqData.getClass());
		if(reqData != null && reqData.getToken() != null && reqData.getToken() != ""){
			log.info(reqData.getToken());
			return tokenManage.checkToken(reqData.getToken());
		}
		return false;
	}
	
	/**
	 * 通过http的cookie来检验
	 * @return
	 */
	private boolean checkTokenByCookie(){
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
			if(cookie.getName() == "ijjg_token"){
				String token = cookie.getValue();
				return tokenManage.checkToken(token);
			}
		}
		return false;
	}
	
	/**
	 * 判断当前url是否需要token
	 * @param action
	 * @return
	 */
	private boolean isTokenNeed(String action){
		for(int i = 0; i<ALLOW_ACTION.length; i++){
			int index = action.indexOf(ALLOW_ACTION[i]);
			if(index != -1){
				return false;
			}
		}
		log.info(action+" is need token");
		return true;
	}
	
}
