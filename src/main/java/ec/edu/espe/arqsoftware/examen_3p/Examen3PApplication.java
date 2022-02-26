package ec.edu.espe.arqsoftware.examen_3p;

import ec.edu.espe.arqsoftware.examen_3p.model.Prestamo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@SpringBootApplication
@Slf4j
public class Examen3PApplication {

    public static void main(String[] args) {
        SpringApplication.run(Examen3PApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, Object> kafkaTemplate) {

        return args -> {
            Prestamo prestamo = new Prestamo();
            for (int i = 0; i < 10; i++) {
                double opt1 = 100000 * Math.random();
                String idOpt = String.valueOf(opt1);
                double opt2 = 100 * Math.random();
                BigDecimal value = new BigDecimal(opt2);
                prestamo.setCodigoPrestamo(idOpt);
                prestamo.setValorPago(value);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                prestamo.setFechaPago(LocalDateTime.now());
                String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
                prestamo.setHoraPago(timeStamp);
                log.info("Prestamo: {}", prestamo);
                ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send("prestamos", prestamo);
                send.addCallback(new KafkaSendCallback<String, Object>() {
                    @Override
                    public void onSuccess(SendResult<String, Object> result) {
                        log.info("Mensaje enviado con información del prestamo: {}",result.getRecordMetadata());
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("Error al enviar el mensaje del prestamo{}",ex);
                    }

                    @Override
                    public void onFailure(KafkaProducerException ex) {
                        log.error("Error al enviar el mensaje del prestamo{}",ex);
                    }

                });
            }
        };
    }
}
