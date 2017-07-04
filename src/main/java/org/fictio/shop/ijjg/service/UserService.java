package org.fictio.shop.ijjg.service;

import java.util.Date;
import java.util.Random;

import org.fictio.shop.ijjg.common.CacheManager;
import org.fictio.shop.ijjg.common.DefaultTokenManager;
import org.fictio.shop.ijjg.common.TokenManager;
import org.fictio.shop.ijjg.dao.UserMapper;
import org.fictio.shop.ijjg.pojo.SignUser;
import org.fictio.shop.ijjg.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
	
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserMapper userDao;
	
	public User showUser(){
		User user = new User();
		user.setUserId(1);
		return user;
	}

	public User getUserInfoByUserName(String userName) {
		User user = new User();
		user = userDao.selectUserByUsername(userName);
		return user;
	}

	/**
	 * 用户注册
	 * 
	 * @param signUser
	 * @return
	 * @throws Exception 
	 */
	public String registeUser(SignUser signUser) throws Exception {
		if(!checkSignUser(signUser)){
			throw new Exception("输入的code错误");
		}
		User user = new User();
		user.setUsername(createUserName(signUser.getUsername()));
		user.setPassword(signUser.getPassword());
		user.setUsermobile(signUser.getUsermobile());
		user.setCreatetime(new Date());
		
		int r = userDao.insert(user);
		log.info(r+"");
		TokenManager tokenManager = new DefaultTokenManager();
		return tokenManager.createToken(user.getUsername());
	}
	
	/**
	 * 检查用户注册code是否一致
	 * @param signUser
	 * @return
	 */
	private boolean checkSignUser(SignUser signUser){
		log.info(signUser.toString());
		CacheManager cacheManager = CacheManager.getInstance();
		String code = cacheManager.getValue(signUser.getUsername());
		if(code.equals(signUser.getCode())){
			return true;
		}
		return false;
	}

	/**
	 * 对用户名简单去重
	 * 
	 * @param userName
	 * @return
	 */
	private String createUserName(String userName){
		User tempUser = userDao.selectUserByUsername(userName);
		if(tempUser != null){
			userName += String.valueOf((new Random().nextInt(90)+10));
			createUserName(userName);
		}
		return userName;
	}

	/**
	 * 更新用户的最后登录时间(可用分线程处理)
	 * @param username
	 */
	public void updateUserLoginTime(String username) {
		User user = userDao.selectUserByUsername(username);
		if(user != null){
			user.setLastlogintime(new Date());
			userDao.updateUserLastLoginTime(user);
		}
	}
	
}
