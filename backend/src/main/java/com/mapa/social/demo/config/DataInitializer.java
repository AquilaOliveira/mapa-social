package com.mapa.social.demo.config;

import com.mapa.social.demo.model.*;
import com.mapa.social.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            UsuarioRepository usuarioRepository, 
            PasswordEncoder passwordEncoder,
            CategoriaRepository categoriaRepository,
            ServicoSocialRepository servicoSocialRepository,
            EnderecoRepository enderecoRepository) {
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

            // Criar categorias
            if (categoriaRepository.count() == 0) {
                Categoria saude = new Categoria();
                saude.setNome("Saúde");
                saude.setDescricao("Serviços de saúde pública");
                categoriaRepository.save(saude);

                Categoria assistencia = new Categoria();
                assistencia.setNome("Assistência Social");
                assistencia.setDescricao("Programas de assistência e apoio social");
                categoriaRepository.save(assistencia);

                Categoria educacao = new Categoria();
                educacao.setNome("Educação");
                educacao.setDescricao("Serviços educacionais");
                categoriaRepository.save(educacao);

                Categoria cultura = new Categoria();
                cultura.setNome("Cultura");
                cultura.setDescricao("Espaços culturais e eventos");
                categoriaRepository.save(cultura);

                Categoria esporte = new Categoria();
                esporte.setNome("Esporte");
                esporte.setDescricao("Instalações esportivas públicas");
                categoriaRepository.save(esporte);

                System.out.println("✅ Categorias criadas com sucesso!");
            }

            // Criar serviços sociais de Bragança Paulista
            if (servicoSocialRepository.count() == 0) {
                Categoria saude = categoriaRepository.findByNome("Saúde").orElseThrow();
                Categoria assistencia = categoriaRepository.findByNome("Assistência Social").orElseThrow();
                Categoria educacao = categoriaRepository.findByNome("Educação").orElseThrow();

                // UBS Centro
                criarServico(servicoSocialRepository, enderecoRepository,
                    "UBS Centro", "Unidade Básica de Saúde", "(11) 4033-9200",
                    "Rua Coronel Leme", 1230, "Centro", "Bragança Paulista", "SP", "12900-000",
                    -22.9524, -46.5442, saude);

                // CRAS Centro
                criarServico(servicoSocialRepository, enderecoRepository,
                    "CRAS Centro", "Centro de Referência de Assistência Social", "(11) 4033-8900",
                    "Rua Ângelo Benetton", 150, "Centro", "Bragança Paulista", "SP", "12900-000",
                    -22.9515, -46.5410, assistencia);

                // UBS Jardim São Paulo
                criarServico(servicoSocialRepository, enderecoRepository,
                    "UBS Jardim São Paulo", "Unidade Básica de Saúde", "(11) 4033-9210",
                    "Rua Pedro de Toledo", 500, "Jardim São Paulo", "Bragança Paulista", "SP", "12903-000",
                    -22.9380, -46.5380, saude);

                // EMEI Vila Aparecida
                criarServico(servicoSocialRepository, enderecoRepository,
                    "EMEI Vila Aparecida", "Escola Municipal de Educação Infantil", "(11) 4033-7100",
                    "Rua José Galvão", 89, "Vila Aparecida", "Bragança Paulista", "SP", "12906-000",
                    -22.9450, -46.5520, educacao);

                // UBS Taboão
                criarServico(servicoSocialRepository, enderecoRepository,
                    "UBS Taboão", "Unidade Básica de Saúde", "(11) 4033-9220",
                    "Rua Carlos Gomes", 780, "Taboão", "Bragança Paulista", "SP", "12910-000",
                    -22.9290, -46.5590, saude);

                // CRAS Norte
                criarServico(servicoSocialRepository, enderecoRepository,
                    "CRAS Norte", "Centro de Referência de Assistência Social", "(11) 4033-8910",
                    "Avenida Antonio Pires Pimentel", 1500, "Jardim Bom Clima", "Bragança Paulista", "SP", "12908-000",
                    -22.9250, -46.5320, assistencia);

                // UBS Vista Verde
                criarServico(servicoSocialRepository, enderecoRepository,
                    "UBS Vista Verde", "Unidade Básica de Saúde", "(11) 4033-9230",
                    "Rua João Pereira", 300, "Vista Verde", "Bragança Paulista", "SP", "12914-000",
                    -22.9600, -46.5650, saude);

                // Biblioteca Municipal
                criarServico(servicoSocialRepository, enderecoRepository,
                    "Biblioteca Municipal", "Biblioteca Pública", "(11) 4033-7500",
                    "Rua Coronel Pedro Penteado", 600, "Centro", "Bragança Paulista", "SP", "12900-000",
                    -22.9510, -46.5430, educacao);

                // CREAS
                criarServico(servicoSocialRepository, enderecoRepository,
                    "CREAS Bragança Paulista", "Centro Especializado de Assistência Social", "(11) 4033-8920",
                    "Rua Francisco Barreto Leme", 1800, "Lavapés", "Bragança Paulista", "SP", "12902-000",
                    -22.9560, -46.5380, assistencia);

                // UBS Lavapés
                criarServico(servicoSocialRepository, enderecoRepository,
                    "UBS Lavapés", "Unidade Básica de Saúde", "(11) 4033-9240",
                    "Rua Vereador Benedito Marcondes", 450, "Lavapés", "Bragança Paulista", "SP", "12902-000",
                    -22.9570, -46.5360, saude);

                System.out.println("✅ Serviços sociais de Bragança Paulista criados com sucesso!");
                System.out.println("📍 Total: " + servicoSocialRepository.count() + " serviços cadastrados");
            }
        };
    }

    private void criarServico(ServicoSocialRepository servicoRepo, EnderecoRepository enderecoRepo,
                              String nome, String tipo, String telefone,
                              String rua, int numero, String bairro, String cidade, String uf, String cep,
                              double latitude, double longitude, Categoria categoria) {
        Endereco endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setUf(uf);
        endereco.setCep(cep);
        endereco.setLatitude(latitude);
        endereco.setLongitude(longitude);

        ServicoSocial servico = new ServicoSocial();
        servico.setNome(nome);
        servico.setTipo(tipo);
        servico.setTelefone(telefone);
        servico.setEndereco(endereco);
        servico.setCategoria(categoria);
        servicoRepo.save(servico);
    }
}
