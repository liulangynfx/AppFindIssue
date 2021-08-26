package com.rainea.troubleshoot.cpu;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * 【问题重现】频繁的上下文切换（Context Switch）导致cpu高
 *
 * @author liulang
 * @date 2021-08-17
 **/
public class CSMain {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService tp = Executors.newFixedThreadPool(64);
        for (int idx = 0; idx < 64; idx++) {
            tp.submit(() -> {
                while (true) {
                    //此行代码会使当前前程在runnable和TIMED_WAITING之间频繁切换
                    LockSupport.parkNanos(100);
                }
            });
        }

        System.out.println("--------");
    }

}
