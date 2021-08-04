package br.com.alura.ecommerce;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        try(var dipatcher = new KafkaDisptcher()){
            for(int i = 1; i<=10;i++){
                //envio de evento de pedido
                var key = UUID.randomUUID().toString();
                var value = key+" 123,321,456";
                dipatcher.send("ECOMMERCE_NEW_ORDER",key,value);

                //envio de evento de email
                var email = "Enviando email 123564 wdhcdbhb" + "";
                dipatcher.send("ECOMMERCE_SEND_EMAIL",key,email);
            }
        }
    }
}
