package SGEP_G4.controller;

import SGEP_G4.service.PrestamoService;
import java.security.Principal;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Gestión de préstamos por parte del administrador
@Controller
@RequiredArgsConstructor
@RequestMapping("/prestamo")
public class PrestamoController {

    private final PrestamoService prestamoService;
    private final MessageSource messageSource;

    @GetMapping("/pendientes")
    public String pendientes(Model model) {
        var solicitudes = prestamoService.getSolicitudesPendientes();
        model.addAttribute("solicitudes", solicitudes);
        model.addAttribute("totalPendientes", solicitudes.size());
        return "prestamo/pendientes";
    }

    // HU18 - Aprobar préstamo
    @PostMapping("/aprobar")
    public String aprobar(@RequestParam Integer idPrestamo,
            Principal principal,
            RedirectAttributes redirectAttributes) {

        String titulo = "todoOk";
        String mensaje = "prestamo.aprobado";

        try {
            prestamoService.aprobarSolicitud(idPrestamo, principal.getName());
        } catch (IllegalStateException e) {
            // El servicio indica el motivo con la clave del mensaje
            titulo = "error";
            mensaje = e.getMessage();
        }

        redirectAttributes.addAttribute(titulo,
                messageSource.getMessage(mensaje, null, Locale.getDefault()));
        return "redirect:/prestamo/pendientes";
    }
}
