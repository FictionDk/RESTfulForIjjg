package org.fictio.shop.ijjg.dao;

import java.util.List;

import org.fictio.shop.ijjg.pojo.Message;

public interface MessageMapper {
    int insert(Message record);

    int insertSelective(Message record);

	List<Message> getMessageListByToUserId(Integer userId);
}