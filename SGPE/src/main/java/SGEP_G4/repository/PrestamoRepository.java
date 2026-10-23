package SGEP_G4.repository;

import SGEP_G4.domain.Prestamo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    List<Prestamo> findByUsuarioSolicitante_UsernameOrderByFechaSolicitudDesc(
            String username);

    Optional<Prestamo> findByIdPrestamoAndUsuarioSolicitante_Username(
            Integer idPrestamo, String username);
}