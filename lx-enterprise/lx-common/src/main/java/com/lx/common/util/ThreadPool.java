
package com.lx.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPool {

    private static ThreadPool _INSTANCE = null;

    private ExecutorService executorService;

    private ThreadPool(int nThreads) {
        executorService = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    public static ThreadPool getInstance(int nThreads) {
        if (_INSTANCE == null) {
            _INSTANCE = new ThreadPool(nThreads);
        }
        return _INSTANCE;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

}
