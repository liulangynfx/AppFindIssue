package com.rainea.troubleshoot.heap;

import javassist.ClassPool;

/**
 * jdk 1.7以下执行
 *
 * @author liulang
 * @date 2021-08-23
 **/
public class PermgenSpaceMain {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100_000_000; i++) {
            generate("com.rainea.troubleshoot.heap.Generated" + i);
        }
    }

    public static Class generate(String name) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        return pool.makeClass(name).toClass();
    }
}
