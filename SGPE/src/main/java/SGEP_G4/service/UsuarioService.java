package SGEP_G4.service;

import SGEP_G4.domain.Usuario;
import SGEP_G4.repository.UsuarioRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Optional<Usuario> getUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        var usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(()
                        -> new UsernameNotFoundException("Usuario no encontrado"));

        var rol = usuario.getRol();

        if (rol == null || rol.getNombreRol() == null) {
            throw new UsernameNotFoundException("Usuario sin rol asignado");
        }

        boolean usuarioActivo = usuario.getEstado() != null
                && "ACTIVO".equals(usuario.getEstado().getNombreEstado());

        boolean rolActivo = rol.getEstado() != null
                && "ACTIVO".equals(rol.getEstado().getNombreEstado());

        return User.withUsername(usuario.getUsername())
                .password(usuario.getContrasena())
                .roles(rol.getNombreRol())
                .disabled(!usuarioActivo || !rolActivo)
                .build();
    }
}
