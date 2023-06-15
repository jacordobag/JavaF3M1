package modulo1fase3.proyecto.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "cliente")
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "correo_contacto", nullable = false)
    private String correoContacto;

    @Column(name = "numero_empleados")
    private String numeroEmpleados;

    private String direccion;

}
