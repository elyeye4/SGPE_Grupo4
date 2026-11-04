package SGEP_G4.repository;

import SGEP_G4.domain.Equipo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

    List<Equipo> findByNombreEquipoContainingIgnoreCase(String nombreEquipo);
}
