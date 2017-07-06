package org.fictio.shop.ijjg.dao;

import org.fictio.shop.ijjg.pojo.User;

public interface UserMapper {
	User selectUserByUsername(String userName);
	
	int updateUserLastLoginTime(User user);
	
    int insert(User record);

    int insertSelective(User record);
}