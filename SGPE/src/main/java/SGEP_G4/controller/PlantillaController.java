package SGEP_G4.controller;

import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class PlantillaController {

    @ModelAttribute
    public void agregarUsuario(Model model, Principal principal) {
        model.addAttribute("usuarioAutenticado", principal != null);

        boolean esAdmin = principal instanceof Authentication auth
                && auth.getAuthorities().stream()
                        .anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()));

        model.addAttribute("esAdmin", esAdmin);

        if (principal != null) {
            model.addAttribute("nombreUsuario", principal.getName());
        }
    }
}
