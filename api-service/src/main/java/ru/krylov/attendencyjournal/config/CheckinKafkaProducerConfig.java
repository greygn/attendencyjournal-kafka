package ru.krylov.attendencyjournal.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.krylov.attendencyjournal.dto.CheckinRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация для Kafka продюсера.
 * 
 * Создает KafkaTemplate для отправки сообщений типа CheckinRequest в Kafka.
 */
@Configuration
public class CheckinKafkaProducerConfig {

    /**
     * Создает KafkaTemplate для отправки сообщений о присутствии.
     * 
     * Конфигурирует:
     * - KEY_SERIALIZER_CLASS_CONFIG: StringSerializer (для partition key)
     * - VALUE_SERIALIZER_CLASS_CONFIG: JsonSerializer (для CheckinRequest)
     * - ADD_TYPE_INFO_HEADERS: false (не добавляем информацию о типе)
     * 
     * @param bootstrapServers адреса Kafka брокеров
     * @return KafkaTemplate для отправки CheckinRequest
     */
    @Bean
    public KafkaTemplate<String, CheckinRequest> kafkaTemplate(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(config));
    }
}
