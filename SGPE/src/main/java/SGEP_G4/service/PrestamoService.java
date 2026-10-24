package SGEP_G4.service;

import SGEP_G4.domain.Prestamo;
import SGEP_G4.repository.PrestamoRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;

    @Transactional(readOnly = true)
    public List<Prestamo> getMisSolicitudes(String username) {
        return prestamoRepository
                .findByUsuarioSolicitante_UsernameOrderByFechaSolicitudDesc(
                        username);
    }

    @Transactional(readOnly = true)
    public Optional<Prestamo> getMiSolicitud(
            Integer idPrestamo, String username) {

        return prestamoRepository
                .findByIdPrestamoAndUsuarioSolicitante_Username(
                        idPrestamo, username);
    }
}