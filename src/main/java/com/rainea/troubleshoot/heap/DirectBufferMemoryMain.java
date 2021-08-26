package com.rainea.troubleshoot.heap;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 模拟 java.lang.OutOfMemoryError: Direct buffer memory
 *
 * @author liulang
 * @date 2021-08-24
 **/
public class DirectBufferMemoryMain {

    public static void main(String[] args) {
        List<ByteBuffer> list = new ArrayList<>();
        while (true) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100_000);
            list.add(byteBuffer);
            System.out.println("11111");
        }
    }
}
