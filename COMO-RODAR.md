# 🚀 GUIA RÁPIDO - COMO RODAR O PROJETO

## ✅ SOLUÇÃO DO ERRO DO MVNW

### **Problema:**
```
.\mvnw.cmd : O termo '.\mvnw.cmd' não é reconhecido...
```

### **Solução:**
Criamos scripts automatizados para facilitar!

---

## 🎯 COMO INICIAR O PROJETO

### ⚠️ **IMPORTANTE:** 
**Você precisa abrir DOIS terminais PowerShell separados:**
- **Terminal 1:** Para rodar o backend
- **Terminal 2:** Para rodar o frontend

**NÃO execute comandos adicionais nos terminais dos servidores, pois isso pode interrompê-los!**

---

### **Opção 1: Usando os Scripts Criados (RECOMENDADO)**

#### **Terminal 1 - Backend (PowerShell):**
```powershell
# Na raiz do projeto (mapa-social):
.\start-backend.ps1
```

#### **Terminal 2 - Frontend (PowerShell):**
```powershell
# Na raiz do projeto (mapa-social):
.\start-frontend.ps1
```

#### **Alternativa - CMD:**
```cmd
start-backend.bat
```

---

### **Opção 2: Manualmente**

#### **Backend (Spring Boot):**
```powershell
# Navegue para a pasta demo primeiro:
cd C:\Users\Vitor\Desktop\mapa-social\demo

# Depois execute o mvnw:
.\mvnw.cmd spring-boot:run
```

#### **Frontend (Vite):**
```powershell
# Em outro terminal:
cd C:\Users\Vitor\Desktop\mapa-social\frontend
npm run dev
```

---

## 📋 ORDEM DE INICIALIZAÇÃO

### **1️⃣ Primeiro: Backend**
```powershell
cd demo
.\mvnw.cmd spring-boot:run
```

**Aguarde até ver:**
```
Started DemoApplication in X.XXX seconds
```

**Backend estará em:** http://localhost:8080

---

### **2️⃣ Depois: Frontend**
```powershell
# Em OUTRO terminal:
cd frontend
npm run dev
```

**Frontend estará em:** http://localhost:5173

---

## ✅ VERIFICAR SE ESTÁ FUNCIONANDO

### **Teste o Backend:**
```powershell
curl http://localhost:8080/servicos-sociais
```

**Resposta esperada:** Lista de serviços (pode ser vazia `[]`)

### **Teste o Frontend:**
Abra no navegador: http://localhost:5173

---

## 🛠️ TROUBLESHOOTING

### **❌ Erro: "Porta 8080 já está em uso"**

**Solução 1 - PowerShell:**
```powershell
# Encontrar o processo:
Get-NetTCPConnection -LocalPort 8080

# Matar o processo (substitua PID):
Stop-Process -Id [PID] -Force
```

**Solução 2 - CMD:**
```cmd
# Encontrar o processo:
netstat -ano | findstr :8080

# Matar o processo (substitua PID):
taskkill /F /PID [PID]
```

---

### **❌ Erro: "Não foi possível carregar os serviços"**

**Causa:** Backend não está rodando

**Solução:**
1. Verifique se o backend está rodando (porta 8080)
2. Verifique se o MySQL está rodando
3. Execute o script SQL para popular o banco

---

### **❌ Mapa não aparece (mostra aviso amarelo)**

**Causa:** Google Maps API Key não configurada

**Solução:**
1. Edite: `frontend/src/config/maps.config.js`
2. Substitua `"YOUR_API_KEY"` pela sua chave
3. Como obter: https://console.cloud.google.com/

**Ou use sem mapa:** O sistema funciona mostrando os serviços em lista

---

## 📊 POPULAR O BANCO DE DADOS

Se o sistema mostrar "0 serviços":

```sql
-- Execute no MySQL:
SOURCE C:/Users/Vitor/Desktop/mapa-social/demo/src/main/resources/data/servicos-braganca-paulista.sql;
```

Ou copie e cole o conteúdo no MySQL Workbench.

---

## 🎯 RESUMO DOS COMANDOS

### **Iniciar tudo:**
```powershell
# Terminal 1 - Backend:
.\start-backend.ps1

# Terminal 2 - Frontend:
cd frontend
npm run dev
```

### **Parar tudo:**
- **Backend:** `Ctrl + C` no terminal
- **Frontend:** `Ctrl + C` no terminal

---

## 📁 ESTRUTURA DOS SCRIPTS CRIADOS

```
mapa-social/
├── start-backend.ps1     ← Script PowerShell (USE ESTE!)
├── start-backend.bat     ← Script CMD (alternativa)
├── demo/                 ← Backend Spring Boot
└── frontend/             ← Frontend React + Vite
```

---

## ✨ TUDO FUNCIONANDO?

Você deve ter:

- ✅ Backend: http://localhost:8080
- ✅ Frontend: http://localhost:5173
- ✅ MySQL: localhost:3306/mapa_social_db

**Agora é só acessar** http://localhost:5173 **e usar o Mapa Social!** 🎉

---

**Criado com ❤️ para facilitar seu desenvolvimento**
