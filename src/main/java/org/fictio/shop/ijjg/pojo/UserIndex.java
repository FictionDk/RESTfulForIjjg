package org.fictio.shop.ijjg.pojo;

import java.util.List;
/**
 * 个人中心页面
 * @author dk
 *
 */
public class UserIndex {
    
    private int messageCount;
    
    private List<Message> messageList;
    
    private User user;
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}

	public int getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
    
}