package SGEP_G4.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "FIDE_MOVIMIENTO_TB")
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MOVIMIENTO")
    private Integer idMovimiento;

    @ManyToOne
    @JoinColumn(name = "ID_PRESTAMO")
    private Prestamo prestamo;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @Column(name = "TIPO_MOVIMIENTO", nullable = false, length = 50)
    private String tipoMovimiento;

    @Column(name = "DESCRIPCION_MOVIMIENTO", length = 250)
    private String descripcionMovimiento;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO")
    private Estado estado;

    @Column(name = "FECHA_MOVIMIENTO")
    private LocalDateTime fechaMovimiento;
}
