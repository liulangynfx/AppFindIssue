package com.rainea.troubleshoot.cpu;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 【问题重现】disruptor消费，用户繁忙导致cpu占用较高
 *  -BusySpinWaitStrategy策略，占用很高
 *  -BlockingWaitStrategy策略，占用较低
 *
 * @author liulang
 * @date 2021-08-09
 **/
public class LogEventMain {

    public static void main(String[] args) {
        LogEventFactory factory = new LogEventFactory();

        // 环形数组的容量，必须要是2的次幂
        int bufferSize = 1024 * 1024;

        // 消费者线程工厂
        ThreadFactory threadFactory = new CustomThreadFactory("disruptor");

        //创建disruptor, 泛型参数:传递的事件的类型
        // 第一个参数: 产生Event的工厂类, Event封装生成-消费的数据
        // 第二个参数: RingBuffer的缓冲区大小
        // 第三个参数: 线程池
        // 第四个参数: SINGLE单个生产者, MULTI多个生产者
        // 第五个参数: WaitStrategy 当消费者阻塞在SequenceBarrier上, 消费者如何等待的策略:
        //**BlockingWaitStrategy 使用锁和条件变量, 效率较低, 但CPU的消耗最小, 在不同部署环境下性能表现比较一致
        //SleepingWaitStrategy 多次循环尝试不成功后, 让出CPU, 等待下次调度; 多次调度后仍不成功, 睡眠纳秒级别的时间再尝试. 平衡了延迟和CPU资源占用, 但延迟不均匀.
        //YieldingWaitStrategy 多次循环尝试不成功后, 让出CPU, 等待下次调度. 平衡了延迟和CPU资源占用, 延迟也比较均匀.
        //**BusySpinWaitStrategy 自旋等待，类似自旋锁. 低延迟但同时对CPU资源的占用也多.
        Disruptor<LogEvent> disruptor = new Disruptor(factory, bufferSize, threadFactory, ProducerType.SINGLE,
                new BusySpinWaitStrategy());

        // 注册事件消费处理器, 也即消费者. 可传入多个EventHandler ...
        disruptor.handleEventsWith(new LogEventConsumer());
        disruptor.handleEventsWith(new LogEventConsumer());
//        disruptor.handleEventsWithWorkerPool()

        // 启动 Disruptor
        disruptor.start();

        // 生产者要使用 Disruptor 的环形数组
        RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();

        LogEventProducer producer = new LogEventProducer(ringBuffer);

        // 模拟消息发送
        for (int i = 0; i < 10000; i++) {
            producer.onData(String.format("msg-%s", i));
        }

//        disruptor.shutdown(); //关闭 disruptor  阻塞直至所有事件都得到处理
//        executor.shutdown(); // 需关闭 disruptor使用的线程池, 上一步disruptor关闭时不会连带关闭线程池

        //线程池2
        Runnable runnable = () -> {
            for (int i=0; i < 10000; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " task run");
            }

        };
        ThreadPoolExecutor testPool = new ThreadPoolExecutor(4, 4, 5, TimeUnit.MINUTES, new ArrayBlockingQueue<>(128), new CustomThreadFactory("testPool"));
        testPool.execute(runnable);
        testPool.execute(runnable);
        testPool.execute(runnable);
        testPool.execute(runnable);



//        while (true) {
//            Integer[] arr = new Integer[1024];
//            for (int i=0; i<1024; i++) {
//                arr[i] = i;
//            }
//            System.out.println(arr[4]);
//        }

    }

    static class CustomThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        CustomThreadFactory(String threadPoolName) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    threadPoolName +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
