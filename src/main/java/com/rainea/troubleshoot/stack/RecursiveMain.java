package com.rainea.troubleshoot.stack;

/**
 * 栈-递归调用, 1+2+3+...+n之和
 *
 * @author liulang
 * @date 2021-08-20
 **/
public class RecursiveMain {

    private static long stackLength = 0;

    public static void main(String[] args) {
        try {
            System.out.println(add(20000L));
        } catch (StackOverflowError e) {
            System.out.println("stack depth: " + stackLength);
            throw e;
        }

    }

    public static long add(long num) {
        stackLength ++;
        if (num <= 0) {
            return 0;
        }
        return num + add(num - 1);
    }
}
