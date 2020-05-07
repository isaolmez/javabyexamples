package com.javabyexamples.java.concurrency.buildingblocks.collections.concurrent.blockingqueue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class Indexer implements Runnable {

    private final BlockingQueue<File> queue;

    private final ConcurrentMap<String, Set<File>> index;

    public Indexer(BlockingQueue<File> queue, ConcurrentMap<String, Set<File>> index) {
        this.queue = queue;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.interrupted()) {
                    break;
                }

                indexFile(queue.take());
                System.out.println(index);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void indexFile(File file) {
        String content = readFile(file);
        reverseIndex(file, content);
    }

    private String readFile(File file) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    private void reverseIndex(File file, String content) {
        try (Scanner scanner = new Scanner(content)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                index.compute(word, (k, v) -> {
                    if (v == null) {
                        v = new ConcurrentSkipListSet<>();
                    }

                    v.add(file);
                    return v;
                });
            }
        }
    }
} 
