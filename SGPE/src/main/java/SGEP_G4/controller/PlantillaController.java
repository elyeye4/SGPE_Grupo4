package SGEP_G4.controller;

import java.security.Principal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class PlantillaController {

    @ModelAttribute
    public void agregarUsuario(Model model, Principal principal) {
        model.addAttribute("usuarioAutenticado", principal != null);

        if (principal != null) {
            model.addAttribute("nombreUsuario", principal.getName());
        }
    }
}