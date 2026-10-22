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
@Table(name = "FIDE_USUARIO_TB")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "CONTRASENA")
    private String contrasena;

    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;

    @Column(name = "APELLIDO1")
    private String apellido1;

    @Column(name = "APELLIDO2")
    private String apellido2;

    @Column(name = "CORREO")
    private String correo;

    @Column(name = "TELEFONO")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "ID_ROL")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO")
    private Estado estado;
}