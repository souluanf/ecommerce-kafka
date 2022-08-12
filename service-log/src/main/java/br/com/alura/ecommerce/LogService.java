package br.com.alura.ecommerce;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Map;
import java.util.regex.Pattern;


@Slf4j
public class LogService {

    public static void main(String[] args) {
        var logService = new LogService();
        try (KafkaService<String> service = new KafkaService<>(
                LogService.class.getSimpleName(),
                Pattern.compile("ECOMMERCE.*"),
                logService::parse,
                String.class,
                Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()))) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, String> rec) {
        log.info("------------------------------------------");
        log.info("LOG: " + rec.topic());
        log.info(rec.key());
        log.info(rec.value());
        log.info("{}", rec.partition());
        log.info("{}", rec.offset());
    }
}
