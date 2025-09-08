package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.request.ChangePasswordRequestDto;
import co.edu.uniquindio.proyecto.dto.request.LoginRequestDto;
import co.edu.uniquindio.proyecto.dto.request.UserInformationUpdateDto;
import co.edu.uniquindio.proyecto.dto.response.TokenRespuesta;
import co.edu.uniquindio.proyecto.dto.response.UserResponseDto;
import co.edu.uniquindio.proyecto.servicios.implementacion.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de usuarios administradores.
 *
 * Proporciona endpoints para:
 * - Login
 * - Actualización de información del usuario
 * - Cambio de contraseña
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class AdministradorControlador {
    
    private final AdministradorServicio administradorServicio;

    /**
     * Endpoint para iniciar sesión de un usuario administrador.
     *
     * @param loginRequestDto DTO con email y contraseña
     * @return TokenRespuesta con JWT si las credenciales son correctas
     */
    @PostMapping("/login")
    public ResponseEntity<TokenRespuesta> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(administradorServicio.login(loginRequestDto));
    }

    /**
     * Endpoint para actualizar información del usuario.
     * Requiere token de autorización en el header.
     *
     * @param token Token JWT del usuario
     * @param userUpdate DTO con la información a actualizar
     * @return UserResponseDto con los datos actualizados
     */
    @PutMapping("/updateInformation")
    public ResponseEntity<UserResponseDto> informationUpdate(@RequestHeader("Authorization") String token,
                                                             @RequestBody UserInformationUpdateDto userUpdate) {
        return ResponseEntity.ok(administradorServicio.update(userUpdate, token));
    }

    /**
     * Endpoint para cambiar la contraseña del usuario.
     * Requiere token de autorización en el header.
     *
     * @param token Token JWT del usuario
     * @param changePasswordRequestDto DTO con la contraseña actual y la nueva
     * @return Mensaje de éxito o error
     */
    @PutMapping("/updatePassword")
    public ResponseEntity<String> changePassword(@RequestHeader("Authorization") String token,
                                                 @RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        return ResponseEntity.ok(administradorServicio.changePassword(changePasswordRequestDto, token));
    }
}
