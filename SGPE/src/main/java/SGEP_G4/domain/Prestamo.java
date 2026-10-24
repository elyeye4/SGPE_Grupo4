package SGEP_G4.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "FIDE_PRESTAMO_TB")
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRESTAMO")
    private Integer idPrestamo;

    @Column(name = "FECHA_SOLICITUD")
    private LocalDateTime fechaSolicitud;

    @Column(name = "FECHA_INICIO")
    private LocalDate fechaInicio;

    @Column(name = "FECHA_DEVOLUCION_LIMITE")
    private LocalDate fechaDevolucionLimite;

    @Column(name = "FECHA_APROBACION")
    private LocalDateTime fechaAprobacion;

    @Column(name = "FECHA_ENTREGA")
    private LocalDateTime fechaEntrega;

    @Column(name = "OBSERVACIONES_PRESTAMO")
    private String observacionesPrestamo;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_SOLICITANTE")
    private Usuario usuarioSolicitante;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_ADMINISTRADOR")
    private Usuario usuarioAdministrador;

    @ManyToOne
    @JoinColumn(name = "ID_EQUIPO")
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO")
    private Estado estado;
}