package com.rentpath.kafka.streams.handlers;

import com.rentpath.kafka.streams.handlers.SentryExceptionHandler;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.streams.errors.ProductionExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SentryProductionExceptionHandler extends SentryExceptionHandler implements ProductionExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(SentryProductionExceptionHandler.class);

    public SentryProductionExceptionHandler() {
        super();
    }

    @Override
    public void configure(Map<String, ?> map) {
    }

    @Override
    public ProductionExceptionHandlerResponse handle(ProducerRecord<byte[], byte[]> producerRecord, Exception e) {
        capture(e);
        return ProductionExceptionHandlerResponse.FAIL;
    }
}
