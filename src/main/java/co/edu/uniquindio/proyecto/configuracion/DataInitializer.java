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

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initData() {
        return args -> {
            // ✅ Verificamos si ya existe el rol ADMIN
            RoleEntity roleAdmin = roleRepository.findById("1").orElse(null);
            if (roleAdmin == null) {
                roleAdmin = new RoleEntity();
                roleAdmin.setIdRole("1");
                roleAdmin.setName("ROLE_ADMIN");
                roleAdmin.setDescription("Administrador del sistema");
                roleRepository.save(roleAdmin);

                System.out.println("✅ Rol ADMIN creado");
            }

            // ✅ Verificamos si ya existe el admin
            if (userRepository.findByEmail("admin@uq.edu.co") == null) {
                UserEntity admin = new UserEntity();
                admin.setName("Super");
                admin.setLastname("Admin");
                admin.setDni("123456789");
                admin.setAddress("UQ Armenia");
                admin.setEmail("admin@uq.edu.co");
                admin.setPassword(passwordEncoder.encode("admin123")); // 🔐 Encriptada
                admin.setPhone("3100000000");
                admin.setRole(roleAdmin);

                userRepository.save(admin);

                System.out.println("✅ Usuario admin creado: admin@uq.edu.co / admin123");
            } else {
                System.out.println("ℹ️ El usuario admin ya existe en la base de datos.");
            }
        };
    }
}
