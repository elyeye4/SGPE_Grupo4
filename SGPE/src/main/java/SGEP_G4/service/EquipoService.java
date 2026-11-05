package SGEP_G4.service;

import SGEP_G4.domain.Equipo;
import SGEP_G4.repository.EquipoRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository equipoRepository;

    @Transactional(readOnly = true)
    public Optional<Equipo> getEquipo(Integer idEquipo) {
        return equipoRepository.findById(idEquipo);
    }
}