package org.fictio.shop.ijjg.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.fictio.shop.ijjg.common.CommenConstans;
import org.fictio.shop.ijjg.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminNoticeThread extends BaseThread {
	
	private final static Logger log = LoggerFactory.getLogger(AdminNoticeThread.class);
	
	private int toUserId;
	private MessageService msgService;
	
	//msgService无法注入
	//@Autowired
	//private MessageService msgService;

	private static ExecutorService service = Executors.newFixedThreadPool(BaseThread.getMaxThread());
	private static List<BaseThread> threads = new ArrayList<>();
	
	static {
		for(int i=0; i<=BaseThread.getMaxThread(); i++){
			threads.add(new AdminNoticeThread());
		}
	}
	
	@Override
	public void runCode() throws Exception {
		log.info("toUserid"+toUserId);
		msgService.sendMessageByAdmin(toUserId, CommenConstans.WELCOME_MESSAGE);
	}
	
	public static void sendNotice(int toUserId, MessageService msgService){
		AdminNoticeThread thread = (AdminNoticeThread) BaseThread.getBaseThreadInstance(threads);
		thread.msgService = msgService;
		thread.toUserId = toUserId;
		service.execute(thread);
	}
}
