package co.edu.uniquindio.proyecto.modelo.documentos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("eventos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Evento {
    @Id
    private String id;
    private String nombre;
    private String descripcion;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaEvento;
    private String ciudad;
}
