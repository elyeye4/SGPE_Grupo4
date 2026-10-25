package SGEP_G4.service;

import SGEP_G4.domain.Prestamo;
import SGEP_G4.repository.PrestamoRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import SGEP_G4.repository.EstadoRepository;
import SGEP_G4.repository.EquipoRepository;
import SGEP_G4.repository.UsuarioRepository;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EquipoRepository equipoRepository;
    private final EstadoRepository estadoRepository;

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

    @Transactional
    public Prestamo guardarSolicitud(
            Prestamo prestamo,
            String username,
            Integer idEquipo) {

        var usuario = usuarioRepository.findByUsername(username)
                .orElseThrow();

        var equipo = equipoRepository.findById(idEquipo)
                .orElseThrow();

        var estado = estadoRepository.findByNombreEstado("PENDIENTE")
                .orElseThrow();

        prestamo.setUsuarioSolicitante(usuario);
        prestamo.setEquipo(equipo);
        prestamo.setEstado(estado);
        prestamo.setFechaSolicitud(LocalDateTime.now());

        return prestamoRepository.save(prestamo);
    }
}
