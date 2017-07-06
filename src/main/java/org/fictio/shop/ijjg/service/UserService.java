package org.fictio.shop.ijjg.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.fictio.shop.ijjg.common.CacheManager;
import org.fictio.shop.ijjg.common.CommenConstans;
import org.fictio.shop.ijjg.common.DefaultTokenManager;
import org.fictio.shop.ijjg.common.TokenManager;
import org.fictio.shop.ijjg.dao.UserMapper;
import org.fictio.shop.ijjg.pojo.Message;
import org.fictio.shop.ijjg.pojo.ResponseData;
import org.fictio.shop.ijjg.pojo.SignUser;
import org.fictio.shop.ijjg.pojo.User;
import org.fictio.shop.ijjg.pojo.UserIndex;
import org.fictio.shop.ijjg.thread.AdminNoticeThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
	
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserMapper userDao;
	
	@Autowired
	private MessageService msgService;

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
		user.setUserName(createUserName(signUser.getUsername()));
		user.setPassword(signUser.getPassword());
		user.setUserMobile(signUser.getUsermobile());
		user.setCreateTime(new Date());
		
		userDao.insert(user);
		log.info(user.toString());
		AdminNoticeThread.sendNotice(user.getUserId(),msgService);
		TokenManager tokenManager = new DefaultTokenManager();
		return tokenManager.createToken(user.getUserName());
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
			user.setLastLoginTime(new Date());
			userDao.updateUserLastLoginTime(user);
		}
	}

	/**
	 * 个人中心页面资料获取(个人资料,私信通知)
	 * @param userName
	 * @return
	 */
	public UserIndex getUserIndexByUserName(String userName) {
		UserIndex uIndex = new UserIndex();
		User u = getUserInfoByUserName(userName);
		List<Message> msgList = msgService.getUserMessageList(u.getUserId());
		uIndex.setMessageCount(msgList.size());
		uIndex.setMessageList(msgList);
		uIndex.setUser(u);
		return uIndex;
	}

	/**
	 * 登录业务逻辑处理
	 * @param signUser
	 * @return
	 */
	public ResponseData<String> UserLogin(SignUser signUser) {
		ResponseData<String> result = new ResponseData<>();
		User u = userDao.selectUserByUsername(signUser.getUsername());
		if(u.getPassword().equals(signUser.getPassword())){
			String token = new DefaultTokenManager().createToken(signUser.getUsername());
			updateUserLoginTime(signUser.getUsername());
			result.setSuccess();
			result.setToken(token);
		}else{
			result.setMessage(CommenConstans.PASSWORD_FAILED);
		}
		return result;
	}
	
}
