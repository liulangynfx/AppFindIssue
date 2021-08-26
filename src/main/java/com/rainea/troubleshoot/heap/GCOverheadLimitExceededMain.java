package com.rainea.troubleshoot.heap;

import java.util.UUID;

/**
 * 模拟java.lang.OutOfMemoryError: GC overhead limit exceeded
 * jvm参数：-Xmx10m
 *
 * @author liulang
 * @date 2021-08-23
 **/
public class GCOverheadLimitExceededMain {

    public static void main(String[] args) {
        int size = 200_0000;
        String[] arr = new String[size];
        for (int i=0; i<size; i++) {
            arr[i] = UUID.randomUUID().toString();
        }
        System.out.println("main over");
    }
}
