package org.fictio.shop.ijjg.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.fictio.shop.ijjg.common.TokenException;
import org.fictio.shop.ijjg.pojo.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.gson.Gson;

/**
 * 拦截@controller返回异常,不拦截 Interceptor的异常
 * @author dk
 *
 */
@ControllerAdvice
public class ExceptionController {
	
	private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(Exception.class)
	public void handleGlobleException(Exception e,HttpServletResponse response){
		log.info("get exceptin"+e.getMessage());
		ResponseData<String> result = new ResponseData<>();
		result.setMessage("service_exception"+e.getMessage());
		Gson gson = new Gson();
		try {
			response.getWriter().println(gson.toJson(result));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
			HttpServletResponse response){
		Gson gson = new Gson();
		ResponseData<String> result = new ResponseData<>();
		result.setMessage("cound_not_read_json");
		try {
			response.getWriter().println(gson.toJson(result));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(TokenException.class)
	public void handleTokenInvlidException(TokenException e,HttpServletResponse response){
		System.out.println("token检验失败");
		Gson gson = new Gson();
		ResponseData<String> result = new ResponseData<>();
		result.setMessage(e.getMessage());
		try {
			response.getWriter().println(gson.toJson(result));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public void handleServiceException(Exception e,HttpServletResponse response){
		System.out.println("服务器响应出错");
		
		Gson gson = new Gson();
		ResponseData<String> result = new ResponseData<>();
		result.setMessage("service_error");
		try {
			response.getWriter().println(gson.toJson(result));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
