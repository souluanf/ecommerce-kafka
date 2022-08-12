package br.com.alura.ecommerce;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

@Slf4j
public class EmailService {
    public static void main(String[] args) {
        var emailService = new EmailService();
        try (KafkaService<String> service = new KafkaService<>(EmailService.class.getSimpleName(),
                "ECOMMERCE_SEND_EMAIL",
                emailService::parse,
                String.class,
                Map.of())) {
            service.run();
        }
    }
    @SneakyThrows
    private void parse(ConsumerRecord<String, String> rec) {
        log.info("------------------------------------------");
        log.info("Send email");
        log.info(rec.key());
        log.info(rec.value());
        log.info("{}",rec.partition());
        log.info("{}",rec.offset());
        Thread.sleep(1000);
        log.info("Email sent");
    }
}
