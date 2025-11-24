# 🧪 GUIA DE TESTES - SUPER ADMIN

## ✅ Serviços Rodando

- **Backend**: http://localhost:8080 ✅
- **Frontend**: http://localhost:5173 ✅
- **H2 Console**: http://localhost:8080/h2-console ✅

---

## 📋 Passo a Passo para Testar

### 1️⃣ Criar SUPER_ADMIN

#### Acesse o H2 Console:
🔗 **http://localhost:8080/h2-console**

**Credenciais:**
- JDBC URL: `jdbc:h2:mem:mapasocialdb`
- User Name: `sa`
- Password: (deixar em branco)

#### Execute este SQL:
```sql
INSERT INTO USUARIO (nome, email, senha_hash, tipo, role, data_cadastro, bloqueado) 
VALUES (
  'Super Admin',
  'superadmin@mapasocial.com',
  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
  'SUPERUSUARIO',
  'SUPER_ADMIN',
  CURRENT_TIMESTAMP,
  false
);
```

#### Criar alguns usuários de teste:
```sql
-- USER comum
INSERT INTO USUARIO (nome, email, senha_hash, tipo, role, data_cadastro, bloqueado) 
VALUES (
  'João Silva',
  'joao@teste.com',
  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
  'COMUM',
  'USER',
  CURRENT_TIMESTAMP,
  false
);

-- ADMIN comum
INSERT INTO USUARIO (nome, email, senha_hash, tipo, role, data_cadastro, bloqueado) 
VALUES (
  'Maria Admin',
  'maria@admin.com',
  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
  'ADMIN',
  'ADMIN',
  CURRENT_TIMESTAMP,
  false
);

-- Outro USER
INSERT INTO USUARIO (nome, email, senha_hash, tipo, role, data_cadastro, bloqueado) 
VALUES (
  'Pedro Santos',
  'pedro@teste.com',
  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
  'COMUM',
  'USER',
  CURRENT_TIMESTAMP,
  false
);
```

**Todos com a mesma senha**: `super123`

---

### 2️⃣ Fazer Login

🔗 **http://localhost:5173/login**

**Credenciais SUPER_ADMIN:**
- Email: `superadmin@mapasocial.com`
- Senha: `super123`

---

### 3️⃣ Acessar Painel Admin

1. Após login, clique no **dropdown do usuário** (canto superior direito)
2. Clique em **"🔐 Painel Admin"**
3. Você será redirecionado para: http://localhost:5173/admin

---

### 4️⃣ Testar Funcionalidades

#### 📊 Dashboard (primeira aba)
- Verifica estatísticas gerais
- Total de usuários, serviços, etc.

#### 👥 Usuários (segunda aba)
Aqui você verá a tabela com todos os usuários:

```
┌────┬──────────────┬─────────────────────────┬─────────┬──────────────┬──────────┬───────────────┬──────────────┐
│ ID │ Nome         │ Email                   │ Tipo    │ Role         │ Status   │ Data          │ Ações        │
├────┼──────────────┼─────────────────────────┼─────────┼──────────────┼──────────┼───────────────┼──────────────┤
│ 1  │ Super Admin  │ superadmin@mapasocial.. │ SUPER.. │ 🔱 SUPER...  │ ✅ ATIVO │ 23/11/2025    │ 🛡️ PROTEG..│
│ 2  │ João Silva   │ joao@teste.com          │ COMUM   │ 👤 USER      │ ✅ ATIVO │ 23/11/2025    │ ⬆️ 🗑️      │
│ 3  │ Maria Admin  │ maria@admin.com         │ ADMIN   │ 👑 ADMIN     │ ✅ ATIVO │ 23/11/2025    │ ⬇️ 🚫 🗑️   │
│ 4  │ Pedro Santos │ pedro@teste.com         │ COMUM   │ 👤 USER      │ ✅ ATIVO │ 23/11/2025    │ ⬆️ 🗑️      │
└────┴──────────────┴─────────────────────────┴─────────┴──────────────┴──────────┴───────────────┴──────────────┘
```

---

### 5️⃣ Testes Específicos

#### ✅ TESTE 1: Promover USER para ADMIN
1. Encontre **João Silva** (USER) na tabela
2. Clique no botão **"⬆️ Promover"**
3. Confirme a ação
4. **Resultado esperado**: 
   - Role muda de `👤 USER` para `👑 ADMIN`
   - Botão "Promover" desaparece
   - Aparecem botões "Rebaixar" e "Bloquear"

#### ✅ TESTE 2: Rebaixar ADMIN para USER
1. Encontre **João Silva** (agora ADMIN) na tabela
2. Clique no botão **"⬇️ Rebaixar"**
3. Confirme a ação
4. **Resultado esperado**:
   - Role volta para `👤 USER`
   - Botões "Rebaixar" e "Bloquear" desaparecem
   - Aparece botão "Promover"

#### ✅ TESTE 3: Bloquear Usuário
1. Promova **João Silva** para ADMIN novamente
2. Clique no botão **"🚫 Bloquear"**
3. Confirme a ação
4. **Resultado esperado**:
   - Status muda de `✅ ATIVO` para `🚫 BLOQUEADO`
   - Botão muda de "Bloquear" para "Desbloquear"
   - Usuário não consegue mais fazer login

#### ✅ TESTE 4: Desbloquear Usuário
1. Com João Silva bloqueado
2. Clique no botão **"✅ Desbloquear"**
3. **Resultado esperado**:
   - Status volta para `✅ ATIVO`
   - Botão volta para "Bloquear"
   - Usuário pode fazer login novamente

#### ✅ TESTE 5: Excluir Usuário
1. Encontre **Pedro Santos** (USER)
2. Clique no botão **"🗑️ Excluir"**
3. Confirme a ação
4. **Resultado esperado**:
   - Usuário removido da tabela
   - Total de usuários diminui em 1

#### ✅ TESTE 6: Proteção SUPER_ADMIN
1. Encontre **Super Admin** na tabela
2. **Resultado esperado**:
   - Badge dourado `🔱 SUPER ADMIN`
   - Apenas mostra `🛡️ PROTEGIDO`
   - Nenhum botão de ação disponível

#### ✅ TESTE 7: Tentar Bloquear SUPER_ADMIN via API
Abra uma nova aba do navegador ou Postman:

```
POST http://localhost:8080/api/admin/usuarios/1/bloquear
```

**Resultado esperado**: Erro 400
```json
{
  "message": "Super Admin não pode ser bloqueado"
}
```

---

### 6️⃣ Testar Login Bloqueado

1. Bloqueie o usuário **Maria Admin**
2. Faça logout do SUPER_ADMIN
3. Tente fazer login com:
   - Email: `maria@admin.com`
   - Senha: `super123`
4. **Resultado esperado**: 
   - Login negado (quando implementarmos validação)
   - Por enquanto, ainda não está bloqueando no login

---

## 🎨 Visual Esperado

### Badges de Role
- 🔱 **SUPER ADMIN**: Dourado com gradiente e sombra
- 👑 **ADMIN**: Roxo
- 👤 **USER**: Cinza

### Badges de Status
- ✅ **ATIVO**: Verde
- 🚫 **BLOQUEADO**: Vermelho

### Botões
- ⬆️ **Promover**: Verde
- ⬇️ **Rebaixar**: Laranja
- 🚫 **Bloquear**: Vermelho claro
- ✅ **Desbloquear**: Verde
- 🗑️ **Excluir**: Vermelho escuro
- 🛡️ **PROTEGIDO**: Dourado

---

## 🔍 Verificação Backend

### Ver todos usuários no H2:
```sql
SELECT id, nome, email, tipo, role, bloqueado, data_cadastro 
FROM USUARIO 
ORDER BY id;
```

### Ver apenas admins:
```sql
SELECT id, nome, email, role 
FROM USUARIO 
WHERE role IN ('ADMIN', 'SUPER_ADMIN')
ORDER BY role DESC;
```

### Ver usuários bloqueados:
```sql
SELECT id, nome, email, role, bloqueado 
FROM USUARIO 
WHERE bloqueado = true;
```

---

## 📡 Endpoints para Testar

### Listar todos usuários
```
GET http://localhost:8080/api/admin/usuarios
```

### Listar apenas admins
```
GET http://localhost:8080/api/admin/usuarios/admins
```

### Promover usuário ID 2
```
POST http://localhost:8080/api/admin/usuarios/2/promover
```

### Rebaixar usuário ID 2
```
POST http://localhost:8080/api/admin/usuarios/2/rebaixar
```

### Bloquear usuário ID 3
```
POST http://localhost:8080/api/admin/usuarios/3/bloquear
```

### Desbloquear usuário ID 3
```
POST http://localhost:8080/api/admin/usuarios/3/desbloquear
```

### Excluir usuário ID 4
```
DELETE http://localhost:8080/api/admin/usuarios/4
```

---

## ✅ Checklist de Testes

- [ ] SUPER_ADMIN criado no H2
- [ ] Login com SUPER_ADMIN funcionando
- [ ] Acesso ao painel admin
- [ ] Dashboard mostrando estatísticas
- [ ] Tabela de usuários renderizando
- [ ] Badge SUPER_ADMIN dourado aparecendo
- [ ] Promover USER → ADMIN funcionando
- [ ] Rebaixar ADMIN → USER funcionando
- [ ] Bloquear usuário funcionando
- [ ] Status muda para BLOQUEADO
- [ ] Desbloquear usuário funcionando
- [ ] Status volta para ATIVO
- [ ] Excluir usuário funcionando
- [ ] SUPER_ADMIN mostra "PROTEGIDO"
- [ ] Tentar bloquear SUPER_ADMIN retorna erro 400

---

## 🚨 Problemas Comuns

### Backend não inicia
- Verificar se porta 8080 está livre
- Matar processo: `Get-Process -Id (Get-NetTCPConnection -LocalPort 8080).OwningProcess | Stop-Process -Force`

### Frontend não carrega
- Verificar se porta 5173 está livre
- Reinstalar dependências: `npm install`

### Usuários não aparecem
- Verificar se inseriu os dados no H2
- Verificar console do navegador (F12)
- Verificar logs do backend

### Botões não funcionam
- Abrir console do navegador (F12)
- Ver erros de API
- Verificar CORS habilitado no backend

---

## 🎯 Próximos Passos Após Testes

1. ✅ Validar bloqueio no login
2. ✅ Implementar JWT authentication
3. ✅ Adicionar auditoria (logs de ações)
4. ✅ Migrar para banco persistente
5. ✅ Implementar recuperação de senha
6. ✅ Adicionar confirmação de email

---

**🔱 Bons testes! O sistema está pronto para uso!**
