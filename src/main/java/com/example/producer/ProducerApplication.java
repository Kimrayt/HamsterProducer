package com.example.producer;

import DTO.Data;
import DTO.Datum;
import DTO.Name;
import Model.Hamster;
import com.example.producer.Kafka.MessageProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class ProducerApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ProducerApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);
        ApiService service = context.getBean(ApiServiceImpl.class);

        Hamster tom = new Hamster(1, "Thomas", 1.0);
        Hamster theo = new Hamster(2, "Theodore", 2.0);
        Hamster black = new Hamster(3, "Blackwood", 3.0);
        producer.sendMessage(tom);
        producer.sendMessage(theo);
        producer.sendMessage(black);
    }
}
