package org.fictio.shop.ijjg.service;

import java.util.Date;
import java.util.List;

import org.fictio.shop.ijjg.common.CommenConstans;
import org.fictio.shop.ijjg.dao.MessageMapper;
import org.fictio.shop.ijjg.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageService {
	
	@Autowired
	private MessageMapper msgMapper;
	
	/**
	 * 管理员发送信息
	 * @param userId
	 * @param msg
	 */
	public void sendMessageByAdmin(int userId,String msg){
		sendMessage(CommenConstans.ADMIN_USER_ID, userId, msg);
	}
	
	/**
	 * 发送信息
	 * @param fromUserId
	 * @param toUserId
	 * @param msg
	 */
	public void sendMessage(int fromUserId,int toUserId,String msg){
		Message message = new Message();
		message.setFromUserId(fromUserId);
		message.setToUserId(toUserId);
		message.setMessage(msg);
		message.setAlreadyRead(CommenConstans.MESSAGE_NOT_READ);
		message.setMsgTime(new Date());
		msgMapper.insert(message);
	}
	
	public void testrun(){
		int toUserId = 12;
		List<Message> msgList = getUserMessageList(toUserId);
		for(Message msg : msgList){
			System.out.println(msg.toString());
		}
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<Message> getUserMessageList(Integer userId) {
		return msgMapper.getMessageListByToUserId(userId);
	}
	

}
