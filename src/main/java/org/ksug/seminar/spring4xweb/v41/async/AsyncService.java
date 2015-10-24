package org.ksug.seminar.spring4xweb.v41.async;

import java.util.Map;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.google.common.collect.ImmutableMap;

@Service
public class AsyncService {

    private AsyncListenableTaskExecutor executorService;

    @PostConstruct
    public void setUp() {
        executorService = new ConcurrentTaskExecutor(
                Executors.newFixedThreadPool(10));
    }

    public ListenableFuture<Map<String, String>> execute(String id)
            throws InterruptedException {
        return executorService.submitListenable(() -> {
            Thread.sleep(2000);
            return ImmutableMap.of("id", id, "thread", Thread.currentThread()
                    .toString());
        });
    }

}
