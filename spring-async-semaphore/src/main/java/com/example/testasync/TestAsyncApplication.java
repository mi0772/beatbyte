package com.example.testasync;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@Slf4j
public class TestAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestAsyncApplication.class, args);
	}

	@Autowired
	private MainScheduler mainScheduler;


	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws InterruptedException {
		mainScheduler.schedule();

	}
}
