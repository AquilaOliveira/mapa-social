# 🎯 SCRIPTS DISPONÍVEIS - MAPA SOCIAL

## 📦 Resumo dos Scripts Criados

| Script | Função | Terminal Necessário |
|--------|--------|-------------------|
| `start-backend.ps1` | Inicia o servidor Spring Boot (porta 8080) | Terminal 1 (manter aberto) |
| `start-frontend.ps1` | Inicia o servidor Vite React (porta 5173) | Terminal 2 (manter aberto) |
| `populate-database.ps1` | Popula o banco com dados dos serviços | Terminal 3 (executa e fecha) |

---

## 🚀 INÍCIO RÁPIDO

### 1. Abra o primeiro terminal PowerShell:
```powershell
cd C:\Users\Vitor\Desktop\mapa-social
.\start-backend.ps1
```
✅ Aguarde: "Started DemoApplication"  
⚠️ Deixe este terminal aberto!

---

### 2. Abra o segundo terminal PowerShell:
```powershell
cd C:\Users\Vitor\Desktop\mapa-social
.\start-frontend.ps1
```
✅ Aguarde: "Local: http://localhost:5173/"  
⚠️ Deixe este terminal aberto!

---

### 3. Abra o terceiro terminal PowerShell (apenas uma vez):
```powershell
cd C:\Users\Vitor\Desktop\mapa-social
.\populate-database.ps1
```
✅ Insira usuário e senha do MySQL  
✅ Este script pode ser fechado após executar

---

## 🗺️ CONFIGURAR GOOGLE MAPS

Edite: `frontend/src/config/maps.config.js`

```javascript
export const GOOGLE_MAPS_API_KEY = "SUA_CHAVE_AQUI";
```

**Onde conseguir a chave:**
1. https://console.cloud.google.com/
2. Criar projeto → APIs & Services → Library
3. Ativar "Maps JavaScript API"
4. Credentials → Create API Key

---

## 📂 ESTRUTURA DE ARQUIVOS

```
mapa-social/
├── start-backend.ps1          ← Script para iniciar backend
├── start-frontend.ps1         ← Script para iniciar frontend
├── populate-database.ps1      ← Script para popular banco
├── COMO-RODAR.md             ← Guia detalhado
├── PROXIMOS-PASSOS.md        ← Checklist completo
├── demo/                      ← Backend Spring Boot
│   ├── mvnw.cmd              ← Maven Wrapper
│   ├── pom.xml               ← Dependências Java
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   └── resources/
│       │       ├── application.properties
│       │       └── data/
│       │           └── servicos-braganca-paulista.sql
│       └── test/
└── frontend/                  ← Frontend React + Vite
    ├── package.json          ← Dependências Node
    ├── src/
    │   ├── components/
    │   │   └── Map/
    │   │       ├── InteractiveMap.jsx
    │   │       └── InteractiveMap.css
    │   ├── config/
    │   │   └── maps.config.js  ← Configurar API Key aqui!
    │   └── pages/
    │       └── HomePage.jsx
    └── vite.config.ts
```

---

## 🔍 VERIFICAR STATUS

### Backend (porta 8080):
```powershell
curl http://localhost:8080/servicos-sociais
```
✅ Deve retornar JSON com serviços

### Frontend (porta 5173):
```powershell
curl http://localhost:5173
```
✅ Deve retornar HTML da página

### Banco de Dados:
```powershell
mysql -u root -p mapa_social_db -e "SELECT COUNT(*) FROM servico_social;"
```
✅ Deve retornar número de registros

---

## ⚠️ SOLUÇÃO DE PROBLEMAS COMUNS

### Porta 8080 ocupada:
```powershell
Get-NetTCPConnection -LocalPort 8080 | Select-Object OwningProcess
Stop-Process -Id XXXX -Force
```

### Porta 5173 ocupada:
```powershell
Get-NetTCPConnection -LocalPort 5173 | Select-Object OwningProcess
Stop-Process -Id XXXX -Force
```

### MySQL não conecta:
```powershell
Get-Service MySQL* | Select-Object Name, Status
Start-Service MySQL84  # Ajuste o nome
```

### Script PowerShell não executa:
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

---

## 📊 ENDPOINTS DA API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/servicos-sociais` | Lista todos os serviços |
| GET | `/servicos-sociais/{id}` | Busca serviço por ID |
| GET | `/servicos-sociais/categoria/{categoria}` | Filtra por categoria |
| POST | `/servicos-sociais` | Cria novo serviço |
| PUT | `/servicos-sociais/{id}` | Atualiza serviço |
| DELETE | `/servicos-sociais/{id}` | Remove serviço |

---

## 🎨 TECNOLOGIAS UTILIZADAS

**Backend:**
- Java 17
- Spring Boot 3.5.7
- Spring Data JPA
- MySQL 8.4.3
- Maven

**Frontend:**
- React 19.1.1
- Vite 7.1.9
- Google Maps JavaScript API
- CSS3

---

## ✅ CHECKLIST COMPLETO

- [ ] MySQL instalado e rodando
- [ ] Backend iniciado (Terminal 1)
- [ ] Frontend iniciado (Terminal 2)
- [ ] Banco de dados populado
- [ ] Google Maps API Key configurada
- [ ] Testado em http://localhost:5173
- [ ] Mapa exibindo marcadores
- [ ] Busca funcionando
- [ ] Filtros funcionando

---

## 📞 DÚVIDAS?

Consulte os arquivos:
- `COMO-RODAR.md` - Guia detalhado
- `PROXIMOS-PASSOS.md` - Checklist passo a passo
- `README.md` - Documentação do projeto

**Boa sorte! 🚀**
