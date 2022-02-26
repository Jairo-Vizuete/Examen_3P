package ec.edu.espe.arqsoftware.examen_3p;

import ec.edu.espe.arqsoftware.examen_3p.controller.PrestamoController;
import ec.edu.espe.arqsoftware.examen_3p.model.Prestamo;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.client.RestTemplate;

public class KafkaListeners {
    private final PrestamoController messageController;
    private RestTemplate restTemplate = new RestTemplate();

    public KafkaListeners(PrestamoController messageController) {
        this.messageController = messageController;
    }

    @KafkaListener(topics = "prestamos",groupId = "groupId")
    public void Listener(Prestamo prestamo){
        this.restTemplate.postForObject("http://localhost:8080/api/v1/prestamo", prestamo, Prestamo.class);
    }
}
