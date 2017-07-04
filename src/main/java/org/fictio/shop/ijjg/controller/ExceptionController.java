package org.fictio.shop.ijjg.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.fictio.shop.ijjg.common.TokenException;
import org.fictio.shop.ijjg.pojo.ExceptionResult;
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
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
			HttpServletResponse response){
		System.out.println("参数解析失败");
		Gson gson = new Gson();
		ExceptionResult exception = new ExceptionResult();
		exception.setResultCode(404);
		exception.setMsg("cound_not_read_json");
		try {
			response.getWriter().println(gson.toJson(exception));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(TokenException.class)
	public void handleTokenInvlidException(TokenException e,HttpServletResponse response){
		System.out.println("token检验失败");
		Gson gson = new Gson();
		ExceptionResult exception = new ExceptionResult();
		exception.setResultCode(500);
		exception.setMsg(e.getMessage());
		try {
			response.getWriter().println(gson.toJson(exception));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public void handleServiceException(Exception e,HttpServletResponse response){
		System.out.println("服务器响应出错");
		
		Gson gson = new Gson();
		ExceptionResult exception = new ExceptionResult();
		exception.setResultCode(500);
		exception.setMsg("service_error");
		try {
			response.getWriter().println(gson.toJson(exception));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
