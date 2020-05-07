package com.javabyexamples.java.concurrency.buildingblocks.collections.concurrent.blockingqueue;

import java.io.File;
import java.io.FileFilter;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    private static final int N_CONSUMERS = 100;

    private static final int BOUND = 100;

    public static void main(String[] args) {
        startIndexing(new File("C:\\git\\indextest"));
    }

    public static void startIndexing(File... roots) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);
        ConcurrentMap<String, Set<File>> index = new ConcurrentHashMap<>();
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getAbsolutePath().endsWith(".txt");
            }
        };

        for (File root : roots) {
            new Thread(new FileCrawler(queue, filter, root)).start();
        }

        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread(new Indexer(queue, index)).start();
        }
    }
}
