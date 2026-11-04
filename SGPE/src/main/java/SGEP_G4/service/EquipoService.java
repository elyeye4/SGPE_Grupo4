package SGEP_G4.service;

import SGEP_G4.domain.Equipo;
import SGEP_G4.repository.EquipoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

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
}
