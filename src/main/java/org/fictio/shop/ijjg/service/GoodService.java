package org.fictio.shop.ijjg.service;

import java.util.List;

import org.fictio.shop.ijjg.dao.GoodMapper;
import org.fictio.shop.ijjg.pojo.Good;
import org.fictio.shop.ijjg.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoodService {

	@Autowired
	private GoodMapper goodMapper;
	
	public Good showGoodById(int id){
		return goodMapper.getGoodById(id);
	}

	
	/**
	 * 获取商品分页
	 * @param page
	 * @return
	 */
	public Page getGoodByPage(Page page) {
		List<Good> goodList = goodMapper.getGoodByPage(page.getPageStart(),page.getPageSize());
		for(Good good : goodList){
			System.out.println(good.getName());
		}
		page.setPageContent(goodList);
		
		return page;
	}
	
}
