package com.rainea.troubleshoot.heap;

/**
 * 模拟 Kill process or sacrifice child异常
 * 模拟此错误的前提是要关闭系统的交换分区，且设置-Xmx足够大，最好接近或者等于物理内存
 *
 * @author liulang
 * @date 2021-08-24
 **/
public class KillProcessMain {

    public static void main(String[] args) {
        java.util.List<int[]> l = new java.util.ArrayList();
        for (int i = 1; i < 10000000; i++) {
            try {
                l.add(new int[7_000]);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
