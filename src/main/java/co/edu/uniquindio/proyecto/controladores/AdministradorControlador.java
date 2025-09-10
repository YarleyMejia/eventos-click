package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.request.ChangePasswordRequestDto;
import co.edu.uniquindio.proyecto.dto.request.LoginRequestDto;
import co.edu.uniquindio.proyecto.dto.request.UserInformationUpdateDto;
import co.edu.uniquindio.proyecto.dto.response.TokenRespuesta;
import co.edu.uniquindio.proyecto.dto.response.UserResponseDto;
import co.edu.uniquindio.proyecto.servicios.implementacion.AdministradorServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class AdministradorControlador {

    private final AdministradorServicio administradorServicio;

    @PostMapping("/login")
    public ResponseEntity<TokenRespuesta> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(administradorServicio.login(loginRequestDto));
    }

    @PutMapping("/updateInformation")
    public ResponseEntity<UserResponseDto> informationUpdate(@RequestHeader("Authorization") String token, @RequestBody UserInformationUpdateDto userUpdate) {
        return ResponseEntity.ok(administradorServicio.update(userUpdate, token));
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<String> changePassword(@RequestHeader("Authorization") String token,
                                                 @RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        return ResponseEntity.ok(administradorServicio.changePassword(changePasswordRequestDto, token));
    }

}
