# mapa-social

Mapa de serviços sociais gratuitos, facilitando o acesso a direitos básicos por meio da tecnologia.

---

## Índice
- [Sobre](#-sobre)
- [Tecnologias](#-tecnologias)
- [Instalação](#-instalação)
- [Autor](#-autores)

---

## Sobre
Este projeto foi desenvolvido pensando na inclusão de pessoas que não tenham acesso ou meios de chegar a um tipo serviço gratuito na cidade de Bragança Paulista.  
Ele permite que os usuários acessem e naveguem no mapa da cidade com ou sem um login ativo, façam buscas sobre os tipos de serviços disponíveis e tenham acesso a um meio de contato dos serviços prestados..

---

## Tecnologias
As seguintes ferramentas foram usadas na construção do projeto:

- [React](https://reactjs.org/)
- API ou Banco de dados relacional (Decidir)
- Decidir em que linguagem o Backend será construído
- [TailwindCSS](https://tailwindcss.com) ou somente o CSS3

---

## Instalação & Execução

### Backend (Spring Boot)
Pré-requisitos: Java 17, Maven Wrapper incluso.

```bash
git clone https://github.com/SEU_USUARIO/mapa-social.git
cd mapa-social/backend
./mvnw clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

Perfil H2 (memória):
```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=h2
```

### Frontend (Vite React)
Pré-requisitos: Node 18+.
```bash
cd ../frontend
npm install
npm run dev
```

Defina a URL do backend em `.env`:
```
VITE_API_BASE=http://localhost:8080
```

## Deploy (Produção)

### Backend no Railway
1. Crie projeto no Railway e conecte o repositório `backend`.
2. Adicione Postgres (gera variáveis `PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER`, `PGPASSWORD`).
3. Garanta `spring.jpa.hibernate.ddl-auto=validate` e `Flyway` habilitado (já configurado).
4. Adicione variável `FRONTEND_ORIGIN=https://SEU_FRONTEND.vercel.app` para CORS.
5. Railway gera a URL pública do backend (use no frontend).

### Frontend no Vercel
1. Importar repositório `frontend`.
2. Definir variável de ambiente `VITE_API_BASE=https://SEU_BACKEND.up.railway.app`.
3. Fazer deploy; testar rotas e mapa.

### Variáveis Principais
- Backend: `FRONTEND_ORIGIN`, (`PG*` automáticas Railway)
- Frontend: `VITE_API_BASE`

### Fluxo de Migração de Banco
1. Primeiro deploy aplica `V1__baseline.sql` (Flyway).
2. Dados de seed são criados apenas se não existirem (via `DataInitializer`).
3. Próximas mudanças estruturais: criar novos arquivos `V2__...sql`, `V3__...sql` etc.

### CI
Workflow GitHub Actions (`backend/.github/workflows/backend-ci.yml`) executa build e testes em cada push/PR.

## Desenvolvimento Futuro
- Adicionar autenticação JWT.
- Criar testes de integração para endpoints principais.
- Monitoramento com Actuator + métricas.

---

### Autores

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

