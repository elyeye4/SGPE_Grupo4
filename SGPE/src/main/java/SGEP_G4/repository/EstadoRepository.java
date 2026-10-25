package SGEP_G4.repository;

import SGEP_G4.domain.Estado;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    Optional<Estado> findByNombreEstado(String nombreEstado);
}