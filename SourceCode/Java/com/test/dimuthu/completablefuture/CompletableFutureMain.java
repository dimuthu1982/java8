package com.test.dimuthu.completablefuture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class CompletableFutureMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		System.out.println(LocalDateTime.now() + "  [Thread: "+Thread.currentThread().getName()+"] Process Initiated...");

		CompletableFuture<String> completableFuture_10 = submit(5);
		String formattedString_10 = completableFuture_10.get();
		System.out.println(LocalDateTime.now() + " Returned [formattedString: "+formattedString_10+"]...");

		System.out.println(LocalDateTime.now() + " [Thread: "+Thread.currentThread().getName()+"] Process Completed...");

		System.out.println("\n===================== Asynchronous =====================\n");

		System.out.println(LocalDateTime.now() + "  [Thread: "+Thread.currentThread().getName()+"] Asynchronous Process Initiated...");
		CompletableFuture<String> future = supplyAsync(5);
		System.out.println(LocalDateTime.now() + " [Thread: "+Thread.currentThread().getName()+"] Asynchronous Process Completed...");

		System.out.println(LocalDateTime.now() + " [Thread: "+Thread.currentThread().getName()+"] Asynchronous Process Before Calling get()...");
		
		System.out.println(LocalDateTime.now() + " future.get(): " + future.get());
		System.out.println(LocalDateTime.now() + " [Thread: "+Thread.currentThread().getName()+"] Asynchronous Process After Calling get()...");
	}

	private static CompletableFuture<String> supplyAsync(long sleepInSeconds) {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

			List<String> strList;
			String formattedString = "Empty Data";

			try {
				System.out.println(LocalDateTime.now() + " \t[Thread: "+Thread.currentThread().getName()+"] Sub Process Initiated...");

				strList = TaskExecutor.getInstance().longExecutionTask(sleepInSeconds);
				formattedString = "Returned Data: " + String.join(",", strList);

				System.out.println(LocalDateTime.now() + " \t[Thread: "+Thread.currentThread().getName()+"] Sub Process Output [formattedString: "+formattedString+"]");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(LocalDateTime.now() + " \t[Thread: "+Thread.currentThread().getName()+"] Sub Process Completed...");
			return formattedString;

		});
		return future;
	}

	private static CompletableFuture<String> submit(long sleepInSeconds) {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();

		Executors.newCachedThreadPool().submit(() -> {

			try {
				System.out.println(LocalDateTime.now() + " \t[Thread: "+Thread.currentThread().getName()+"] Sub Process Initiated...");

				List<String> strList = TaskExecutor.getInstance().longExecutionTask(sleepInSeconds);

				String formattedString = String.join(",", strList);

				System.out.println(LocalDateTime.now() + " \t[Thread: "+Thread.currentThread().getName()+"] Sub Process Output [formattedString: "+formattedString+"]");

				completableFuture.complete(formattedString);

				System.out.println(LocalDateTime.now() + " \t[Thread: "+Thread.currentThread().getName()+"]Sub Process Completed...");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		return completableFuture;
	}
}
