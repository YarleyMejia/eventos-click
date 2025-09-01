package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CrearCuentaDTO(

        /*if(cuenta.email().isBlank()){
            throw new Exception("El email es obligatorio");
        }

        if(cuenta.password().length() < 7){
            throw new Exception("La contraseña necesita como minimo siete caracteres");
        }
Lo que hacen las etiquetas es reemplazar los condicionales que están comentado
anteriormente */

        @NotBlank @Length(max = 10) String cedula,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(max = 10) String telefono,
        @Length(max = 100) String direccion,
        @NotBlank @Length(max = 40) @Email String email,
        @NotBlank @Length(min = 7 , max = 20) String password
        //@Past LocalDate fechaNacimiento
) {
}
