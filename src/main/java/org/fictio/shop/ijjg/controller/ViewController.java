package org.fictio.shop.ijjg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面展示控制器,只负责页面展示,不处理数据
 * @author dk
 *
 */
@Controller
@RequestMapping("view")
public class ViewController {
	
	@RequestMapping("login")
	public String loginShow(){
		return "login";
	}
	
	@RequestMapping("regist")
	public String register(){
		return "regist";
	}
	
	@RequestMapping("ucenterIndex")
	public String ucenterIndexShow(){
		return "ucenterIndex";
	}

}
