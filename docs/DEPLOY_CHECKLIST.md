# Checklist de Deploy (Mapa Social)

Este documento guia o deploy completo (backend Railway + frontend Vercel) e validação funcional.

## 1. Pré-requisitos Locais
- Java 17 instalado
- Node.js 18+ instalado
- Maven Wrapper (`./mvnw`) funcionando
- Repositórios separados ou monorepo já publicados no GitHub:
  - Backend: `backend/`
  - Frontend: `frontend/`

## 2. Backend – Railway (PostgreSQL)
1. Criar projeto Railway (New Project > Deploy From GitHub > selecionar repositório backend).
2. Adicionar Postgres (Add Plugin > PostgreSQL). Railway cria variáveis: `PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER`, `PGPASSWORD`.
3. Confirmar build automático: Railway detecta Maven (senão definir Build Command: `./mvnw -B clean package`).
4. Start Command: `java -jar target/demo-0.0.1-SNAPSHOT.jar`.
5. Verificar logs:
   - Flyway aplicando `V1__baseline.sql`.
   - Mensagens de seed (categorias/serviços) sem erros de duplicidade.
6. Adicionar variável opcional para CORS:
   - `FRONTEND_ORIGIN=https://SEU_FRONTEND.vercel.app`
7. Copiar URL pública do backend (ex: `https://mapa-social-production.up.railway.app`).

## 3. Flyway & Hibernate
- Arquivo baseline: `src/main/resources/db/migration/V1__baseline.sql`.
- Hibernate configurado com `ddl-auto=validate`.
- Próximas alterações de schema: criar `V2__...sql`, `V3__...sql` etc.

## 4. Frontend – Vercel
1. Importar repositório frontend no Vercel.
2. Framework: Vite (auto detectado) ou "Other".
3. Variáveis de ambiente:
   - `VITE_API_BASE=https://URL_BACKEND_RAILWAY`
4. Deploy e aguardar build.
5. Testar URL Vercel (home, mapa, login, solicitar serviço).

## 5. Arquivo de Ambiente Frontend
- Usar `.env` em desenvolvimento local:
```
VITE_API_BASE=http://localhost:8080
```
- Em produção Vercel usar painel de Environment Variables.

## 6. Teste Funcional Pós-Deploy
[ ] Acessar frontend público.
[ ] Realizar login (usuário seed). 
[ ] Ver mapa com pins (10 serviços Bragança Paulista).
[ ] Abrir popup e clicar "Como Chegar" (links externos corretos).
[ ] Solicitar serviço (criar nova solicitação) e verificar retorno 200.
[ ] Listar solicitações do usuário.
[ ] Ver status e timestamps corretos.

## 7. CI (GitHub Actions)
- Workflow `backend/.github/workflows/backend-ci.yml` roda build e testes.
- Verificar na aba Actions se está verde antes de merge.

## 8. Troubleshooting Rápido
| Problema | Causa Provável | Ação |
|----------|----------------|------|
| Flyway erro de versão | Arquivo duplicado ou ordem errada | Renomear sequência e redeploy |
| CORS bloqueado | Variável FRONTEND_ORIGIN ausente | Adicionar e reiniciar backend |
| Seed duplicado | Lógica DataInitializer sem verificação | Adicionar checagem antes de salvar |
| 404 endpoints | Caminho base alterado | Confirmar `@RequestMapping` e URL base no frontend |
| Timeout no mapa | Backend lento / sem resposta | Ver logs Railway, reiniciar serviço |

## 9. Próximos Passos Futuro
- Adicionar autenticação JWT.
- Configurar observabilidade (Actuator + métricas).
- Criar testes de integração para endpoints críticos.
- Pipeline para deploy automatizado (ex: GitHub Action disparando redeploy Railway via API).

## 10. Comandos Locais Úteis
```bash
# Backend build
cd backend
./mvnw clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar

# Frontend dev
cd ../frontend
npm install
npm run dev
```

## 11. Variáveis Resumo
Backend:
- PGHOST, PGPORT, PGDATABASE, PGUSER, PGPASSWORD (Railway)
- FRONTEND_ORIGIN (manual)

Frontend:
- VITE_API_BASE (Railway backend URL)

---
Checklist concluído: marque cada item ao finalizar o fluxo.
