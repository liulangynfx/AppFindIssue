package com.rainea.troubleshoot.cpu;

import com.lmax.disruptor.EventHandler;

/**
 * @author liulang
 * @date 2021-08-09
 **/
public class LogEventConsumer implements EventHandler<LogEvent> {

    @Override
    public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
//        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + " | Event : " + event);
    }
}
