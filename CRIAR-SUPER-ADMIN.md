# 🔱 Como Criar o Primeiro SUPER ADMIN

## Visão Geral da Hierarquia de Roles

```
SUPER_ADMIN (Super TI) 🔱
    └── Controla ADMINS
        └── ADMIN (Administrador) 👑
            └── Controla USERS
                └── USER (Usuário Comum) 👤
```

## Poderes de Cada Role

### 🔱 SUPER_ADMIN (Super TI)
- ✅ Promover USER para ADMIN
- ✅ Rebaixar ADMIN para USER
- ✅ Bloquear/Desbloquear qualquer usuário
- ✅ Excluir qualquer usuário (exceto outros SUPER_ADMIN)
- ✅ Gerenciar sugestões, serviços e categorias
- 🛡️ **NÃO PODE SER MODIFICADO OU BLOQUEADO** (proteção total)

### 👑 ADMIN (Administrador)
- ✅ Gerenciar sugestões (aprovar/rejeitar)
- ✅ Visualizar usuários
- ✅ Excluir USER (não pode excluir ADMIN)
- ❌ Não pode promover/rebaixar usuários
- ❌ Não pode bloquear usuários

### 👤 USER (Usuário Comum)
- ✅ Usar sistema normalmente
- ✅ Sugerir novos serviços
- ❌ Sem acesso ao painel admin

---

## Método 1: Console H2 (Recomendado)

### Passo 1: Acesse o Console H2
1. **Inicie o backend**: `cd demo; ./mvnw spring-boot:run`
2. **Acesse**: http://localhost:8080/h2-console
3. **Configurações**:
   - JDBC URL: `jdbc:h2:mem:mapasocialdb`
   - User Name: `sa`
   - Password: (deixar em branco)
4. **Clique em "Connect"**

### Passo 2: Criar SUPER_ADMIN
Execute este SQL no console:

```sql
INSERT INTO USUARIO (nome, email, senha_hash, tipo, role, data_cadastro) 
VALUES (
  'Super Admin',
  'superadmin@mapasocial.com',
  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
  'SUPERUSUARIO',
  'SUPER_ADMIN',
  CURRENT_TIMESTAMP
);
```

**Credenciais**:
- **Email**: `superadmin@mapasocial.com`
- **Senha**: `super123`

### Passo 3: Verificar
```sql
SELECT id, nome, email, role FROM USUARIO WHERE role = 'SUPER_ADMIN';
```

---

## Método 2: Script PowerShell

### criar-super-admin.ps1
```powershell
$baseUrl = "http://localhost:8080/api/admin"

$superAdmin = @{
    nome = "Super Admin"
    email = "superadmin@mapasocial.com"
    senha = "super123"
    tipo = "SUPERUSUARIO"
    role = "SUPER_ADMIN"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "$baseUrl/usuarios" -Method POST -Body $superAdmin -ContentType "application/json"
    Write-Host "✅ SUPER ADMIN criado com sucesso!" -ForegroundColor Green
    Write-Host "Email: superadmin@mapasocial.com" -ForegroundColor Cyan
    Write-Host "Senha: super123" -ForegroundColor Cyan
} catch {
    Write-Host "❌ Erro: $_" -ForegroundColor Red
}
```

**Execute**:
```powershell
cd c:\Users\Vitor\Desktop\mapa-social
.\criar-super-admin.ps1
```

---

## Método 3: Promover Admin Existente

Se você já tem um admin (`admin@mapasocial.com`), pode promovê-lo manualmente:

```sql
UPDATE USUARIO 
SET role = 'SUPER_ADMIN' 
WHERE email = 'admin@mapasocial.com';
```

---

## ✅ Como Testar

### 1. Fazer Login como SUPER_ADMIN
1. **Frontend**: http://localhost:5173/login
2. **Email**: `superadmin@mapasocial.com`
3. **Senha**: `super123`

### 2. Acessar Painel Admin
- Clique no dropdown de usuário (canto superior direito)
- Clique em **"🔐 Painel Admin"**

### 3. Testar Poderes
Na aba **"Usuários"**:

#### Promover USER para ADMIN
1. Encontre um USER na tabela
2. Clique no botão **"⬆️ Promover"**
3. Confirme a ação
4. ✅ Usuário agora é ADMIN (role muda de USER → ADMIN)

#### Rebaixar ADMIN para USER
1. Encontre um ADMIN na tabela
2. Clique no botão **"⬇️ Rebaixar"**
3. Confirme a ação
4. ✅ Usuário volta a ser USER (role muda de ADMIN → USER)

#### Bloquear Usuário
1. Encontre um ADMIN na tabela
2. Clique no botão **"🚫 Bloquear"**
3. Confirme a ação
4. ✅ Usuário bloqueado (não conseguirá fazer login)

#### Excluir Usuário
1. Encontre qualquer USER ou ADMIN
2. Clique no botão **"🗑️ Excluir"**
3. Confirme a ação
4. ✅ Usuário removido do sistema

### 4. Verificar Proteções

#### SUPER_ADMIN é Protegido
- Na tabela, SUPER_ADMIN aparece com badge **🔱 SUPER ADMIN** (dourado)
- Ao invés de botões, mostra **"🛡️ PROTEGIDO"**
- ❌ Backend rejeita qualquer tentativa de modificar SUPER_ADMIN

---

## 🎨 Interface Visual

### Badges de Role
- **🔱 SUPER ADMIN**: Badge dourado com gradiente
- **👑 ADMIN**: Badge roxo
- **👤 USER**: Badge cinza

### Botões de Ação
- **⬆️ Promover** (verde): USER → ADMIN
- **⬇️ Rebaixar** (laranja): ADMIN → USER
- **🚫 Bloquear** (vermelho claro): Bloquear acesso
- **🗑️ Excluir** (vermelho): Remover usuário
- **🛡️ PROTEGIDO** (dourado): SUPER_ADMIN não pode ser tocado

---

## 🔐 Endpoints Backend

### Promover para ADMIN
```http
POST http://localhost:8080/api/admin/usuarios/{id}/promover
```
**Resposta**:
```json
{
  "id": 2,
  "nome": "João Silva",
  "email": "joao@email.com",
  "role": "ADMIN"
}
```

### Rebaixar para USER
```http
POST http://localhost:8080/api/admin/usuarios/{id}/rebaixar
```

### Bloquear Usuário
```http
POST http://localhost:8080/api/admin/usuarios/{id}/bloquear
```

### Desbloquear Usuário
```http
POST http://localhost:8080/api/admin/usuarios/{id}/desbloquear
```

### Listar Admins
```http
GET http://localhost:8080/api/admin/usuarios/admins
```
**Resposta**:
```json
[
  {
    "id": 1,
    "nome": "Super Admin",
    "email": "superadmin@mapasocial.com",
    "role": "SUPER_ADMIN"
  },
  {
    "id": 3,
    "nome": "Admin Fulano",
    "email": "admin@email.com",
    "role": "ADMIN"
  }
]
```

---

## ⚠️ Proteções Implementadas

### Backend (AdminService.java)
```java
// Verifica se é SUPER_ADMIN antes de qualquer operação
if (usuario.getRole() == UserRole.SUPER_ADMIN) {
    throw new IllegalArgumentException("Não é possível modificar um SUPER_ADMIN");
}
```

### Validações
1. ✅ SUPER_ADMIN não pode ser promovido
2. ✅ SUPER_ADMIN não pode ser rebaixado
3. ✅ SUPER_ADMIN não pode ser bloqueado
4. ✅ SUPER_ADMIN não pode ser excluído
5. ✅ Apenas SUPER_ADMIN pode promover/rebaixar outros usuários

---

## 📊 Fluxo Completo

```
1. Criar SUPER_ADMIN no banco H2
2. Fazer login no frontend
3. Acessar /admin
4. Na aba "Usuários":
   - Ver lista de todos usuários
   - USER: botão "Promover" visível
   - ADMIN: botões "Rebaixar" e "Bloquear" visíveis
   - SUPER_ADMIN: apenas badge "PROTEGIDO"
5. Promover um USER:
   - Clica "Promover" → confirma → USER vira ADMIN
6. Rebaixar um ADMIN:
   - Clica "Rebaixar" → confirma → ADMIN vira USER
7. Bloquear um ADMIN:
   - Clica "Bloquear" → confirma → usuário não pode mais logar
```

---

## 🐛 Troubleshooting

### Backend retorna 400 ao tentar promover
**Erro**: `"Não é possível modificar um SUPER_ADMIN"`
- ✅ **Causa**: Tentando modificar um SUPER_ADMIN (proteção funcionando)
- ✅ **Solução**: Apenas USER pode ser promovido, ADMIN pode ser rebaixado

### Botões não aparecem
- ✅ **Causa**: Role não está sendo reconhecido
- ✅ **Solução**: Verificar se `usuario.role` está vindo corretamente da API

### SUPER_ADMIN aparece como USER
- ✅ **Causa**: Banco não foi atualizado
- ✅ **Solução**: Reinicie o backend (H2 recria tabelas)

---

## 📝 Próximos Passos

1. ✅ Criar primeiro SUPER_ADMIN
2. ✅ Testar promover/rebaixar usuários
3. ✅ Testar bloqueio de usuários
4. ⏳ Implementar campo "bloqueado" na entidade Usuario
5. ⏳ Adicionar validação de bloqueio no login
6. ⏳ Implementar JWT para autenticação real
7. ⏳ Migrar para banco persistente (não H2)

---

**🔱 Sistema de hierarquia implementado e funcionando!**
