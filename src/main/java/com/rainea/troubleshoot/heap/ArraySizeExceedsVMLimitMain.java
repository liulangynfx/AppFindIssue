package com.rainea.troubleshoot.heap;

/**
 * 模拟 java.lang.OutOfMemoryError: Requested array size exceeds VM limit
 *
 * @author liulang
 * @date 2021-08-24
 **/
public class ArraySizeExceedsVMLimitMain {

    public static void main(String[] args) {
        for (int i = 3; i >= 0; i--) {
            try {
                //i=3时，数组长度是2^31 - 3,数组大小是（2^31 - 3）* 4 = 8g - 12B，当前堆不够分配
                //i=2时，数组长度是2^31 - 2,数组大小是（2^31 - 2）* 4 = 8g - 8B
                //i=1时，数组长度是2^31 - 1,超过jvm的限制
                //i=0时，数组长度是2^31 - 0,
                int[] arr = new int[Integer.MAX_VALUE-i];
                System.out.format("Successfully initialized an array with %,d elements.\n", Integer.MAX_VALUE-i);
            } catch (Throwable t) {
                t.printStackTrace();
                throw t;
            }
        }
    }
}
