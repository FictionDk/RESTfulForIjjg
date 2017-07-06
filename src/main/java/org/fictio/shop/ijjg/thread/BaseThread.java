package org.fictio.shop.ijjg.thread;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程池
 * @author dk
 *
 */
public abstract class BaseThread implements Runnable{
	
	private final static Logger log = LoggerFactory.getLogger(BaseThread.class);
	
	private static int errorTimes = 5;
	private static int maxThread = 10;
	private boolean threadStates = false;
	
	/**
	 * 获得空闲线程
	 * @param threads 线程池
	 * @return
	 */
	protected static BaseThread getBaseThreadInstance(List<BaseThread> threads){
		for(BaseThread t : threads){
			if(!t.threadStates){
				t.threadStates = true;
				return t;
			}
		}
		try {
			Thread.sleep(1500L);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return getBaseThreadInstance(threads);
	}
	
	public abstract void runCode() throws Exception;
	
	public void run(){
		try {
			runCode();
			this.close();
		} catch (Exception e) {
			log.info(e.getMessage());
			if(getErrorTimes()>0){
				setErrorTimes(getErrorTimes()-1);
				run();
			}
			this.close();
		}
	}
	
	public void close(){
		this.threadStates = false;
	}

	public static int getErrorTimes() {
		return errorTimes;
	}

	public static void setErrorTimes(int errorTimes) {
		BaseThread.errorTimes = errorTimes;
	}

	public static int getMaxThread() {
		return maxThread;
	}

	public static void setMaxThread(int maxThread) {
		BaseThread.maxThread = maxThread;
	}
}
