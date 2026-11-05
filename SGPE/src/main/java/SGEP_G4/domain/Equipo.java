package SGEP_G4.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "FIDE_EQUIPO_TB")
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EQUIPO")
    private Integer idEquipo;

    @Column(name = "NOMBRE_EQUIPO", nullable = false, length = 80)
    @NotBlank
    @Size(max = 80)
    private String nombreEquipo;

    @Column(name = "DESCRIPCION_EQUIPO", length = 250)
    @Size(max = 250)
    private String descripcionEquipo;

    @Column(name = "RUTA_IMAGEN", length = 1024)
    @Size(max = 1024)
    private String rutaImagen;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO")
    private Estado estado;
}
