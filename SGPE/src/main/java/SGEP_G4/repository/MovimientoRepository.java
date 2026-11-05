package SGEP_G4.repository;

import SGEP_G4.domain.Movimiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

    List<Movimiento> findByPrestamo_IdPrestamoOrderByFechaMovimientoAsc(Integer idPrestamo);
}
