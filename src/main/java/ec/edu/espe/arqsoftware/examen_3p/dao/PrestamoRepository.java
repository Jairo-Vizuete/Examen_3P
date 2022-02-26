package ec.edu.espe.arqsoftware.examen_3p.dao;

import ec.edu.espe.arqsoftware.examen_3p.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

}
