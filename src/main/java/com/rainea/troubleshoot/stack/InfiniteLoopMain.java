package com.rainea.troubleshoot.stack;

/**
 * 栈-无限循环调用，抛出栈溢出异常
 *
 * @author liulang
 * @date 2021-08-20
 **/
public class InfiniteLoopMain {

    private static long stackLength = 0;

    public static void main(String[] args) {
        try {
            printMsg("hello");
        } catch (StackOverflowError e) {
            System.out.println("stack depth: " + stackLength);
            throw e;
        }

    }

    public static void printMsg(String msg) {
        stackLength ++;
        printMsg(msg);
    }

}
