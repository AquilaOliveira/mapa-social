# 🔧 SOLUÇÃO FINAL - Login Funcionando

## ✅ Problema Resolvido

O hash BCrypt no `data.sql` estava incorreto. Foi criado um `DataInitializer.java` que gera os hashes corretamente usando o `PasswordEncoder` do Spring.

---

## 🔑 Credenciais Atualizadas

**Todos os usuários agora usam a senha**: `admin123`

### Usuários Disponíveis:

1. **SUPER_ADMIN**
   - Email: `superadmin@mapasocial.com`
   - Senha: `admin123`
   - Role: SUPER_ADMIN

2. **ADMIN**
   - Email: `maria@admin.com`
   - Senha: `admin123`
   - Role: ADMIN

3. **USER**
   - Email: `joao@user.com`
   - Senha: `admin123`
   - Role: USER

4. **USER Bloqueado**
   - Email: `pedro@user.com`
   - Senha: `admin123`
   - Role: USER (bloqueado = true)

---

## 🚀 Como Testar

### 1. Certificar que backend está rodando

Abra um PowerShell e execute:
```powershell
cd c:\Users\Vitor\Desktop\mapa-social\backend
.\mvnw.cmd spring-boot:run
```

Aguarde até ver:
```
Started DemoApplication in X.XXX seconds
✅ Usuários iniciais criados com sucesso!
📧 Emails: superadmin@mapasocial.com, maria@admin.com, joao@user.com, pedro@user.com
🔑 Senha para todos: admin123
```

### 2. Acessar frontend

http://localhost:5173/login

### 3. Fazer login

- **Email**: `superadmin@mapasocial.com`
- **Senha**: `admin123`

### 4. Acessar painel admin

Após login bem-sucedido:
1. Clique no dropdown do usuário (canto superior direito)
2. Clique em "🔐 Painel Admin"
3. Teste as funcionalidades na aba "Usuários"

---

## 🔍 Verificar se Funcionou

### Teste via PowerShell:

```powershell
# Listar usuários
Invoke-RestMethod -Uri http://localhost:8080/usuarios

# Testar login
$body = @{ email = 'superadmin@mapasocial.com'; senhaHash = 'admin123' } | ConvertTo-Json
Invoke-RestMethod -Uri http://localhost:8080/usuarios/login -Method POST -Body $body -ContentType 'application/json'
```

**Resposta esperada do login:**
```json
{
  "message": "Login bem-sucedido!",
  "email": "superadmin@mapasocial.com"
}
```

---

## 📝 O que foi Alterado

### 1. Criado `DataInitializer.java`
- Localização: `backend/src/main/java/com/mapa/social/demo/config/DataInitializer.java`
- Função: Cria usuários iniciais usando `PasswordEncoder` do Spring
- Vantagem: Hash BCrypt gerado corretamente toda vez que o backend inicia

### 2. Removido `data.sql`
- Motivo: Hash estático não estava funcionando
- Novo método usa CommandLineRunner para inicializar dados

### 3. Adicionados logs de debug
- `UsuarioController.java`: Logs no endpoint `/login`
- `UsuarioService.java`: Logs no método `verificarSenha()`
- Ver logs no console do backend para debug

---

## ⚠️ Importante

### Backend H2 é em Memória
- Dados são perdidos ao reiniciar
- Usuários são recriados automaticamente pelo `DataInitializer`
- Sempre use senha: `admin123`

### Para Produção
- Migrar para banco persistente (MySQL/PostgreSQL)
- Implementar JWT authentication
- Remover logs de debug
- Alterar senhas padrão

---

## 🐛 Troubleshooting

### Login ainda retorna 401?

1. **Verificar console do backend**
   - Procure por "=== DEBUG LOGIN ==="
   - Veja se email e senha estão sendo recebidos
   - Verifique "=== VERIFICAR SENHA ==="
   - Cheque se "Matches:" retorna true ou false

2. **Testar hash diretamente**
   ```powershell
   Invoke-RestMethod -Uri http://localhost:8080/usuarios/test-bcrypt/admin123
   ```
   Deve retornar `matchesComBanco: true`

3. **Verificar usuários no banco**
   ```powershell
   $users = Invoke-RestMethod -Uri http://localhost:8080/usuarios
   $users | Select-Object id, nome, email, role, bloqueado | Format-Table
   ```

4. **Reiniciar backend limpo**
   ```powershell
   Get-Process java | Stop-Process -Force
   cd c:\Users\Vitor\Desktop\mapa-social\backend
   .\mvnw.cmd clean spring-boot:run
   ```

### Frontend não está enviando credenciais?

Verifique `Login.jsx` linha ~24:
```javascript
body: JSON.stringify({ email, senhaHash: password }),
```

O campo deve ser `senhaHash`, não `password`.

---

## ✅ Checklist Final

- [ ] Backend rodando em http://localhost:8080
- [ ] Frontend rodando em http://localhost:5173
- [ ] Mensagem "✅ Usuários iniciais criados com sucesso!" no console
- [ ] Listagem de usuários funciona: `GET /usuarios`
- [ ] Login com admin123 retorna 200 OK
- [ ] Acesso ao painel admin funciona
- [ ] Promover/rebaixar usuários funciona
- [ ] Bloquear/desbloquear usuários funciona

---

**🎉 Sistema totalmente funcional com login corrigido!**

Senha universal para todos os usuários: **admin123**
