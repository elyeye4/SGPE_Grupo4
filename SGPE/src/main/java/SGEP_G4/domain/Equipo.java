package SGEP_G4.domain;

import jakarta.persistence.*;
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

    @Column(name = "NOMBRE_EQUIPO")
    private String nombreEquipo;

    @Column(name = "DESCRIPCION_EQUIPO")
    private String descripcionEquipo;

    @Column(name = "RUTA_IMAGEN")
    private String rutaImagen;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO")
    private Estado estado;
}