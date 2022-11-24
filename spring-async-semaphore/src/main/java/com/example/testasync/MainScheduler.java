package com.example.testasync;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author c.digiuseppe
 */
@Component
@Slf4j
public class MainScheduler {

    private final Job1 job1;
    private final Job2 job2;

    public MainScheduler(Job1 job1, Job2 job2) {
        this.job1 = job1;
        this.job2 = job2;
    }

    public void schedule() throws InterruptedException {
        log.info("scheule start");
        while (true) {

            this.start(job1);
            this.start(job2);

            Thread.sleep(3000);
            JobController.printQueue();
        }
    }

    private void start(Job j) throws InterruptedException {
        if (JobController.isRunning(j)) {
            log.warn("job {} sta ancora girando", j.getIdentifier());
        }
        else {
            JobController.addJob(j);
            var c1 = j.process();
            this.control(c1, j);
        }
    }
    private void control(Future<Integer> f, Job j) {
        CompletableFuture.supplyAsync(() -> {
            try {
                var r = f.get(2, TimeUnit.SECONDS);
                JobController.remove(j);
            } catch (TimeoutException | InterruptedException | ExecutionException e) {
                log.warn("{} in timeout, interrompo", j.getIdentifier());
                f.cancel(true);
                JobController.remove(j);

            }
            return null;
        });
    }
}
