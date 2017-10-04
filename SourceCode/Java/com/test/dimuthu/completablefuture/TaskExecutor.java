package com.test.dimuthu.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TaskExecutor {

	private static TaskExecutor instance = new TaskExecutor();
	
	private TaskExecutor() {}
	
	public static TaskExecutor getInstance() {
		return instance;
	}
	
	public List<String> longExecutionTask(long processTime) throws InterruptedException {
		TimeUnit.SECONDS.sleep(processTime);	
		
		return Arrays.asList("Letter A", "Letter B");
	}
}
