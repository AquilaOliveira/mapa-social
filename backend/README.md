# Mapa Social - Backend Java

Backend API REST desenvolvido com Spring Boot para o projeto Mapa Social.

## Tecnologias

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- Spring Security
- PostgreSQL (Supabase)
- JWT Authentication
- Maven

## Configuração

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Acesso ao banco de dados Supabase

### Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto backend com:

```
SUPABASE_DB_PASSWORD=sua_senha_do_banco
JWT_SECRET=sua_chave_secreta_jwt
```

### Instalação

1. Clone o repositório
2. Configure as variáveis de ambiente
3. Execute o projeto:

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080/api`

## Endpoints

### Autenticação

- `POST /api/auth/register` - Criar nova conta
- `POST /api/auth/login` - Fazer login

### Categorias

- `GET /api/categorias` - Listar categorias ativas
- `GET /api/categorias/{id}` - Buscar categoria por ID

### Serviços Sociais

- `GET /api/servicos` - Listar todos os serviços
- `GET /api/servicos/{id}` - Buscar serviço por ID
- `GET /api/servicos/categoria/{categoriaId}` - Listar por categoria
- `GET /api/servicos/buscar?termo={termo}` - Buscar por termo

### Sugestões

- `POST /api/sugestoes` - Criar sugestão (autenticado)
- `POST /api/sugestoes/anonima` - Criar sugestão anônima
- `GET /api/sugestoes/minhas` - Listar minhas sugestões (autenticado)

### Favoritos

- `GET /api/favoritos` - Listar favoritos (autenticado)
- `POST /api/favoritos/{servicoId}` - Adicionar favorito (autenticado)
- `DELETE /api/favoritos/{servicoId}` - Remover favorito (autenticado)

## Estrutura do Projeto

```
backend/
├── src/main/java/com/mapasocial/
│   ├── controller/      # Controllers REST
│   ├── dto/            # Data Transfer Objects
│   ├── model/          # Entidades JPA
│   ├── repository/     # Repositórios JPA
│   ├── security/       # Configurações de segurança
│   └── service/        # Lógica de negócio
└── src/main/resources/
    └── application.properties
```

## Segurança

A API utiliza JWT (JSON Web Tokens) para autenticação. Endpoints públicos não requerem autenticação, enquanto endpoints protegidos necessitam do token no header:

```
Authorization: Bearer {seu_token_jwt}
```
