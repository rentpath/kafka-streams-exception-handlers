package com.rentpath.kafka.streams.handlers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.errors.DeserializationExceptionHandler;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class LogAndFailSentryExceptionHandler extends SentryExceptionHandler implements DeserializationExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(LogAndFailSentryExceptionHandler.class);

    public LogAndFailSentryExceptionHandler() {
        super();
    }

    @Override
    public void configure(Map<String, ?> map) {
    }

    @Override
    public DeserializationHandlerResponse handle(ProcessorContext processorContext, ConsumerRecord<byte[], byte[]> consumerRecord, Exception e) {
        log.warn(
                "Exception caught during Deserialization, taskId: {}, topic: {}, partition: {}, offset: {}",
                new Object[] {
                        processorContext.taskId(),
                        consumerRecord.topic(),
                        consumerRecord.partition(),
                        consumerRecord.offset(),
                        e
                }
        );
        capture(e);
        return DeserializationHandlerResponse.FAIL;
    }
}
