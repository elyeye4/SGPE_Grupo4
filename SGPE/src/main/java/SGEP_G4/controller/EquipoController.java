package SGEP_G4.controller;

import SGEP_G4.domain.Equipo;
import SGEP_G4.service.EquipoService;
import jakarta.validation.Valid;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/equipo")
public class EquipoController {

    private final EquipoService equipoService;
    private final MessageSource messageSource;

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

    @GetMapping("/disponibilidad/{idEquipo}")
    public String disponibilidad(@PathVariable Integer idEquipo, Model model) {

        var equipo = equipoService.getEquipo(idEquipo);

        if (equipo.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("equipo", equipo.get());

        return "equipo/disponibilidad";
    }

    // HU16 - Formulario para registrar un equipo nuevo
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("equipo", new Equipo());
        cargarCatalogos(model);
        return "equipo/formulario";
    }

    // HU17 - Formulario para editar un equipo existente
    @GetMapping("/modificar/{idEquipo}")
    public String modificar(@PathVariable Integer idEquipo, Model model) {
        var equipo = equipoService.getEquipo(idEquipo);

        if (equipo.isEmpty()) {
            return "redirect:/equipo/listado";
        }

        model.addAttribute("equipo", equipo.get());
        cargarCatalogos(model);
        return "equipo/formulario";
    }

    // Ruta unificada para registrar (sin id) o modificar (con id) equipos
    @PostMapping("/guardar")
    public String guardar(@Valid Equipo equipo,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        boolean esNuevo = equipo.getIdEquipo() == null;

        if (equipo.getCategoria() == null
                || equipo.getCategoria().getIdCategoria() == null) {
            result.rejectValue("categoria", "equipo.error.categoria");
        }

        if (!esNuevo && (equipo.getEstado() == null
                || equipo.getEstado().getIdEstado() == null)) {
            result.rejectValue("estado", "equipo.error.estado");
        }

        if (result.hasErrors()) {
            cargarCatalogos(model);
            return "equipo/formulario";
        }

        String titulo = "todoOk";
        String mensaje = esNuevo ? "equipo.registrado" : "equipo.actualizado";

        try {
            equipoService.save(equipo);
        } catch (IllegalArgumentException e) {
            titulo = "error";
            mensaje = "equipo.error.guardar";
        }

        redirectAttributes.addAttribute(titulo,
                messageSource.getMessage(mensaje, null, Locale.getDefault()));
        return "redirect:/equipo/listado";
    }

    private void cargarCatalogos(Model model) {
        model.addAttribute("categorias", equipoService.getCategorias());
        model.addAttribute("estados", equipoService.getEstadosEquipo());
    }
}
