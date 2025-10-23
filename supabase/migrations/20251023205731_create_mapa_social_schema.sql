/*
  # Mapa Social Database Schema

  ## Overview
  This migration creates the complete database schema for the Mapa Social application,
  a platform to help people find free social services in Bragança Paulista.

  ## 1. New Tables

  ### `usuarios` (Users)
  - `id` (uuid, primary key) - Unique user identifier
  - `nome` (text) - User's full name
  - `email` (text, unique) - User's email address
  - `senha_hash` (text) - Hashed password
  - `telefone` (text, nullable) - User's phone number
  - `cpf` (text, unique, nullable) - User's CPF document
  - `data_nascimento` (date, nullable) - User's birth date
  - `ativo` (boolean) - Whether the user account is active
  - `created_at` (timestamptz) - Account creation timestamp
  - `updated_at` (timestamptz) - Last update timestamp

  ### `categorias` (Categories)
  - `id` (uuid, primary key) - Unique category identifier
  - `nome` (text, unique) - Category name (e.g., Saúde, Educação)
  - `descricao` (text, nullable) - Category description
  - `icone_url` (text, nullable) - URL to category icon
  - `cor` (text) - Color for UI representation
  - `ativo` (boolean) - Whether the category is active
  - `created_at` (timestamptz) - Creation timestamp

  ### `servicos_sociais` (Social Services)
  - `id` (uuid, primary key) - Unique service identifier
  - `categoria_id` (uuid, foreign key) - Reference to category
  - `nome` (text) - Service name
  - `descricao` (text) - Service description
  - `endereco` (text) - Service address
  - `latitude` (numeric) - Geographic latitude
  - `longitude` (numeric) - Geographic longitude
  - `telefone` (text, nullable) - Contact phone
  - `email` (text, nullable) - Contact email
  - `horario_funcionamento` (text, nullable) - Operating hours
  - `site_url` (text, nullable) - Website URL
  - `requisitos` (text, nullable) - Requirements to access service
  - `documentos_necessarios` (text, nullable) - Required documents
  - `ativo` (boolean) - Whether the service is active
  - `verificado` (boolean) - Whether the service is verified
  - `created_at` (timestamptz) - Creation timestamp
  - `updated_at` (timestamptz) - Last update timestamp

  ### `favoritos` (Favorites)
  - `id` (uuid, primary key) - Unique favorite identifier
  - `usuario_id` (uuid, foreign key) - Reference to user
  - `servico_id` (uuid, foreign key) - Reference to social service
  - `created_at` (timestamptz) - Creation timestamp

  ### `historico_pesquisa` (Search History)
  - `id` (uuid, primary key) - Unique history identifier
  - `usuario_id` (uuid, foreign key, nullable) - Reference to user (null for anonymous)
  - `termo_pesquisa` (text) - Search term
  - `categoria_id` (uuid, foreign key, nullable) - Reference to category if filtered
  - `created_at` (timestamptz) - Search timestamp

  ### `avaliacoes` (Reviews)
  - `id` (uuid, primary key) - Unique review identifier
  - `servico_id` (uuid, foreign key) - Reference to social service
  - `usuario_id` (uuid, foreign key) - Reference to user
  - `nota` (integer) - Rating (1-5)
  - `comentario` (text, nullable) - Review comment
  - `created_at` (timestamptz) - Creation timestamp
  - `updated_at` (timestamptz) - Last update timestamp

  ### `sugestoes` (Suggestions)
  - `id` (uuid, primary key) - Unique suggestion identifier
  - `usuario_id` (uuid, foreign key, nullable) - Reference to user (null for anonymous)
  - `nome_servico` (text) - Suggested service name
  - `categoria_sugerida` (text) - Suggested category
  - `descricao` (text) - Service description
  - `endereco` (text, nullable) - Service address
  - `contato` (text, nullable) - Contact information
  - `status` (text) - Status: 'pendente', 'aprovado', 'rejeitado'
  - `observacao_admin` (text, nullable) - Admin notes
  - `created_at` (timestamptz) - Creation timestamp
  - `updated_at` (timestamptz) - Last update timestamp

  ### `avisos` (Notices)
  - `id` (uuid, primary key) - Unique notice identifier
  - `titulo` (text) - Notice title
  - `mensagem` (text) - Notice message
  - `tipo` (text) - Type: 'info', 'alerta', 'urgente'
  - `ativo` (boolean) - Whether the notice is active
  - `data_inicio` (timestamptz) - Start date for displaying
  - `data_fim` (timestamptz, nullable) - End date for displaying
  - `created_at` (timestamptz) - Creation timestamp

  ### `estatisticas` (Statistics)
  - `id` (uuid, primary key) - Unique statistic identifier
  - `tipo` (text) - Type: 'visualizacao', 'pesquisa', 'favorito', 'avaliacao'
  - `servico_id` (uuid, foreign key, nullable) - Reference to service
  - `categoria_id` (uuid, foreign key, nullable) - Reference to category
  - `quantidade` (integer) - Count
  - `data` (date) - Date of the statistic
  - `created_at` (timestamptz) - Creation timestamp

  ## 2. Security
  - Enable RLS on all tables
  - Add restrictive policies for authenticated users
  - Public read access for services and categories
  - Users can only modify their own data

  ## 3. Indexes
  - Geographic indexes for location-based queries
  - Search indexes for text fields
  - Foreign key indexes for performance
*/

-- Create usuarios table
CREATE TABLE IF NOT EXISTS usuarios (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  nome text NOT NULL,
  email text UNIQUE NOT NULL,
  senha_hash text NOT NULL,
  telefone text,
  cpf text UNIQUE,
  data_nascimento date,
  ativo boolean DEFAULT true,
  created_at timestamptz DEFAULT now(),
  updated_at timestamptz DEFAULT now()
);

-- Create categorias table
CREATE TABLE IF NOT EXISTS categorias (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  nome text UNIQUE NOT NULL,
  descricao text,
  icone_url text,
  cor text DEFAULT '#3B82F6',
  ativo boolean DEFAULT true,
  created_at timestamptz DEFAULT now()
);

-- Create servicos_sociais table
CREATE TABLE IF NOT EXISTS servicos_sociais (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  categoria_id uuid REFERENCES categorias(id) ON DELETE SET NULL,
  nome text NOT NULL,
  descricao text NOT NULL,
  endereco text NOT NULL,
  latitude numeric(10, 8),
  longitude numeric(11, 8),
  telefone text,
  email text,
  horario_funcionamento text,
  site_url text,
  requisitos text,
  documentos_necessarios text,
  ativo boolean DEFAULT true,
  verificado boolean DEFAULT false,
  created_at timestamptz DEFAULT now(),
  updated_at timestamptz DEFAULT now()
);

-- Create favoritos table
CREATE TABLE IF NOT EXISTS favoritos (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  usuario_id uuid REFERENCES usuarios(id) ON DELETE CASCADE NOT NULL,
  servico_id uuid REFERENCES servicos_sociais(id) ON DELETE CASCADE NOT NULL,
  created_at timestamptz DEFAULT now(),
  UNIQUE(usuario_id, servico_id)
);

-- Create historico_pesquisa table
CREATE TABLE IF NOT EXISTS historico_pesquisa (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  usuario_id uuid REFERENCES usuarios(id) ON DELETE CASCADE,
  termo_pesquisa text NOT NULL,
  categoria_id uuid REFERENCES categorias(id) ON DELETE SET NULL,
  created_at timestamptz DEFAULT now()
);

-- Create avaliacoes table
CREATE TABLE IF NOT EXISTS avaliacoes (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  servico_id uuid REFERENCES servicos_sociais(id) ON DELETE CASCADE NOT NULL,
  usuario_id uuid REFERENCES usuarios(id) ON DELETE CASCADE NOT NULL,
  nota integer CHECK (nota >= 1 AND nota <= 5) NOT NULL,
  comentario text,
  created_at timestamptz DEFAULT now(),
  updated_at timestamptz DEFAULT now(),
  UNIQUE(servico_id, usuario_id)
);

-- Create sugestoes table
CREATE TABLE IF NOT EXISTS sugestoes (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  usuario_id uuid REFERENCES usuarios(id) ON DELETE SET NULL,
  nome_servico text NOT NULL,
  categoria_sugerida text NOT NULL,
  descricao text NOT NULL,
  endereco text,
  contato text,
  status text DEFAULT 'pendente' CHECK (status IN ('pendente', 'aprovado', 'rejeitado')),
  observacao_admin text,
  created_at timestamptz DEFAULT now(),
  updated_at timestamptz DEFAULT now()
);

-- Create avisos table
CREATE TABLE IF NOT EXISTS avisos (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  titulo text NOT NULL,
  mensagem text NOT NULL,
  tipo text DEFAULT 'info' CHECK (tipo IN ('info', 'alerta', 'urgente')),
  ativo boolean DEFAULT true,
  data_inicio timestamptz DEFAULT now(),
  data_fim timestamptz,
  created_at timestamptz DEFAULT now()
);

-- Create estatisticas table
CREATE TABLE IF NOT EXISTS estatisticas (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  tipo text NOT NULL CHECK (tipo IN ('visualizacao', 'pesquisa', 'favorito', 'avaliacao')),
  servico_id uuid REFERENCES servicos_sociais(id) ON DELETE CASCADE,
  categoria_id uuid REFERENCES categorias(id) ON DELETE CASCADE,
  quantidade integer DEFAULT 1,
  data date DEFAULT CURRENT_DATE,
  created_at timestamptz DEFAULT now()
);

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_servicos_categoria ON servicos_sociais(categoria_id);
CREATE INDEX IF NOT EXISTS idx_servicos_location ON servicos_sociais(latitude, longitude);
CREATE INDEX IF NOT EXISTS idx_servicos_ativo ON servicos_sociais(ativo);
CREATE INDEX IF NOT EXISTS idx_favoritos_usuario ON favoritos(usuario_id);
CREATE INDEX IF NOT EXISTS idx_favoritos_servico ON favoritos(servico_id);
CREATE INDEX IF NOT EXISTS idx_historico_usuario ON historico_pesquisa(usuario_id);
CREATE INDEX IF NOT EXISTS idx_avaliacoes_servico ON avaliacoes(servico_id);
CREATE INDEX IF NOT EXISTS idx_sugestoes_status ON sugestoes(status);
CREATE INDEX IF NOT EXISTS idx_estatisticas_data ON estatisticas(data);

-- Enable Row Level Security
ALTER TABLE usuarios ENABLE ROW LEVEL SECURITY;
ALTER TABLE categorias ENABLE ROW LEVEL SECURITY;
ALTER TABLE servicos_sociais ENABLE ROW LEVEL SECURITY;
ALTER TABLE favoritos ENABLE ROW LEVEL SECURITY;
ALTER TABLE historico_pesquisa ENABLE ROW LEVEL SECURITY;
ALTER TABLE avaliacoes ENABLE ROW LEVEL SECURITY;
ALTER TABLE sugestoes ENABLE ROW LEVEL SECURITY;
ALTER TABLE avisos ENABLE ROW LEVEL SECURITY;
ALTER TABLE estatisticas ENABLE ROW LEVEL SECURITY;

-- RLS Policies for categorias (public read)
CREATE POLICY "Anyone can view active categories"
  ON categorias FOR SELECT
  USING (ativo = true);

-- RLS Policies for servicos_sociais (public read)
CREATE POLICY "Anyone can view active services"
  ON servicos_sociais FOR SELECT
  USING (ativo = true);

-- RLS Policies for usuarios
CREATE POLICY "Users can view own profile"
  ON usuarios FOR SELECT
  TO authenticated
  USING (id = (SELECT id FROM usuarios WHERE email = auth.jwt()->>'email'));

CREATE POLICY "Users can update own profile"
  ON usuarios FOR UPDATE
  TO authenticated
  USING (id = (SELECT id FROM usuarios WHERE email = auth.jwt()->>'email'))
  WITH CHECK (id = (SELECT id FROM usuarios WHERE email = auth.jwt()->>'email'));

-- RLS Policies for favoritos
CREATE POLICY "Users can view own favorites"
  ON favoritos FOR SELECT
  TO authenticated
  USING (usuario_id = (SELECT id FROM usuarios WHERE email = auth.jwt()->>'email'));

CREATE POLICY "Users can insert own favorites"
  ON favoritos FOR INSERT
  TO authenticated
  WITH CHECK (usuario_id = (SELECT id FROM usuarios WHERE email = auth.jwt()->>'email'));

CREATE POLICY "Users can delete own favorites"
  ON favoritos FOR DELETE
  TO authenticated
  USING (usuario_id = (SELECT id FROM usuarios WHERE email = auth.jwt()->>'email'));

-- RLS Policies for historico_pesquisa
CREATE POLICY "Users can view own search history"
  ON historico_pesquisa FOR SELECT
  TO authenticated
  USING (usuario_id = (SELECT id FROM usuarios WHERE email = auth.jwt()->>'email'));

CREATE POLICY "Users can insert own search history"
  ON historico_pesquisa FOR INSERT
  TO authenticated
  WITH CHECK (usuario_id = (SELECT id FROM usuarios WHERE email = auth.jwt()->>'email'));

-- RLS Policies for avaliacoes (public read)
CREATE POLICY "Anyone can view reviews"
  ON avaliacoes FOR SELECT
  USING (true);

CREATE POLICY "Authenticated users can insert reviews"
  ON avaliacoes FOR INSERT
  TO authenticated
  WITH CHECK (usuario_id = (SELECT id FROM usuarios WHERE email = auth.jwt()->>'email'));

CREATE POLICY "Users can update own reviews"
  ON avaliacoes FOR UPDATE
  TO authenticated
  USING (usuario_id = (SELECT id FROM usuarios WHERE email = auth.jwt()->>'email'))
  WITH CHECK (usuario_id = (SELECT id FROM usuarios WHERE email = auth.jwt()->>'email'));

CREATE POLICY "Users can delete own reviews"
  ON avaliacoes FOR DELETE
  TO authenticated
  USING (usuario_id = (SELECT id FROM usuarios WHERE email = auth.jwt()->>'email'));

-- RLS Policies for sugestoes
CREATE POLICY "Anyone can insert suggestions"
  ON sugestoes FOR INSERT
  WITH CHECK (true);

CREATE POLICY "Users can view own suggestions"
  ON sugestoes FOR SELECT
  TO authenticated
  USING (usuario_id = (SELECT id FROM usuarios WHERE email = auth.jwt()->>'email'));

-- RLS Policies for avisos (public read)
CREATE POLICY "Anyone can view active notices"
  ON avisos FOR SELECT
  USING (ativo = true AND data_inicio <= now() AND (data_fim IS NULL OR data_fim >= now()));

-- RLS Policies for estatisticas (public read)
CREATE POLICY "Anyone can view statistics"
  ON estatisticas FOR SELECT
  USING (true);
