package SGEP_G4.controller;

import SGEP_G4.service.PrestamoService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/solicitud")
public class SolicitudController {

    private final PrestamoService prestamoService;

    @GetMapping("/mis-solicitudes")
    public String misSolicitudes(Model model, Principal principal) {
        var solicitudes =
                prestamoService.getMisSolicitudes(principal.getName());

        model.addAttribute("solicitudes", solicitudes);

        return "solicitud/listado";
    }

    @GetMapping("/detalle/{idPrestamo}")
    public String detalle(
            @PathVariable Integer idPrestamo,
            Model model,
            Principal principal) {

        var solicitud = prestamoService.getMiSolicitud(
                idPrestamo, principal.getName());

        if (solicitud.isEmpty()) {
            return "redirect:/solicitud/mis-solicitudes";
        }

        model.addAttribute("solicitud", solicitud.get());

        return "solicitud/detalle";
    }
}