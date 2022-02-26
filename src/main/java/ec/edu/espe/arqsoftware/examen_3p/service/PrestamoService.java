package ec.edu.espe.arqsoftware.examen_3p.service;

import ec.edu.espe.arqsoftware.examen_3p.dao.PrestamoRepository;
import ec.edu.espe.arqsoftware.examen_3p.model.Prestamo;
import org.springframework.stereotype.Service;

@Service
public class PrestamoService {
    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public void realizarPrestamo(Prestamo prestamo){
        this.prestamoRepository.save(prestamo);
    }
}
