package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class FraudeDetectorService {
    public static void main(String[] args) {
        var fraudeService = new FraudeDetectorService();
        var service = new KafkaService(FraudeDetectorService.class.getSimpleName(),"ECOMMERCE_NEW_ORDER",fraudeService::parse);
        service.run();
    }

    public void parse(ConsumerRecord<String,String> record){
        System.out.println("Processing new order, checking for froud");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Order processed");
    }

}
