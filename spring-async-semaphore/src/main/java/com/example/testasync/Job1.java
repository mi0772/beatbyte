package com.example.testasync;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@Component
public class Job1 extends Job {

    public Job1() {
        super("job1");
    }

    @Override
    @Async
    public Future<Integer> process() throws InterruptedException {
        log.info("job 1 started ");

        Thread.sleep(200);
        log.info("job 1 end");
        return new AsyncResult<Integer>(0);
    }
}
