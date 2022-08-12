package br.com.alura.ecommerce;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;


@Slf4j
public class FraudDetectorService {

    public static void main(String[] args) {
        var fraudService = new FraudDetectorService();
        try (var service = new KafkaService<>(FraudDetectorService.class.getSimpleName(),
                "ECOMMERCE_NEW_ORDER",
                fraudService::parse,
                Order.class,
                Map.of())) {
            service.run();
        }
    }

    @SneakyThrows
    private void parse(ConsumerRecord<String, Order> rec) {
        log.info("------------------------------------------");
        log.info("Processing new order, checking for fraud");
        log.info(rec.key());
        log.info("{}", rec.value());
        log.info("{}", rec.partition());
        log.info("{}", rec.offset());
        Thread.sleep(5000);
        log.info("Order processed");
    }

}
