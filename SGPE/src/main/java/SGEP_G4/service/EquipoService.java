package SGEP_G4.service;

import SGEP_G4.domain.Categoria;
import SGEP_G4.domain.Equipo;
import SGEP_G4.domain.Estado;
import SGEP_G4.repository.CategoriaRepository;
import SGEP_G4.repository.EquipoRepository;
import SGEP_G4.repository.EstadoRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EquipoService {

    // Estados que puede tener un equipo (el resto aplica a préstamos u otros registros)
    public static final List<String> ESTADOS_EQUIPO
            = List.of("DISPONIBLE", "PRESTADO", "INACTIVO");

    private final EquipoRepository equipoRepository;
    private final CategoriaRepository categoriaRepository;
    private final EstadoRepository estadoRepository;

    @Transactional(readOnly = true)
    public List<Equipo> getEquipos() {
        return equipoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Equipo> buscarEquipos(String nombre) {
        return equipoRepository.findByNombreEquipoContainingIgnoreCase(nombre);
    }

    @Transactional(readOnly = true)
    public Optional<Equipo> getEquipo(Integer idEquipo) {
        return equipoRepository.findById(idEquipo);
    }

    @Transactional(readOnly = true)
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAllByOrderByNombreCategoriaAsc();
    }

    @Transactional(readOnly = true)
    public List<Estado> getEstadosEquipo() {
        return estadoRepository.findByNombreEstadoInOrderByIdEstadoAsc(ESTADOS_EQUIPO);
    }

    // Si el idEquipo está vacío se registra un equipo nuevo (HU16),
    // si tiene un valor se actualiza el existente (HU17).
    @Transactional
    public Equipo save(Equipo equipo) {
        var categoria = categoriaRepository
                .findById(equipo.getCategoria().getIdCategoria())
                .orElseThrow(() -> new IllegalArgumentException(
                "La categoría no existe: " + equipo.getCategoria().getIdCategoria()));

        equipo.setCategoria(categoria);
        equipo.setNombreEquipo(equipo.getNombreEquipo().trim());
        equipo.setDescripcionEquipo(vacioANulo(equipo.getDescripcionEquipo()));
        equipo.setRutaImagen(vacioANulo(equipo.getRutaImagen()));

        if (equipo.getIdEquipo() == null) {
            // Todo equipo nuevo queda disponible para préstamo
            equipo.setEstado(estadoRepository.findByNombreEstado("DISPONIBLE")
                    .orElseThrow());
        } else {
            if (!equipoRepository.existsById(equipo.getIdEquipo())) {
                throw new IllegalArgumentException(
                        "El equipo no existe: " + equipo.getIdEquipo());
            }

            var estado = estadoRepository
                    .findById(equipo.getEstado().getIdEstado())
                    .filter(e -> ESTADOS_EQUIPO.contains(e.getNombreEstado()))
                    .orElseThrow(() -> new IllegalArgumentException(
                    "Estado no válido para un equipo: " + equipo.getEstado().getIdEstado()));

            equipo.setEstado(estado);
        }

        return equipoRepository.save(equipo);
    }

    private String vacioANulo(String texto) {
        return texto == null || texto.isBlank() ? null : texto.trim();
    }
}
