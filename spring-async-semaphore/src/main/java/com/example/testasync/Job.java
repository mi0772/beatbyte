package com.example.testasync;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author c.digiuseppe
 */
public abstract class Job {

    private String identifier;

    public Job(String identifier) {
        this.identifier = identifier;
    }

    public abstract Future<Integer> process() throws InterruptedException;

    public String getIdentifier() {
        return identifier;
    }
}
