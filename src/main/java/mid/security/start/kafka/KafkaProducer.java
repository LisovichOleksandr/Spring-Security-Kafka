package mid.security.start.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    public final KafkaTemplate<String, String > kafkaTemplate;

    public void send(String message) {
        kafkaTemplate.send("sandbox", message);
    }

}
