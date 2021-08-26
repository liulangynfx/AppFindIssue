package com.rainea.troubleshoot.cpu;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * @author liulang
 * @date 2021-08-09
 **/
public class LogEventProducer {

    private final RingBuffer<LogEvent> ringBuffer;

    private static final EventTranslatorOneArg<LogEvent, String> TRANSLATOR = (event, sequence, bb) -> event.setMsg(bb);

    public LogEventProducer(RingBuffer<LogEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(String msg){
        ringBuffer.publishEvent(TRANSLATOR, msg);
    }
}
