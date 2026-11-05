package SGEP_G4.controller;

import SGEP_G4.service.EquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/equipo")
public class EquipoController {

    private final EquipoService equipoService;

    @GetMapping("/disponibilidad/{idEquipo}")
    public String disponibilidad(@PathVariable Integer idEquipo, Model model) {

        var equipo = equipoService.getEquipo(idEquipo);

        if (equipo.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("equipo", equipo.get());

        return "equipo/disponibilidad";
    }
}