package co.edu.uniquindio.proyecto.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenRespuesta {

    private String token;
    private String role;
    private String name;
    private String lastname;
    private String email;
}