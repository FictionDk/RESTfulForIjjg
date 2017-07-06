package org.fictio.shop.ijjg.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fictio.shop.ijjg.common.CacheManager;
import org.fictio.shop.ijjg.common.DefaultTokenManager;
import org.fictio.shop.ijjg.pojo.RequestData;
import org.fictio.shop.ijjg.pojo.ResponseData;
import org.fictio.shop.ijjg.pojo.SignUser;
import org.fictio.shop.ijjg.pojo.UserIndex;
import org.fictio.shop.ijjg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping("user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户注册
	 * 
	 * @param json={"username":username,"password":password,"code",code}
	 * @param response={"code":code,"result":result,"token":token}
	 */
	@RequestMapping("registeAct")
	@ResponseBody
	public void registeAct(HttpServletRequest request,HttpServletResponse response){
		Gson gson = new Gson();
		SignUser signUser = gson.fromJson(request.getParameter("json"), SignUser.class);
		ResponseData<String> result = new ResponseData<>();
		try {
			String token = userService.registeUser(signUser);
			result.setToken(token);
			result.setSuccess();
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			e.printStackTrace();
		}
		
		response.setContentType("application/json; charset=UTF-8");
		try {
			response.getWriter().println(gson.toJson(result));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成注册码
	 * @param request
	 * @param response
	 */
	@RequestMapping("getRegisteCode")
	@ResponseBody
	public void getRegisteCode(HttpServletRequest request,HttpServletResponse response){
		CacheManager cacheManager = CacheManager.getInstance();
		Gson gson = new Gson();
		SignUser user = gson.fromJson(request.getParameter("json"),SignUser.class);
		ResponseData<String> result = new ResponseData<String>();
		log.info(user.toString());
		
		if(user!=null && user.getUsername()!=""){
			String code = String.valueOf((new Random().nextInt(9000)+1000));
			log.info("code="+code);
			cacheManager.setValue(user.getUsername(), code);
			result.setSuccess();
		}else{
			result.setMessage("获取验证码失败!");
		}
		
		response.setContentType("application/json; charset=UTF-8");
		try {
			response.getWriter().println(gson.toJson(result));
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * 用户登录
	 * @param request
	 * @param response
	 */
	@RequestMapping("login")
	@ResponseBody
	public <T> void singInAct(HttpServletRequest request,HttpServletResponse response){
		SignUser signUser = new SignUser();
		Gson gson = new Gson();
		ResponseData<String> result = new ResponseData<>();
		try {
			String signUserName = request.getParameter("json");
			signUser = gson.fromJson(signUserName, signUser.getClass());
			result = userService.UserLogin(signUser);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		response.setContentType("application/json; charset=UTF-8");
		try {
			response.getWriter().println(gson.toJson(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("userInfoGet")
	@ResponseBody
	public <T> void getUserInfo(HttpServletRequest request,HttpServletResponse response){
		Gson gson = new Gson();
		RequestData<T> reqData = gson.fromJson(request.getParameter("json"), RequestData.class);
		String token = reqData.getToken();
		String userName = new DefaultTokenManager().getUsername(token);
		log.info("username = "+userName);
		UserIndex u = userService.getUserIndexByUserName(userName);
		log.info(u.toString());
		ResponseData<UserIndex> result = new ResponseData<>();
		result.setObject(u);
		result.setSuccess();
		result.setToken(token);
		response.setContentType("application/json; charset=UTF-8");
		try {
			response.getWriter().println(gson.toJson(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
