-- Seed inicial para ambiente H2 em memória
-- Ao reiniciar o backend, estes registros serão recriados

-- SUPER_ADMIN (senha: super123)
INSERT INTO USUARIO (nome, email, senha_hash, tipo, role, data_cadastro, bloqueado)
VALUES ('Super Admin', 'superadmin@mapasocial.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'SUPERUSUARIO', 'SUPER_ADMIN', CURRENT_TIMESTAMP, FALSE);

-- ADMIN (senha: super123)
INSERT INTO USUARIO (nome, email, senha_hash, tipo, role, data_cadastro, bloqueado)
VALUES ('Maria Admin', 'maria@admin.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN', 'ADMIN', CURRENT_TIMESTAMP, FALSE);

-- USER (senha: super123)
INSERT INTO USUARIO (nome, email, senha_hash, tipo, role, data_cadastro, bloqueado)
VALUES ('João Silva', 'joao@user.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'COMUM', 'USER', CURRENT_TIMESTAMP, FALSE);

-- USER bloqueado (senha: super123)
INSERT INTO USUARIO (nome, email, senha_hash, tipo, role, data_cadastro, bloqueado)
VALUES ('Pedro Bloqueado', 'pedro@user.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'COMUM', 'USER', CURRENT_TIMESTAMP, TRUE);
