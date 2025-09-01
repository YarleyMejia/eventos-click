package co.edu.uniquindio.proyecto.dto;
/*
DTO: son objetos con la información especificca en un contexto dado.
Solo existen para encapsular información(datos).
 */
public record RegistroClienteDTO (
     String cedula,
     String nombre,
     String telefono,
     String direccion,
     String correo,
     String password
){
    
 }
