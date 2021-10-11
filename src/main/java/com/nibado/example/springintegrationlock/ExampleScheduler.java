package com.nibado.example.springintegrationlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExampleScheduler {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleScheduler.class);

    private final LockRegistry lockRegistry;

    public ExampleScheduler(LockRegistry lockRegistry) {
        this.lockRegistry = lockRegistry;
    }

    @Scheduled(fixedDelayString = "${random.int(500)}")
    public void scheduledTask() {
        LOG.info("Task Enter");
        var lock = lockRegistry.obtain("TaskKey");
        lock.lock();

        LOG.info("Task Start");
        sleep(2000);
        LOG.info("Task Finished");

        lock.unlock();
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            LOG.error("Sleep interrupted", e);
        }
    }
}
