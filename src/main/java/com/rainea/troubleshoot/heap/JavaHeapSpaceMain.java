package com.rainea.troubleshoot.heap;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * 模拟java.lang.OutOfMemoryError: Java heap space
 * jvm参数：-Xmx10m
 *
 * @author liulang
 * @date 2021-08-22
 **/
public class JavaHeapSpaceMain {

    static final int SIZE=2*1024*1024;

    public static void main(String[] args) {
        int[] i = new int[SIZE];
    }
}
