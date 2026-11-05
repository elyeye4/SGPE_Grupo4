package SGEP_G4.controller;

import SGEP_G4.service.PrestamoService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import SGEP_G4.domain.Prestamo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequiredArgsConstructor
@RequestMapping("/solicitud")
public class SolicitudController {

    private final PrestamoService prestamoService;

    @GetMapping("/mis-solicitudes")
    public String misSolicitudes(Model model, Principal principal) {
        var solicitudes
                = prestamoService.getMisSolicitudes(principal.getName());

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

    @GetMapping("/nueva/{idEquipo}")
    public String nuevaSolicitud(
            @PathVariable Integer idEquipo,
            Model model) {

        model.addAttribute("prestamo", new Prestamo());
        model.addAttribute("idEquipo", idEquipo);

        return "solicitud/formulario";
    }

    @PostMapping("/guardar")
    public String guardarSolicitud(
            @Valid Prestamo prestamo,
            BindingResult result,
            @RequestParam Integer idEquipo,
            Principal principal,
            Model model) {

        if (prestamo.getFechaInicio() != null
                && prestamo.getFechaDevolucionLimite() != null
                && prestamo.getFechaDevolucionLimite()
                        .isBefore(prestamo.getFechaInicio())) {

            model.addAttribute(
                    "errorFecha",
                    "La fecha de devolución no puede ser anterior a la fecha de inicio.");

            model.addAttribute("idEquipo", idEquipo);

            return "solicitud/formulario";
        }

        if (result.hasErrors()) {
            model.addAttribute("idEquipo", idEquipo);
            return "solicitud/formulario";
        }

        prestamoService.guardarSolicitud(
                prestamo,
                principal.getName(),
                idEquipo);

        return "redirect:/solicitud/mis-solicitudes";
    }
}
