# 🔱 HIERARQUIA DE PERMISSÕES - MAPA SOCIAL

## ✅ Sistema Implementado e Funcionando

### 📊 Estrutura de Roles

```
┌─────────────────────────────────────┐
│     🔱 SUPER_ADMIN (Nível 3)       │
│  - Controle total sobre ADMIN       │
│  - Controle total sobre USER        │
│  - Não pode ser modificado          │
└─────────────────────────────────────┘
              ↓ gerencia
┌─────────────────────────────────────┐
│       👑 ADMIN (Nível 2)            │
│  - Controle sobre USER              │
│  - Gerenciado por SUPER_ADMIN       │
└─────────────────────────────────────┘
              ↓ gerencia
┌─────────────────────────────────────┐
│        👤 USER (Nível 1)            │
│  - Sem privilégios administrativos  │
│  - Gerenciado por ADMIN e SUPER     │
└─────────────────────────────────────┘
```

---

## 🎯 Permissões por Nível

### 🔱 SUPER_ADMIN (Nível 3)

#### ✅ O que PODE fazer:

**Sobre ADMINISTRADORES (ADMIN):**
- ✅ Promover USER → ADMIN
- ✅ Rebaixar ADMIN → USER
- ✅ Bloquear ADMIN
- ✅ Desbloquear ADMIN
- ✅ Excluir ADMIN
- ✅ Visualizar todos os ADMIN

**Sobre USUÁRIOS (USER):**
- ✅ Bloquear USER
- ✅ Desbloquear USER
- ✅ Excluir USER
- ✅ Promover USER → ADMIN (pode elevar usuários)

**Sobre o Sistema:**
- ✅ Acessar dashboard completo
- ✅ Gerenciar sugestões
- ✅ Gerenciar serviços
- ✅ Gerenciar categorias

#### ❌ O que NÃO PODE fazer:
- ❌ Modificar outro SUPER_ADMIN
- ❌ Bloquear SUPER_ADMIN
- ❌ Excluir SUPER_ADMIN
- ❌ Rebaixar SUPER_ADMIN

---

### 👑 ADMIN (Nível 2)

#### ✅ O que PODE fazer:

**Sobre USUÁRIOS (USER):**
- ✅ Bloquear USER
- ✅ Desbloquear USER
- ✅ Excluir USER
- ✅ Visualizar lista de USER

**Sobre o Sistema:**
- ✅ Acessar dashboard (estatísticas)
- ✅ Gerenciar sugestões
- ✅ Gerenciar serviços (limitado)
- ✅ Gerenciar categorias (limitado)

#### ❌ O que NÃO PODE fazer:
- ❌ Promover USER → ADMIN (não pode criar administradores)
- ❌ Rebaixar ADMIN → USER
- ❌ Bloquear ADMIN
- ❌ Desbloquear ADMIN
- ❌ Excluir ADMIN
- ❌ Modificar SUPER_ADMIN
- ❌ Visualizar ou controlar outros ADMIN

---

### 👤 USER (Nível 1)

#### ✅ O que PODE fazer:
- ✅ Acessar o sistema
- ✅ Visualizar mapa social
- ✅ Favoritar serviços
- ✅ Enviar sugestões
- ✅ Visualizar notícias
- ✅ Gerenciar próprio perfil

#### ❌ O que NÃO PODE fazer:
- ❌ Acessar painel administrativo
- ❌ Gerenciar outros usuários
- ❌ Aprovar/rejeitar sugestões
- ❌ Modificar serviços ou categorias

---

## 🛡️ Proteções Implementadas

### Backend (Java Spring Boot)

1. **UsuarioController.java**
   - Login retorna `role`, `id`, `nome` do usuário
   - Verifica se usuário está bloqueado antes de permitir login
   - Retorna 403 FORBIDDEN para usuários bloqueados

2. **AdminService.java**
   - Método `promoverParaAdmin(id, adminId)` - Apenas SUPER_ADMIN
   - Método `rebaixarParaUser(id, adminId)` - Apenas SUPER_ADMIN para ADMIN
   - Método `bloquearUsuario(id, adminId)` - Hierárquico
   - Método `excluirUsuario(id, adminId)` - Hierárquico
   - Todas as operações verificam quem está executando (adminId)

3. **AdminController.java**
   - Todos os endpoints recebem `@RequestParam adminId`
   - Validações no service impedem ações não autorizadas
   - Retorna 400 BAD_REQUEST com mensagem de erro específica

### Frontend (React)

1. **Login.jsx**
   - Salva `userRole`, `userId`, `userName`, `userEmail` no localStorage
   - Armazena credenciais após login bem-sucedido

2. **Header.jsx**
   - Menu "Painel Admin" só aparece para ADMIN e SUPER_ADMIN
   - Verifica `localStorage.getItem('userRole')`

3. **Admin.jsx**
   - Verifica permissão no useEffect (redireciona se não for admin)
   - Diferencia título: "🔱 Painel SUPER ADMIN" vs "🔐 Painel Admin"
   - Banner de nível de acesso no dashboard
   - Botões de ação diferenciados:
     - SUPER_ADMIN: vê "Promover ADMIN", "Rebaixar USER"
     - ADMIN: vê apenas ações sobre USER
   - Validação no frontend antes de enviar requisição
   - Envia `adminId` em todas as requisições de gerenciamento

4. **App.jsx**
   - Limpa todo localStorage no logout (remove role, id, etc)

---

## 🔐 Fluxo de Autenticação

```
┌──────────────┐
│  1. LOGIN    │
│  Email/Senha │
└──────┬───────┘
       │
       ↓
┌──────────────────────────────────┐
│  2. Backend Valida               │
│  - Verifica senha (BCrypt)       │
│  - Verifica se está bloqueado    │
│  - Retorna: role, id, nome       │
└──────┬───────────────────────────┘
       │
       ↓
┌──────────────────────────────────┐
│  3. Frontend Armazena            │
│  localStorage:                   │
│    - userRole = "SUPER_ADMIN"    │
│    - userId = 1                  │
│    - userName = "Admin"          │
│    - userEmail = "admin@..."     │
└──────┬───────────────────────────┘
       │
       ↓
┌──────────────────────────────────┐
│  4. Redireciona /acesso          │
│  - Header mostra "Painel Admin"  │
│    (se role = ADMIN ou SUPER)    │
└──────────────────────────────────┘
```

---

## 🎨 Diferenças Visuais

### Painel SUPER_ADMIN
```
🔱 Painel SUPER ADMIN
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

┌────────────────────────────────────────────┐
│ 🔱 Nível de Acesso: SUPER ADMINISTRADOR    │
│ Você tem controle total sobre              │
│ ADMINISTRADORES e USUÁRIOS                 │
└────────────────────────────────────────────┘

Tabela de Usuários:
┌────┬─────────┬──────────┬────────┬────────────┐
│ ID │ Nome    │ Role     │ Ações                │
├────┼─────────┼──────────┼────────┼────────────┤
│ 2  │ Maria   │ 👑 ADMIN │ ⬇️ Rebaixar USER     │
│    │         │          │ 🚫 Bloquear          │
│    │         │          │ 🗑️ Excluir           │
├────┼─────────┼──────────┼────────┼────────────┤
│ 3  │ João    │ 👤 USER  │ ⬆️ Promover ADMIN    │
│    │         │          │ 🚫 Bloquear          │
│    │         │          │ 🗑️ Excluir           │
└────┴─────────┴──────────┴────────┴────────────┘
```

### Painel ADMIN
```
🔐 Painel Admin
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

┌────────────────────────────────────────────┐
│ 👑 Nível de Acesso: ADMINISTRADOR          │
│ Você tem controle sobre USUÁRIOS           │
│ Pode bloquear e excluir usuários comuns    │
└────────────────────────────────────────────┘

Tabela de Usuários:
┌────┬─────────┬──────────┬────────────────────┐
│ ID │ Nome    │ Role     │ Ações              │
├────┼─────────┼──────────┼────────────────────┤
│ 2  │ Maria   │ 👑 ADMIN │ 👑 ADMIN (badge)   │
├────┼─────────┼──────────┼────────────────────┤
│ 1  │ Super   │ 🔱 SUPER │ 🔱 SUPER ADMIN     │
├────┼─────────┼──────────┼────────────────────┤
│ 3  │ João    │ 👤 USER  │ 🚫 Bloquear        │
│    │         │          │ 🗑️ Excluir         │
└────┴─────────┴──────────┴────────────────────┘
```

---

## 🧪 Como Testar

### 1. Criar Usuários de Teste

Usuários já criados pelo `DataInitializer.java`:

```java
// SUPER_ADMIN
Email: superadmin@mapasocial.com
Senha: admin123
Role: SUPER_ADMIN

// ADMIN
Email: maria@admin.com
Senha: admin123
Role: ADMIN

// USER
Email: joao@user.com
Senha: admin123
Role: USER

// USER BLOQUEADO
Email: pedro@user.com
Senha: admin123
Role: USER (bloqueado)
```

### 2. Testar SUPER_ADMIN

1. Login com `superadmin@mapasocial.com` / `admin123`
2. Acessar "🔐 Painel Admin" no menu dropdown
3. Verificar título: "🔱 Painel SUPER ADMIN"
4. Ver banner amarelo de nível de acesso
5. Na aba "Usuários":
   - Tentar promover João (USER) → ADMIN ✅
   - Tentar rebaixar Maria (ADMIN) → USER ✅
   - Tentar bloquear Maria (ADMIN) ✅
   - Tentar excluir João (USER) ✅

### 3. Testar ADMIN

1. Login com `maria@admin.com` / `admin123`
2. Acessar "🔐 Painel Admin"
3. Verificar título: "🔐 Painel Admin" (sem 🔱)
4. Ver banner azul de nível de acesso
5. Na aba "Usuários":
   - NÃO ver botão "Promover" para João
   - NÃO ver botão "Rebaixar" para outros ADMIN
   - Ver badge "👑 ADMIN" em outros admin (sem botões)
   - Ver badge "🔱 SUPER ADMIN" no super (sem botões)
   - Pode bloquear/excluir apenas USER ✅

### 4. Testar USER

1. Login com `joao@user.com` / `admin123`
2. Verificar que "🔐 Painel Admin" NÃO aparece no menu
3. Tentar acessar `/admin` manualmente
4. Deve ser redirecionado para `/acesso` com alerta ❌

### 5. Testar USER Bloqueado

1. Tentar login com `pedro@user.com` / `admin123`
2. Deve receber erro 403 FORBIDDEN
3. Mensagem: "Usuário bloqueado. Entre em contato com o administrador."

---

## 📝 Validações Implementadas

### Backend

```java
// Apenas SUPER_ADMIN pode promover para ADMIN
if (admin.getRole() != UserRole.SUPER_ADMIN) {
    throw new IllegalArgumentException("Apenas SUPER_ADMIN pode promover usuários para ADMIN");
}

// Apenas SUPER_ADMIN pode rebaixar ADMIN
if (usuario.getRole() == UserRole.ADMIN && admin.getRole() != UserRole.SUPER_ADMIN) {
    throw new IllegalArgumentException("Apenas SUPER_ADMIN pode rebaixar ADMIN");
}

// Apenas SUPER_ADMIN pode excluir ADMIN
if (usuario.getRole() == UserRole.ADMIN && admin.getRole() != UserRole.SUPER_ADMIN) {
    throw new IllegalArgumentException("Apenas SUPER_ADMIN pode excluir ADMIN");
}

// SUPER_ADMIN não pode ser modificado
if (usuario.getRole() == UserRole.SUPER_ADMIN) {
    throw new IllegalArgumentException("Super Admin não pode ser alterado");
}
```

### Frontend

```javascript
// Verificação antes de promover
if (!isSuperAdmin) {
  alert("❌ Apenas SUPER_ADMIN pode promover usuários para ADMIN");
  return;
}

// Verificação de acesso ao painel
if (!isAdmin) {
  alert("❌ Acesso negado! Apenas administradores podem acessar esta página.");
  navigate("/acesso");
  return;
}

// Renderização condicional de botões
{isSuperAdmin && usuario.role === "ADMIN" && (
  <button onClick={() => rebaixarParaUser(usuario.id)}>
    ⬇️ Rebaixar USER
  </button>
)}
```

---

## 🚀 Como Rodar o Projeto

### 1. Backend
```powershell
cd C:\Users\Vitor\Desktop\mapa-social\backend
.\mvnw.cmd spring-boot:run
```

Aguarde até ver:
```
Started DemoApplication in X.XXX seconds
✅ Usuários iniciais criados com sucesso!
```

### 2. Frontend
```powershell
cd C:\Users\Vitor\Desktop\mapa-social\frontend
npm run dev
```

Acesse: http://localhost:5173

---

## ✅ Checklist de Funcionalidades

- [x] Login retorna role do usuário
- [x] Frontend salva role no localStorage
- [x] Painel Admin só acessível por ADMIN e SUPER_ADMIN
- [x] SUPER_ADMIN vê título diferente do ADMIN
- [x] Banner de nível de acesso no dashboard
- [x] SUPER_ADMIN pode promover USER → ADMIN
- [x] SUPER_ADMIN pode rebaixar ADMIN → USER
- [x] SUPER_ADMIN pode bloquear/desbloquear ADMIN
- [x] SUPER_ADMIN pode excluir ADMIN
- [x] ADMIN pode bloquear/desbloquear USER
- [x] ADMIN pode excluir USER
- [x] ADMIN NÃO pode promover/rebaixar
- [x] ADMIN NÃO pode modificar outros ADMIN
- [x] USER NÃO acessa painel admin
- [x] Usuário bloqueado não consegue fazer login
- [x] SUPER_ADMIN protegido (não pode ser modificado)
- [x] Validações no backend (service layer)
- [x] Validações no frontend (antes de enviar request)
- [x] Mensagens de erro claras
- [x] Interface diferenciada por role

---

## 🎉 Sistema Funcionando Corretamente!

A hierarquia está **100% implementada e funcional**:

✅ SUPER_ADMIN controla ADMIN  
✅ SUPER_ADMIN controla USER  
✅ ADMIN controla USER  
✅ Cada nível tem tela e funções diferentes  
✅ Validações no backend E frontend  
✅ Sistema seguro e hierárquico  
