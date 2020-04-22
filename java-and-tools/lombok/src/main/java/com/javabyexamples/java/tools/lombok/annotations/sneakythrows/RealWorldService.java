package com.javabyexamples.java.tools.lombok.annotations.sneakythrows;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;

public class RealWorldService {

    public void runnableWithException() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                throw new IOException();
            }
        });
    }
}
