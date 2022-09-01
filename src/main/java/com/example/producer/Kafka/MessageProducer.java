package com.example.producer.Kafka;

import DTO.Data;
import Model.Hamster;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@NoArgsConstructor
@Component
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, Hamster> kafkaTemplate;
    @Value(value = "${kafka.topic.name}")
    private String topicName;


    public void sendMessage(Hamster hamster) {
        ListenableFuture<SendResult<String, Hamster>> future = kafkaTemplate.send(topicName, hamster);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Hamster>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Unable to send message = {} dut to: {}", hamster, throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Hamster> stringDataSendResult) {
                log.info("Sent Message = {} with offset = {}", hamster, stringDataSendResult.getRecordMetadata().offset());
            }
        });
    }
}
