package com.rainea.troubleshoot.cpu;

import com.lmax.disruptor.EventFactory;

/**
 * @author liulang
 * @date 2021-08-09
 **/
public class LogEventFactory implements EventFactory<LogEvent> {

    @Override
    public LogEvent newInstance() {
        return new LogEvent();
    }
}
