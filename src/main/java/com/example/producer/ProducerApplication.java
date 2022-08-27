package com.example.producer;

import DTO.Data;
import DTO.Datum;
import DTO.Name;
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

        service.getData(10).forEach(producer::sendMessage);
        Data test = new Data();
        List<Datum> test1 = new ArrayList<>();
        Datum a = new Datum();
        a.setId("1");
        Name b = new Name();
        b.setFirst("Thomas");
        b.setTitle("Theodore");
        b.setLast("Blackwood");
        a.setName(b);
        test1.add(a);
        test.setData(test1);
        producer.sendMessage(test);
    }
}
