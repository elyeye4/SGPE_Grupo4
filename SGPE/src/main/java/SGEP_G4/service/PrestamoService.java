package SGEP_G4.service;

import SGEP_G4.domain.Prestamo;
import SGEP_G4.repository.PrestamoRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import SGEP_G4.domain.Movimiento;
import SGEP_G4.repository.EstadoRepository;
import SGEP_G4.repository.EquipoRepository;
import SGEP_G4.repository.MovimientoRepository;
import SGEP_G4.repository.UsuarioRepository;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EquipoRepository equipoRepository;
    private final EstadoRepository estadoRepository;
    private final MovimientoRepository movimientoRepository;

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

    // HU18 - Solicitudes que esperan revisión del administrador
    @Transactional(readOnly = true)
    public List<Prestamo> getSolicitudesPendientes() {
        return prestamoRepository
                .findByEstado_NombreEstadoOrderByFechaSolicitudAsc("PENDIENTE");
    }

    // HU18 - Aprobar una solicitud de préstamo.
    // Si no se puede aprobar lanza IllegalStateException cuyo mensaje es
    // la clave de messages.properties que explica el motivo.
    @Transactional
    public Prestamo aprobarSolicitud(Integer idPrestamo, String usernameAdmin) {

        var prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new IllegalStateException(
                "prestamo.error.noExiste"));

        if (!"PENDIENTE".equals(prestamo.getEstado().getNombreEstado())) {
            throw new IllegalStateException("prestamo.error.noPendiente");
        }

        var equipo = prestamo.getEquipo();

        if (equipo.getEstado() == null
                || !"DISPONIBLE".equals(equipo.getEstado().getNombreEstado())) {
            throw new IllegalStateException(
                    "prestamo.error.equipoNoDisponible");
        }

        var administrador = usuarioRepository.findByUsername(usernameAdmin)
                .orElseThrow();

        var aprobado = estadoRepository.findByNombreEstado("APROBADO")
                .orElseThrow();

        var prestado = estadoRepository.findByNombreEstado("PRESTADO")
                .orElseThrow();

        var ahora = LocalDateTime.now();

        prestamo.setEstado(aprobado);
        prestamo.setFechaAprobacion(ahora);
        prestamo.setUsuarioAdministrador(administrador);

        // El equipo queda reservado para este préstamo
        equipo.setEstado(prestado);
        equipoRepository.save(equipo);

        var movimiento = new Movimiento();
        movimiento.setPrestamo(prestamo);
        movimiento.setUsuario(administrador);
        movimiento.setTipoMovimiento("SOLICITUD_APROBADA");
        movimiento.setDescripcionMovimiento(
                "El administrador aprobó la solicitud de préstamo.");
        movimiento.setEstado(aprobado);
        movimiento.setFechaMovimiento(ahora);
        movimientoRepository.save(movimiento);

        return prestamoRepository.save(prestamo);
    }
}
