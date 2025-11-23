package com.mapa.social.demo.config;

import com.mapa.social.demo.model.UserRole;
import com.mapa.social.demo.model.Usuario;
import com.mapa.social.demo.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Usuario superAdmin = new Usuario();
                superAdmin.setNome("Super Admin");
                superAdmin.setEmail("superadmin@mapasocial.com");
                superAdmin.setSenhaHash(passwordEncoder.encode("admin123"));
                superAdmin.setTipo("SUPERUSUARIO");
                superAdmin.setRole(UserRole.SUPER_ADMIN);
                superAdmin.setBloqueado(false);
                usuarioRepository.save(superAdmin);

                Usuario admin = new Usuario();
                admin.setNome("Maria Admin");
                admin.setEmail("maria@admin.com");
                admin.setSenhaHash(passwordEncoder.encode("admin123"));
                admin.setTipo("ADMIN");
                admin.setRole(UserRole.ADMIN);
                admin.setBloqueado(false);
                usuarioRepository.save(admin);

                Usuario user = new Usuario();
                user.setNome("João Silva");
                user.setEmail("joao@user.com");
                user.setSenhaHash(passwordEncoder.encode("admin123"));
                user.setTipo("COMUM");
                user.setRole(UserRole.USER);
                user.setBloqueado(false);
                usuarioRepository.save(user);

                Usuario bloqueado = new Usuario();
                bloqueado.setNome("Pedro Bloqueado");
                bloqueado.setEmail("pedro@user.com");
                bloqueado.setSenhaHash(passwordEncoder.encode("admin123"));
                bloqueado.setTipo("COMUM");
                bloqueado.setRole(UserRole.USER);
                bloqueado.setBloqueado(true);
                usuarioRepository.save(bloqueado);

                System.out.println("✅ Usuários iniciais criados com sucesso!");
                System.out.println("📧 Emails: superadmin@mapasocial.com, maria@admin.com, joao@user.com, pedro@user.com");
                System.out.println("🔑 Senha para todos: admin123");
            }
        };
    }
}
