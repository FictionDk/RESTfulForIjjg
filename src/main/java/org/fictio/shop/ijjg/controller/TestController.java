package org.fictio.shop.ijjg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fictio.shop.ijjg.pojo.Good;
import org.fictio.shop.ijjg.pojo.Page;
import org.fictio.shop.ijjg.service.GoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping("test")
public class TestController {
	
	private static final Logger log = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private GoodService goodService;
	
	@RequestMapping("show1")
	public String show(){
		Good good = goodService.showGoodById(12345);
		System.out.println(good);
		log.info("singIn收到");
		return "layout";
	}

	@RequestMapping("get")
	@ResponseBody
	public void testIndex(HttpServletRequest request,HttpServletResponse response){
		System.out.println("收到!");
		//User user = userService.showUser(); 
		Page page = new Page();
		String spage = request.getParameter("json");
		Gson gson = new Gson();
		page = gson.fromJson(spage, page.getClass());
		System.out.println("page:"+page);
		
		page = goodService.getGoodByPage(page);
		
		@SuppressWarnings("unchecked")
		List<Good> goodList = (List<Good>) page.getPageContent();
		
		response.setContentType("application/json; charset=UTF-8");
		try {
			response.getWriter().println(gson.toJson(goodList));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("gtest/{id}")
	public void goodTest(@PathVariable("id") String goodId,HttpServletRequest request,HttpServletResponse response){
		//int id = (int) request.getAttribute("id");
		System.out.println("id="+goodId);
		int id = 60783;
		Good good = new Good();
		good.setName("未收到商品id");
		if(id != 0){
			good = goodService.showGoodById(id);
		}
		System.out.println(good.toString());
		Gson gson = new Gson();
		try {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().println(gson.toJson(good));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
