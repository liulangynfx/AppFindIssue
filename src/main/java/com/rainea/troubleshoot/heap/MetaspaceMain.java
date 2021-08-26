package com.rainea.troubleshoot.heap;

/**
 * 模拟 java.lang.ClassFormatError: Metaspace
 * jvm参数：-XX:MaxMetaspaceSize=50m
 *
 * @author liulang
 * @date 2021-08-23
 **/
public class MetaspaceMain {

    static javassist.ClassPool cp = javassist.ClassPool.getDefault();

    public static void main(String[] args) throws Exception{
        for (int i = 0; ; i++) {
            //动态创建一个空类，里面没有任何方法和属性，加载到元空间
            Class c = cp.makeClass("com.rainea.troubleshoot.heap.Generated" + i).toClass();
        }
    }
}
