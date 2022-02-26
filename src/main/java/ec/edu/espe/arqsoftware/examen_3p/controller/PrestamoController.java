package ec.edu.espe.arqsoftware.examen_3p.controller;

import ec.edu.espe.arqsoftware.examen_3p.model.Prestamo;
import ec.edu.espe.arqsoftware.examen_3p.service.PrestamoService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class PrestamoController {

    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final PrestamoService prestamoService;

    public PrestamoController(KafkaTemplate<String, Object> kafkaTemplate, PrestamoService prestamoService) {
        this.kafkaTemplate = kafkaTemplate;
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public void publish(@RequestBody Prestamo prestamo){
        kafkaTemplate.send("prestamos", prestamo);
    }

    @PostMapping("prestamo")
    public void sendMessagge(@RequestBody Prestamo prestamo){
        this.prestamoService.realizarPrestamo(prestamo);
    }
}
