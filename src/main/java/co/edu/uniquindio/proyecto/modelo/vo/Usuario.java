package co.edu.uniquindio.proyecto.modelo.vo;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("usuarios")
@Getter
@Setter
@NoArgsConstructor

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    
    private String telefono;
    private String direccion;
    private String nombre;
    private String cedula;

    public Usuario(String telefono, String direccion, String nombre, String cedula) {
        this.telefono = telefono;
        this.direccion = direccion;
        this.nombre = nombre;
        this.cedula = cedula;
    }
}
