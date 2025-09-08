package co.edu.uniquindio.proyecto.configuracion;

import co.edu.uniquindio.proyecto.Entity.RoleEntity;
import co.edu.uniquindio.proyecto.Entity.UserEntity;
import co.edu.uniquindio.proyecto.repositorios.IUserRepository;
import co.edu.uniquindio.proyecto.repositorios.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase de configuración para inicializar datos en la base de datos
 * al iniciar la aplicación.
 *
 * Crea roles y usuarios iniciales si no existen.
 */
@Configuration // Marca la clase como configuración de Spring
@RequiredArgsConstructor // Genera constructor con los campos final automáticamente
public class DataInitializer {

    // Repositorio de usuarios
    private final IUserRepository userRepository;

    // Repositorio de roles
    private final IRoleRepository roleRepository;

    // Codificador de contraseñas
    private final PasswordEncoder passwordEncoder;

    /**
     * Bean de CommandLineRunner que se ejecuta al iniciar la aplicación.
     * Inicializa los datos necesarios como roles y usuario admin.
     *
     * @return CommandLineRunner para inicializar datos
     */
    @Bean
    CommandLineRunner initData() {
        return args -> {
            // ✅ Verificamos si ya existe el rol ADMIN en la base de datos
            RoleEntity roleAdmin = roleRepository.findById("1").orElse(null);
            if (roleAdmin == null) {
                roleAdmin = new RoleEntity();
                roleAdmin.setIdRole("1"); // ID del rol
                roleAdmin.setName("ROLE_ADMIN"); // Nombre del rol
                roleAdmin.setDescription("Administrador del sistema"); // Descripción
                roleRepository.save(roleAdmin);

                System.out.println("✅ Rol ADMIN creado");
            }

            // ✅ Verificamos si ya existe el usuario admin
            if (userRepository.findByEmail("admin@uq.edu.co") == null) {
                UserEntity admin = new UserEntity();
                admin.setName("Super");
                admin.setLastname("Admin");
                admin.setDni("123456789");
                admin.setAddress("UQ Armenia");
                admin.setEmail("admin@uq.edu.co");
                admin.setPassword(passwordEncoder.encode("admin123")); // 🔐 Contraseña encriptada
                admin.setPhone("3100000000");
                admin.setRole(roleAdmin); // Asignamos el rol de administrador

                userRepository.save(admin);

                System.out.println("✅ Usuario admin creado: admin@uq.edu.co / admin123");
            } else {
                System.out.println("ℹ️ El usuario admin ya existe en la base de datos.");
            }
        };
    }
}
