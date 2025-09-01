package co.edu.uniquindio.proyecto.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    private String name;
    private String lastname;
    private String address;
    private String phone;

}
