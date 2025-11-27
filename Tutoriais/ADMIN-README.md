# 🔐 Funcionalidades de Administrador - Mapa Social

## Implementação Concluída ✅

### 📋 O que foi criado:

1. **Enum `UserRole`** - Define os papéis USER e ADMIN
2. **Campo `role` no Usuario** - Identifica se é usuário comum ou administrador
3. **`AdminService`** - Serviço com lógica administrativa
4. **`AdminController`** - Endpoints protegidos para administradores
5. **`SugestaoServicoRepository`** - Repository para gerenciar sugestões
6. **`SecurityConfig` atualizado** - Proteção de rotas `/admin/**`

---

## 🚀 Endpoints Administrativos

### **Base URL:** `http://localhost:8080/admin`

⚠️ **Todos os endpoints requerem role=ADMIN**

---

### 📊 Dashboard

#### **GET** `/admin/dashboard/estatisticas`
Retorna estatísticas gerais do sistema.

**Resposta:**
```json
{
  "totalUsuarios": 150,
  "totalServicos": 45,
  "totalSugestoesPendentes": 8,
  "totalHistoricos": 320,
  "totalFavoritos": 98
}
```

---

### 💡 Gerenciar Sugestões

#### **GET** `/admin/sugestoes/pendentes`
Lista todas as sugestões com status PENDENTE.

**Resposta:**
```json
[
  {
    "id": 1,
    "nomeSugerido": "Centro de Acolhimento",
    "enderecoSugerido": "Rua A, 123",
    "descricaoSugerida": "Centro para pessoas em situação de rua",
    "status": "PENDENTE",
    "dataSugestao": "2025-11-18T10:30:00"
  }
]
```

#### **POST** `/admin/sugestoes/{id}/aprovar`
Aprova uma sugestão pendente.

**Resposta:**
```json
{
  "message": "Sugestão aprovada com sucesso",
  "sugestao": { ... }
}
```

#### **POST** `/admin/sugestoes/{id}/rejeitar`
Rejeita uma sugestão pendente.

**Resposta:**
```json
{
  "message": "Sugestão rejeitada",
  "sugestao": { ... }
}
```

---

### 👥 Gerenciar Usuários

#### **GET** `/admin/usuarios`
Lista todos os usuários cadastrados.

**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "email": "joao@email.com",
    "tipo": "COMUM",
    "role": "USER",
    "dataCadastro": "2025-11-10T14:20:00"
  }
]
```

#### **DELETE** `/admin/usuarios/{id}`
Exclui um usuário do sistema.

**Resposta:**
```json
{
  "message": "Usuário excluído com sucesso"
}
```

---

### 🏢 Gerenciar Serviços Sociais

#### **POST** `/admin/servicos`
Cria um novo serviço social.

**Body:**
```json
{
  "nome": "Casa de Apoio",
  "descricao": "Acolhimento para famílias",
  "categoria": { "id": 1 },
  "endereco": {
    "logradouro": "Rua B, 456",
    "cidade": "São Paulo",
    "estado": "SP",
    "cep": "01234-567"
  }
}
```

#### **PUT** `/admin/servicos/{id}`
Atualiza um serviço existente.

#### **DELETE** `/admin/servicos/{id}`
Exclui um serviço social.

---

### 🏷️ Gerenciar Categorias

#### **POST** `/admin/categorias`
Cria uma nova categoria.

**Body:**
```json
{
  "nome": "Saúde Mental",
  "descricao": "Serviços de apoio psicológico"
}
```

#### **PUT** `/admin/categorias/{id}`
Atualiza uma categoria existente.

#### **DELETE** `/admin/categorias/{id}`
Exclui uma categoria.

---

## 🔑 Como Criar o Primeiro Admin

### Opção 1: Via SQL (Recomendado)
Execute o script `criar-admin.sql` no seu banco MySQL:

```sql
-- Usuário: admin@mapasocial.com
-- Senha: admin123
```

### Opção 2: Via API + SQL
1. Cadastre um usuário normalmente via `/usuarios/cadastro`
2. Execute no banco:
```sql
UPDATE usuario SET role = 'ADMIN' WHERE email = 'seu@email.com';
```

---

## 🛡️ Segurança

- Rotas `/admin/**` protegidas por Spring Security
- Apenas usuários com `role = ADMIN` podem acessar
- Senhas criptografadas com BCrypt

---

## 📝 Próximos Passos (Fase 2)

### Backend:
- [ ] Adicionar campo `ativo` em ServicoSocial
- [ ] Endpoint para ativar/desativar serviços
- [ ] Endpoint para serviços mais acessados
- [ ] Endpoint para serviços mais favoritados

### Frontend:
- [ ] Página `/admin` com dashboard
- [ ] Lista de sugestões pendentes com botões aprovar/rejeitar
- [ ] Gerenciador de usuários
- [ ] Gerenciador de categorias e serviços
- [ ] Gráficos de estatísticas

---

## 🧪 Testando

```bash
# Inicie o backend
cd backend
./mvnw spring-boot:run

# Teste o endpoint de estatísticas (após criar admin)
curl http://localhost:8080/admin/dashboard/estatisticas
```

---

**Criado em:** 18/11/2025  
**Versão do Spring Boot:** 3.5.7  
**Java:** 17
