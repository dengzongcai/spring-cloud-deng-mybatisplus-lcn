package com.deng.commons.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * @ClassName: ExecutorManager 
 * @Description: 线程管理
 * @author ZongCai
 * @date 2021/8/2
 */
public class ExecutorManager {
	
	private static ExecutorManager executorManager;
	
	private final ExecutorService executor;
	
	/**
	 * 默认数
	 */
	private static int defaultThreadNum = 500;
	
	/**
	 * 程序运行时创建一个静态只读的进程辅助对象 
	 */
	private static final Object sysnRoot = new Object();
	
	
	public ExecutorManager(int threadNum){
		executor = Executors.newFixedThreadPool(threadNum);
	}
	
	
	public static ExecutorManager getInstance(){
		if(executorManager == null){
			synchronized (sysnRoot){
				if(executorManager == null){
					executorManager = new ExecutorManager(defaultThreadNum);
				}
			}
		}
		return executorManager;
	}
	
	


	/**
	 * 
	 * @Title: execute 
	 * @Description: 执行线程
	 * @param @param command 参数说明 
	 * @return void 返回类型 
	 * @throws
	 */
	public static void execute(Runnable command){
		ExecutorManager.getInstance().executor.execute(command);
	}
	
	
	/**
	 * 
	 * @Title: submit 
	 * @Description: 提交线程
	 * @param @param task
	 * @param @return 参数说明 
	 * @return Future<T> 返回类型 
	 * @throws
	 */
	public static <T> Future<T> submit(Callable<T> task){
		return ExecutorManager.getInstance().executor.submit(task);
	}
	
}
