package ec.edu.espe.arqsoftware.examen_3p.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @Column(name = "cod_prestamo", nullable = false)
    private String codigoPrestamo;

    @Column(name = "valor_pago", nullable = false, length = 50)
    private BigDecimal valorPago;

    @Column(name = "fecha_pago", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDateTime fechaPago;

    @Column(name = "hora_pago", nullable = false)
    private String horaPago;

    @Column(name = "num_cuota", nullable = false)
    private BigDecimal cuota;

}
