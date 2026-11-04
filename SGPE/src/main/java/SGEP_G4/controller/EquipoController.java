package SGEP_G4.controller;

import SGEP_G4.service.EquipoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/equipo")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping("/listado")
    public String listado(@RequestParam(name = "buscar", required = false) String buscar,
                          Model model) {
        String texto = buscar == null ? "" : buscar.trim();

        model.addAttribute("equipos", texto.isEmpty()
                ? equipoService.getEquipos()
                : equipoService.buscarEquipos(texto));
        model.addAttribute("buscar", texto);
        return "equipo/listado";
    }

    @GetMapping("/detalle/{idEquipo}")
    public String detalle(@PathVariable Integer idEquipo, Model model) {
        var equipo = equipoService.getEquipo(idEquipo);

        if (equipo.isEmpty()) {
            return "redirect:/equipo/listado";
        }

        model.addAttribute("equipo", equipo.get());
        return "equipo/detalle";
    }
}
