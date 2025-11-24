# 🔧 SOLUÇÃO: Credenciais Inválidas no Login

## 🔍 Problema Identificado

O SUPER_ADMIN ainda não existe no banco de dados H2 (que é **em memória** e perde dados ao reiniciar).

---

## ✅ SOLUÇÃO RÁPIDA: Criar SUPER_ADMIN no H2

### Passo 1: Acesse o H2 Console
🔗 **http://localhost:8080/h2-console**

**Credenciais:**
- JDBC URL: `jdbc:h2:mem:mapasocialdb`
- User Name: `sa`
- Password: (deixar em branco)
- Clique em **"Connect"**

### Passo 2: Verificar se já existe usuário
```sql
SELECT * FROM USUARIO;
```

Se a tabela estiver vazia, execute:

### Passo 3: Criar SUPER_ADMIN
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

**📝 Nota**: O hash `$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy` corresponde à senha `super123`

### Passo 4: Verificar criação
```sql
SELECT id, nome, email, role, bloqueado FROM USUARIO;
```

Resultado esperado:
```
ID | NOME        | EMAIL                      | ROLE        | BLOQUEADO
1  | Super Admin | superadmin@mapasocial.com  | SUPER_ADMIN | FALSE
```

---

## 🔐 Agora Faça Login

🔗 **http://localhost:5173/login**

**Credenciais:**
- Email: `superadmin@mapasocial.com`
- Senha: `super123`

---

## 💡 Criar Usuários de Teste (Opcional)

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
  'Maria Santos',
  'maria@teste.com',
  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
  'ADMIN',
  'ADMIN',
  CURRENT_TIMESTAMP,
  false
);
```

**Todos com senha**: `super123`

---

## 🔄 IMPORTANTE: Banco H2 é em Memória

⚠️ **O banco H2 perde TODOS os dados quando você reinicia o backend!**

Toda vez que reiniciar o backend, você precisa:
1. Recriar o SUPER_ADMIN
2. Recriar outros usuários de teste

---

## 🐛 Troubleshooting

### Ainda diz "Credenciais inválidas"?

#### 1. Verificar se usuário existe
```sql
SELECT id, nome, email, senha_hash, role 
FROM USUARIO 
WHERE email = 'superadmin@mapasocial.com';
```

Se retornar vazio → usuário não foi criado, execute o INSERT novamente

#### 2. Verificar hash da senha
O hash deve começar com `$2a$10$...`

Se o hash estiver diferente → delete e recrie:
```sql
DELETE FROM USUARIO WHERE email = 'superadmin@mapasocial.com';

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

#### 3. Verificar logs do backend
No terminal onde o backend está rodando, procure por erros quando tentar fazer login.

#### 4. Testar com outro email
Crie um usuário simples para testar:
```sql
INSERT INTO USUARIO (nome, email, senha_hash, tipo, role, data_cadastro, bloqueado) 
VALUES (
  'Teste',
  'teste@teste.com',
  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
  'COMUM',
  'USER',
  CURRENT_TIMESTAMP,
  false
);
```

Tente login com:
- Email: `teste@teste.com`
- Senha: `super123`

Se funcionar → problema era que SUPER_ADMIN não existia

---

## 🎯 Verificação Final

Execute no H2:
```sql
SELECT 
  id,
  nome,
  email,
  tipo,
  role,
  bloqueado,
  data_cadastro,
  LEFT(senha_hash, 20) as senha_hash_inicio
FROM USUARIO
ORDER BY id;
```

Deve mostrar todos os usuários com:
- `senha_hash_inicio` começando com `$2a$10$N9qo8uLOickgx`
- `bloqueado` = `FALSE`
- `role` correto para cada usuário

---

## 📋 Checklist

- [ ] Abri H2 Console (http://localhost:8080/h2-console)
- [ ] Conectei com JDBC URL: `jdbc:h2:mem:mapasocialdb`
- [ ] Executei SELECT * FROM USUARIO para verificar
- [ ] Se vazio, executei INSERT do SUPER_ADMIN
- [ ] Verifiquei que usuário foi criado (SELECT novamente)
- [ ] Senha_hash começa com `$2a$10$`
- [ ] Tentei login em http://localhost:5173/login
- [ ] Email: superadmin@mapasocial.com
- [ ] Senha: super123
- [ ] ✅ Login funcionou!

---

## 🚀 Próximo Passo

Após login bem-sucedido:
1. Clique no dropdown do usuário (canto superior direito)
2. Clique em "🔐 Painel Admin"
3. Teste as funcionalidades na aba "Usuários"

---

**🔑 A senha `super123` corresponde ao hash BCrypt que está no banco!**
