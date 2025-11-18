-- Script para criar o primeiro usuário ADMIN
-- Execute este script no seu banco de dados MySQL

-- Inserir admin (senha padrão: admin123)
-- A senha é criptografada com BCrypt
INSERT INTO usuario (nome, email, senha_hash, tipo, role, data_cadastro) 
VALUES (
    'Administrador',
    'admin@mapasocial.com',
    '$2a$10$8R8VVQX3G7qX.9yP4qZ4GOXqYqXn5BhKqhHmZL8kI0c5.3qI5.HZO',
    'ADMIN',
    'ADMIN',
    NOW()
);

-- Para criar um novo admin com outra senha, você precisará:
-- 1. Rodar o backend
-- 2. Usar o endpoint /usuarios/cadastro normalmente
-- 3. Depois atualizar o campo 'role' manualmente:
-- UPDATE usuario SET role = 'ADMIN' WHERE email = 'email@exemplo.com';
