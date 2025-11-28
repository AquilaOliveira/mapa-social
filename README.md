# Mapa Social

Mapa de serviços sociais gratuitos que centraliza informações de saúde, alimentação, abrigo e assistência social em um mapa interativo, facilitando o acesso a direitos básicos para pessoas em situação de vulnerabilidade.

---

![Tela inicial do Mapa Social](tela.jpeg)

---

## Índice

- [Sobre](#sobre)
- [Funcionalidades](#funcionalidades)
- [Arquitetura](#arquitetura)
- [Tecnologias](#tecnologias)
- [Instalação](#instalação)
- [Deploy](#deploy)
- [Autores](#autores)

---

## Sobre

O Mapa Social é uma plataforma web pensada para incluir pessoas que têm dificuldade em localizar serviços sociais gratuitos na cidade de Bragança Paulista e região, permitindo acesso ao mapa mesmo sem login obrigatório.  
A aplicação aproxima cidadãos em vulnerabilidade de organizações que oferecem serviços nas áreas de saúde, nutrição, moradia e apoio social, contribuindo para a redução de desigualdades e fortalecimento de redes comunitárias.

---

## Funcionalidades

- Acesso anônimo ao mapa, com visualização de serviços e detalhes como nome, endereço, contato e categoria.  
- Filtro por categoria e busca por palavra‑chave para encontrar serviços sociais.  
- Visualização de rotas até o local via aplicativos externos de mapas (ex.: Google Maps, Waze).  
- Envio de sugestão de novos serviços sociais por usuários anônimos ou autenticados.  

Usuário autenticado:  
- Favoritar serviços, consultar favoritos e histórico de acessos.  
- Acompanhar status das sugestões (pendente, aprovado, recusado) e visualizar justificativas.  

Administrador:  
- Analisar, aprovar, recusar e editar serviços sugeridos.  
- Publicar, editar e remover avisos na plataforma e visualizar estatísticas administrativas.  

Usuário TI (super administrador):  
- Criar, editar, desativar administradores e gerenciar permissões administrativas.

---

## Arquitetura

A aplicação adota arquitetura cliente‑servidor com separação entre frontend, backend e banco de dados.

- Frontend: SPA em React consumindo uma API REST.  
- Backend: API REST em Java (Spring Boot), responsável por regras de negócio, autenticação e integrações.  
- Banco de dados: MySQL armazenando usuários, serviços sociais, categorias, favoritos, histórico e logs.

Os módulos principais seguem os casos de uso documentados: Manter Serviços Sociais, Ver Rotas para o Local, Fazer Login, Manter Administradores/Usuário, Gerenciar Avisos e Estatísticas, Consultar Favoritos.

---

## Tecnologias

- Frontend  
  - React  
  - JavaScript (ES2024)  
  - CSS3 para layout responsivo e visual consistente

- Backend  
  - Java  
  - Spring Boot

- Banco de dados  
  - MySQL

- Ferramentas  
  - Visual Studio Code como IDE principal

> Ajuste esta seção se decidir incluir TailwindCSS, autenticação JWT ou outras bibliotecas adicionais.

---

## Instalação

### Backend (Spring Boot)
Pré‑requisitos: Java 17, Maven (ou Maven Wrapper) e MySQL configurado.

git clone https://github.com/AquilaOliveira/mapa-social.git

cd mapa-social/backend

./mvnw clean package

java -jar target/demo-0.0.1-SNAPSHOT.jar


### Frontend (Vite + React)

Pré‑requisitos: Node 18+.

cd ../frontend
npm install
npm run dev


Defina a URL da API no arquivo `.env` do frontend:

VITE_API_BASE=http://localhost:8080


---

## Deploy

### Backend

Pode ser implantado em um provedor compatível com aplicações Java (como Railway, Render ou similar).

Variáveis de ambiente sugeridas:

- `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`  
- `FRONTEND_ORIGIN=https://SEU_FRONTEND.vercel.app` (para CORS)

### Frontend

Pode ser publicado na Vercel ou similar, apontando para a URL pública do backend.

- `VITE_API_BASE=https://SEU_BACKEND_PUBLIC_URL`


---

## Autores

- Áquila Matheus de Oliveira  
  - LinkedIn: https://www.linkedin.com/in/aquila-oliveira  
  - GitHub: https://github.com/AquilaOliveira  

- Josiely de Oliveira Ferreira  
  - LinkedIn: https://www.linkedin.com/in/josiely-oliveira  
  - GitHub: https://github.com/Josiely-Oliveira  

- Raissa Santos Feitosa  
  - LinkedIn: https://www.linkedin.com/in/raissa-santos-feitosa-73485b1a3/  
  - GitHub: https://github.com/raissa-sf  

- Vitor de Oliveira Faria  
  - LinkedIn: https://www.linkedin.com/in/vitor-oliveira-526a28209  
  - GitHub: https://github.com/Vi1tor
