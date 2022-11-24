package com.example.testasync;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author c.digiuseppe
 */
@Slf4j
@Component
public class Job2 extends Job {
    public Job2() {
        super("job2");
    }

    @Override
    @Async
    public Future<Integer> process() throws InterruptedException {
        log.info("job 2 started");

        Thread.sleep(3000);
        log.info("job 2 end");
        return new AsyncResult<Integer>(0);
    }
}