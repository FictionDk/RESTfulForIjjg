package org.fictio.shop.ijjg.dao;

import org.fictio.shop.ijjg.pojo.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
    
    User selectUserByUsername(String username);

	void updateUserLastLoginTime(User user);
}