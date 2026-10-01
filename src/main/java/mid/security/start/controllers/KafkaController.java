package mid.security.start.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/kafka")
@RequiredArgsConstructor
public class KafkaController {

    public final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping
    public ResponseEntity<?> kafkaSend(@RequestBody String message) {
        System.out.println("LOGGER: KAFKA CONTROLLER");
        kafkaTemplate.send("sandbox", message);
        return ResponseEntity.ok(message);
    }
}
