package co.edu.uniquindio.proyecto.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "role") // Define la colección de MongoDB
public class RoleEntity {

    @Id

    private String idRole;
    private String name;
    private String description;
}
