package co.edu.uniquindio.proyecto.servicios.implementacion;


import co.edu.uniquindio.proyecto.Entity.UserEntity;
import co.edu.uniquindio.proyecto.configuracion.security.JwtService;
import co.edu.uniquindio.proyecto.dto.request.ChangePasswordRequestDto;
import co.edu.uniquindio.proyecto.dto.request.LoginRequestDto;
import co.edu.uniquindio.proyecto.dto.request.UserInformationUpdateDto;
import co.edu.uniquindio.proyecto.dto.response.TokenRespuesta;
import co.edu.uniquindio.proyecto.dto.response.UserResponseDto;
import co.edu.uniquindio.proyecto.excepciones.dataError.EmailNotFound;
import co.edu.uniquindio.proyecto.excepciones.dataError.InvalidPasswordException;
import co.edu.uniquindio.proyecto.excepciones.dataError.InvalidTokenException;
import co.edu.uniquindio.proyecto.repositorios.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdministradorServicio {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TokenRespuesta login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        UserEntity user = userRepository.findByEmail(loginRequestDto.getEmail());
        String token = jwtService.getToken(user);

        return TokenRespuesta.builder()
                .token(token)
                .role(user.getRole().getName())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();
    }


    public UserResponseDto update(UserInformationUpdateDto userUpdate, String token) {

        String emailFromToken = jwtService.getEmailFromToken(token);

        if (!emailFromToken.equals(userUpdate.getEmail())) {
            throw new InvalidTokenException("El token no corresponde al usuario");
        }

        UserEntity user = userRepository.findByEmail(userUpdate.getEmail());

        if(user == null){
            throw new EmailNotFound("No se ha encontrado un usuario con este email");
        }

        if(userUpdate.getName() != null){
            user.setName(userUpdate.getName());
        }

        if(userUpdate.getAddress() != null){
            user.setAddress(userUpdate.getAddress());
        }

        if(userUpdate.getPhone() != null){
            user.setPhone(userUpdate.getPhone());
        }

        userRepository.save(user);

        return UserResponseDto.builder()
                .name(user.getName())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
    }

    public String changePassword(ChangePasswordRequestDto changePasswordRequestDto, String token) {

        String emailFromToken = jwtService.getEmailFromToken(token);

        if (!emailFromToken.equals(changePasswordRequestDto.getEmail())) {
            throw new InvalidTokenException("El token no corresponde al usuario");
        }

        UserEntity user = userRepository.findByEmail(changePasswordRequestDto.getEmail());

        if (user == null) {
            throw new EmailNotFound("No se ha encontrado un usuario con este email");
        }

        if (!passwordEncoder.matches(changePasswordRequestDto.getCurrentPassword(), user.getPassword())) {
            throw new InvalidPasswordException("La contraseña actual es incorrecta");
        }

        if (passwordEncoder.matches(changePasswordRequestDto.getNewPassword(), user.getPassword())) {
            throw new InvalidPasswordException("La nueva contraseña no puede ser la misma que la actual");
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequestDto.getNewPassword()));
        userRepository.save(user);

        return "Contraseña cambiada con éxito";

    }
}