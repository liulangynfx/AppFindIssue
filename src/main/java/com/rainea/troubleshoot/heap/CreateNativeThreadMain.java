package com.rainea.troubleshoot.heap;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟java.lang.OutOfMemoryError: unable to create new native thread
 * mac运行下在 4070 次之后报此异常
 *
 * @author liulang
 * @date 2021-08-24
 **/
public class CreateNativeThreadMain {
    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);

        while (true) {
            new Thread(() -> {
                System.out.println("thread " + count.getAndIncrement());
                try {
                    Thread.sleep(50_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
