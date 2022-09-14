package com.example.producer;

import Model.Hamster;
import com.example.producer.Kafka.MessageProducer;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@EnableScheduling
public class ProducerApplication {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(ProducerApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);
        ApiService service = context.getBean(ApiServiceImpl.class);

        Hamster tom = new Hamster(1, "Thomas", 1.0);
        Hamster theo = new Hamster(2, "Theodore", 2.0);
        Hamster black = new Hamster(3, "Blackwood", 3.0);

        Resource resource = new ClassPathResource("kafkaTest.json");
        String jsonHamsterItem = FileUtils.readFileToString(resource.getFile(), StandardCharsets.UTF_8);
        producer.sendMessage("SaveHamsters", jsonHamsterItem);
    }
}
