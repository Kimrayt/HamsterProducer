package com.example.producer;

import com.example.producer.Kafka.MessageProducer;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@EnableScheduling
public class ProducerApplication {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(ProducerApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);

        Resource resource = new ClassPathResource("kafkaTest.json");
        String jsonHamsterItem = FileUtils.readFileToString(resource.getFile(), StandardCharsets.UTF_8);
        producer.sendMessage("GetAllHamsters", "2");
    }
}
