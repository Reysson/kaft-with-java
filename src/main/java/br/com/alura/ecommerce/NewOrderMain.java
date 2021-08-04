package br.com.alura.ecommerce;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        try(var orderDispatcher = new KafkaDisptcher<Order>()){
            try(var emailDispatcher = new KafkaDisptcher<String>()){
                for(int i = 1; i<=10;i++){
                    //envio de evento de pedido
                    var userId = UUID.randomUUID().toString();
                    var orderId = UUID.randomUUID().toString();
                    var amount = new BigDecimal(Math.random() * 5000 + 1);
                    var order = new Order(userId, orderId, amount);
                    orderDispatcher.send("ECOMMERCE_NEW_ORDER",userId,order);

                    //envio de evento de email
                    var email = "Enviando email 123564 wdhcdbhb" + "";
                    emailDispatcher.send("ECOMMERCE_SEND_EMAIL",userId,email);
                }
            }

        }
    }
}
