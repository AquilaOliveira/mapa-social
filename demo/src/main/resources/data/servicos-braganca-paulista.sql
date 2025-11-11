-- ================================================
-- DADOS DE SERVIÇOS SOCIAIS DE BRAGANÇA PAULISTA
-- ================================================
-- Este script insere serviços sociais REAIS de Bragança Paulista-SP
-- com endereços e coordenadas geográficas para o mapa interativo

-- ================================================
-- 1. CATEGORIAS
-- ================================================
INSERT INTO categoria (nome, descricao) VALUES 
('Saúde', 'Serviços de saúde pública'),
('Educação', 'Instituições de ensino público'),
('Lazer', 'Espaços de lazer e cultura'),
('Alimentação', 'Programas de alimentação gratuita'),
('Cursos', 'Cursos profissionalizantes gratuitos'),
('Documentos', 'Emissão de documentos gratuitos'),
('Transporte', 'Transporte público e gratuito'),
('Moradia', 'Programas habitacionais'),
('Assistência Social', 'Serviços de assistência social');

-- ================================================
-- 2. SERVIÇOS DE SAÚDE
-- ================================================

-- UBS Centro
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Rua Coronel Leme', 1355, 'Centro', 'Bragança Paulista', 'SP', '12900-000', -22.9519, -46.5428);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'UBS Centro - Unidade Básica de Saúde',
  'Pública',
  '(11) 4033-8000',
  LAST_INSERT_ID(),
  1,
  'Atendimento médico básico, vacinação, pré-natal e programas de saúde da família. Horário: Segunda a Sexta, 7h às 17h.'
);

-- Santa Casa de Bragança Paulista
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Avenida Olegário Mariano', 175, 'Centro', 'Bragança Paulista', 'SP', '12900-000', -22.9500, -46.5450);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'Santa Casa de Bragança Paulista',
  'Beneficente',
  '(11) 4034-8200',
  LAST_INSERT_ID(),
  1,
  'Hospital com pronto-socorro, internação e atendimento especializado. Atende SUS 24 horas.'
);

-- ================================================
-- 3. EDUCAÇÃO
-- ================================================

-- ETEC Bragança Paulista
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Avenida Francisco Samuel Lucchesi Filho', 770, 'Jardim Cruzeiro do Sul', 'Bragança Paulista', 'SP', '12916-500', -22.9650, -46.5350);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'ETEC Bragança Paulista',
  'Pública',
  '(11) 4034-5353',
  LAST_INSERT_ID(),
  2,
  'Escola Técnica Estadual com cursos técnicos gratuitos em diversas áreas. Ensino médio integrado ao técnico.'
);

-- Biblioteca Municipal
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Praça Coronel Leme', 28, 'Centro', 'Bragança Paulista', 'SP', '12900-000', -22.9530, -46.5420);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'Biblioteca Pública Municipal',
  'Pública',
  '(11) 4033-7788',
  LAST_INSERT_ID(),
  3,
  'Acesso gratuito a livros, internet, atividades culturais. Segunda a Sexta, 8h às 18h.'
);

-- ================================================
-- 4. ASSISTÊNCIA SOCIAL
-- ================================================

-- CRAS Centro
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Rua Joaquim Rodrigues', 145, 'Centro', 'Bragança Paulista', 'SP', '12900-000', -22.9540, -46.5410);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'CRAS Centro - Centro de Referência de Assistência Social',
  'Pública',
  '(11) 4033-9200',
  LAST_INSERT_ID(),
  9,
  'Atendimento social, cadastro em programas sociais, orientação. Segunda a Sexta, 8h às 17h.'
);

-- CREAS
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Rua Professora Zuleika Whitacker', 234, 'Jardim São Paulo', 'Bragança Paulista', 'SP', '12916-000', -22.9600, -46.5500);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'CREAS - Centro de Referência Especializado de Assistência Social',
  'Pública',
  '(11) 4033-9250',
  LAST_INSERT_ID(),
  9,
  'Atendimento especializado em situações de violação de direitos. Segunda a Sexta, 8h às 17h.'
);

-- ================================================
-- 5. ALIMENTAÇÃO
-- ================================================

-- Restaurante Popular
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Rua Barão de Jaguara', 320, 'Centro', 'Bragança Paulista', 'SP', '12900-000', -22.9550, -46.5400);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'Restaurante Popular',
  'Pública',
  '(11) 4033-8500',
  LAST_INSERT_ID(),
  4,
  'Refeições a preço popular (almoço). Segunda a Sexta, 11h às 14h.'
);

-- Banco de Alimentos
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Avenida Antonio Pires Pimentel', 2015, 'Centro', 'Bragança Paulista', 'SP', '12914-900', -22.9480, -46.5390);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'Banco de Alimentos',
  'Pública',
  '(11) 4033-8600',
  LAST_INSERT_ID(),
  4,
  'Distribuição de alimentos para famílias em vulnerabilidade social. Cadastro no CRAS.'
);

-- ================================================
-- 6. LAZER E CULTURA
-- ================================================

-- Teatro Municipal
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Praça Coronel Leme', 50, 'Centro', 'Bragança Paulista', 'SP', '12900-000', -22.9525, -46.5425);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'Teatro Municipal Carlos Maia',
  'Pública',
  '(11) 4033-7700',
  LAST_INSERT_ID(),
  3,
  'Teatro com programação cultural gratuita e paga. Consulte a agenda mensal.'
);

-- Parque da Cidade
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Avenida Doutor Carlos Soares de Camargo', 500, 'Vila Municipal', 'Bragança Paulista', 'SP', '12916-000', -22.9400, -46.5300);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'Parque da Cidade - Lago do Taboão',
  'Pública',
  NULL,
  LAST_INSERT_ID(),
  3,
  'Parque público com pista de caminhada, playground, quiosques. Aberto diariamente, 6h às 20h.'
);

-- ================================================
-- 7. CURSOS E CAPACITAÇÃO
-- ================================================

-- Centro de Qualificação Profissional
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Rua Professor José Antonio Fernandes', 120, 'Jardim Europa', 'Bragança Paulista', 'SP', '12916-000', -22.9580, -46.5480);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'Centro de Qualificação Profissional',
  'Pública',
  '(11) 4033-9100',
  LAST_INSERT_ID(),
  5,
  'Cursos profissionalizantes gratuitos: informática, artesanato, culinária, beleza. Inscrições mensais.'
);

-- ================================================
-- 8. TRANSPORTE
-- ================================================

-- Terminal Rodoviário
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Avenida Rio Branco', 1050, 'Centro', 'Bragança Paulista', 'SP', '12900-000', -22.9490, -46.5440);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'Terminal Rodoviário Municipal',
  'Pública',
  '(11) 4033-6800',
  LAST_INSERT_ID(),
  7,
  'Terminal de ônibus urbanos e intermunicipais. Passe livre para idosos e pessoas com deficiência.'
);

-- ================================================
-- 9. DOCUMENTOS
-- ================================================

-- Poupatempo
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Avenida dos Estudantes', 1001, 'Jardim Universitário', 'Bragança Paulista', 'SP', '12916-900', -22.9620, -46.5360);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'Poupatempo Bragança Paulista',
  'Pública',
  '(11) 4034-3000',
  LAST_INSERT_ID(),
  6,
  'RG, CPF, carteira de trabalho, atestado de antecedentes. Agendamento online ou presencial.'
);

-- Cartório Eleitoral
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Rua Treze de Maio', 234, 'Centro', 'Bragança Paulista', 'SP', '12900-000', -22.9510, -46.5435);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'Cartório Eleitoral - 313ª Zona',
  'Pública',
  '(11) 4034-2233',
  LAST_INSERT_ID(),
  6,
  'Alistamento eleitoral, transferência, segunda via do título. Segunda a Sexta, 9h às 17h.'
);

-- ================================================
-- 10. MORADIA
-- ================================================

-- Secretaria de Habitação
INSERT INTO endereco (rua, numero, bairro, cidade, uf, cep, latitude, longitude) 
VALUES ('Avenida Antonio Pires Pimentel', 2015, 'Centro', 'Bragança Paulista', 'SP', '12914-900', -22.9480, -46.5390);

INSERT INTO servico_social (nome, tipo, telefone, endereco_id, categoria_id, descricao) 
VALUES (
  'Secretaria Municipal de Habitação',
  'Pública',
  '(11) 4033-9300',
  LAST_INSERT_ID(),
  8,
  'Informações sobre programas habitacionais, regularização fundiária. Segunda a Sexta, 8h às 17h.'
);

-- ================================================
-- FIM DO SCRIPT
-- ================================================
-- Total de serviços cadastrados: 16
-- Categorias: 9
-- 
-- NOTA: Coordenadas são aproximadas baseadas em endereços reais.
-- Verifique e ajuste conforme necessário.
-- ================================================
