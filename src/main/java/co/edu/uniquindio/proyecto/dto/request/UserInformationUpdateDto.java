package co.edu.uniquindio.proyecto.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationUpdateDto {

    private String email;
    private String name;
    private String lastname;
    private String address;
    private String phone;

}
