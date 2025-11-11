# Arquitetura e Tecnologias — Mapa Social

Este documento descreve a arquitetura geral do sistema, as tecnologias adotadas e o fluxo de integração entre os componentes do projeto Mapa Social.

## Arquitetura Geral

O sistema segue uma arquitetura em camadas, separando responsabilidades entre frontend, backend e banco de dados.

Usuário
↓
Interface Web (React + Material UI)
↓
API REST (FastAPI / Python)
↓
Banco de Dados (SQLite)


## Camadas do Sistema

### 1. Frontend
- Desenvolvido em React.js.  
- Interface moderna e responsiva com Material UI.  
- Integração com API de Mapas (Google Maps / Leaflet).  
- Consumo da API backend via Axios ou Fetch.  
- Controle de autenticação com JWT ou Firebase Auth.  
- Rotas gerenciadas com React Router.


### 2. Backend
- Implementado com FastAPI (Python).  
- Estrutura modular com separação de rotas e controladores.  
- Comunicação via REST API em formato JSON.  
- Conexão com o banco de dados via SQLAlchemy.  
- Autenticação JWT e camadas de segurança.  
- Validação de dados com Pydantic.


### 3. Banco de Dados
- Banco SQLite (em desenvolvimento local).  
- Tabelas principais:
  - usuarios
  - servicos_sociais
  - sugestoes
  - favoritos
  - avisos
  - logs

Relacionamentos entre usuários e sugestões, favoritos e serviços sociais.

> Em versão futura, o banco poderá ser migrado para MySQL ou PostgreSQL.

## Integração com API de Mapas
- Utiliza Google Maps API ou Leaflet.js para exibir o mapa interativo.  
- Permite geolocalização via navigator.geolocation (HTML5).  
- Exibe marcadores com detalhes e rotas até o serviço selecionado.

## Segurança
- Criptografia de senhas com bcrypt.  
- Tokens de acesso com JWT.  
- Logs de auditoria para ações administrativas.  
- Políticas de acesso baseadas em papéis (usuário comum, administrador, TI).

## Fluxo Simplificado de Operação

sequenceDiagram
    participant U as Usuário
    participant F as Frontend (React)
    participant B as Backend (FastAPI)
    participant DB as Banco de Dados

    U->>F: Acessa a plataforma
    F->>B: Requisição de login
    B->>DB: Valida credenciais
    DB-->>B: Retorna usuário
    B-->>F: Token JWT
    F->>B: Solicita lista de serviços sociais
    B->>DB: Consulta serviços
    DB-->>B: Retorna dados
    B-->>F: Exibe no mapa

## Tecnologias Complementares

A seguir, estão listadas as principais ferramentas utilizadas no desenvolvimento e documentação do projeto.

| Categoria              | Ferramentas  |
| ---------------------- | ------------ |
| Versionamento          | Git / GitHub |
| Design                 | Figma        |
| Documentação           | Markdown     |
| Ambiente Virtual       | venv         |
| Gerenciador de Pacotes | pip / npm    |

**Fonte:** Autoria própria (2025) – Projeto Mapa Social – IFSP Bragança Paulista
