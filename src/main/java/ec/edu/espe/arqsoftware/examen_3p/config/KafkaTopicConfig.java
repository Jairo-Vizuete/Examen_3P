package ec.edu.espe.arqsoftware.examen_3p.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic amigoscodeTopic() {
        return TopicBuilder.name("prestamos").build();
    }
}
