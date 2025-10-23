# Mapa Social

Plataforma web para localização de serviços sociais gratuitos em Bragança Paulista, facilitando o acesso a direitos básicos através da tecnologia.

---

## Sobre o Projeto

O Mapa Social foi desenvolvido para promover a inclusão de pessoas que não têm acesso ou conhecimento sobre serviços gratuitos disponíveis na cidade de Bragança Paulista. A plataforma permite que usuários:

- Naveguem no mapa da cidade para encontrar serviços
- Busquem por categorias específicas (saúde, educação, alimentação, etc.)
- Acessem informações detalhadas sobre cada serviço
- Sugiram novos serviços para serem adicionados ao mapa
- Salvem seus serviços favoritos (com login)

---

## Tecnologias

### Backend
- **Java 17** com **Spring Boot 3.2.0**
- **Spring Data JPA** para persistência
- **Spring Security** com **JWT** para autenticação
- **PostgreSQL** via **Supabase**
- **Maven** para gerenciamento de dependências

### Frontend
- **React 19** com **Vite**
- **React Router** para navegação
- **Axios** para requisições HTTP
- **CSS3** para estilização

### Banco de Dados
- **Supabase** (PostgreSQL)
- Row Level Security (RLS) habilitado

---

## Instalação e Configuração

### Pré-requisitos

- Java 17 ou superior
- Node.js 18+ e npm
- Acesso ao banco Supabase

### Backend

1. Navegue até a pasta backend:
```bash
cd backend
```

2. Configure as variáveis de ambiente criando um arquivo `.env`:
```
SUPABASE_DB_PASSWORD=sua_senha_do_banco
JWT_SECRET=sua_chave_secreta_jwt
```

3. Compile e execute:
```bash
mvn clean install
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080/api`

### Frontend

1. Navegue até a pasta frontend:
```bash
cd frontend
```

2. Instale as dependências:
```bash
npm install
```

3. Execute o projeto:
```bash
npm run dev
```

A aplicação estará disponível em `http://localhost:5173`

---

## Funcionalidades

### Públicas (sem login)
- Visualizar mapa com serviços
- Buscar serviços por termo
- Filtrar por categoria
- Ver detalhes dos serviços
- Sugerir novos serviços anonimamente

### Autenticadas (com login)
- Todas as funcionalidades públicas
- Salvar serviços favoritos
- Gerenciar perfil
- Ver histórico de sugestões

---

## Autores

Áquila Matheus de Oliveira
- [LinkedIn](https://www.linkedin.com/in/aquila-oliveira)  
- [GitHub](https://github.com/AquilaOliveira)

Josiely de Olievira Ferreira
- [LinkedIn](https://www.linkedin.com/in/josiely-oliveira)
- [GitHub](https://github.com/Josiely-Oliveira)

Raissa Santos Feitosa
- [LinkedIn](https://www.linkedin.com/in/raissa-santos-feitosa-73485b1a3/)
- [GitHub](https://github.com/raissa-sf)

Vitor de Oliveira Faria
- [LinkedIn](https://www.linkedin.com/in/vitor-oliveira-526a28209)
- [GitHub](https://github.com/Vi1tor)

