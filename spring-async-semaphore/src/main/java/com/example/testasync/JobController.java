package com.example.testasync;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author c.digiuseppe
 */

@Slf4j
public class JobController {
    private static final ConcurrentHashMap<String, UUID> activeJobs;

    static {
        activeJobs = new ConcurrentHashMap<>();
    }
    private JobController() {
    }

    public static synchronized UUID addJob(Job j) {
        var uuid = UUID.randomUUID();
        activeJobs.putIfAbsent(j.getIdentifier(), uuid);
        return uuid;
    }

    public static synchronized boolean isRunning(Job j) {
        return activeJobs.containsKey(j.getIdentifier());
    }

    public static synchronized void remove(Job j) {
        activeJobs.remove(j.getIdentifier());
    }

    public static void printQueue() {
        log.info("============================================");
        for (String k : activeJobs.keySet()) {
            log.info("{} - {}", k, activeJobs.get(k));
        }
        log.info("============================================");
    }
}
