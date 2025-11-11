# 🎯 PRÓXIMOS PASSOS - MAPA SOCIAL

## 📋 Checklist

- [ ] Iniciar Backend
- [ ] Iniciar Frontend
- [ ] Popular Banco de Dados
- [ ] Configurar Google Maps API Key
- [ ] Testar Aplicação

---

## 1️⃣ INICIAR O BACKEND

### Abra um terminal PowerShell (Terminal 1):

```powershell
cd C:\Users\Vitor\Desktop\mapa-social
.\start-backend.ps1
```

✅ **Aguarde até ver a mensagem:**
```
Started DemoApplication in X.XXX seconds
```

⚠️ **IMPORTANTE:** Deixe este terminal aberto e NÃO execute mais nada nele!

---

## 2️⃣ INICIAR O FRONTEND

### Abra um SEGUNDO terminal PowerShell (Terminal 2):

```powershell
cd C:\Users\Vitor\Desktop\mapa-social
.\start-frontend.ps1
```

✅ **Aguarde até ver a mensagem:**
```
Local:   http://localhost:5173/
```

⚠️ **IMPORTANTE:** Deixe este terminal também aberto!

---

## 3️⃣ POPULAR O BANCO DE DADOS

### Abra um TERCEIRO terminal PowerShell (Terminal 3):

```powershell
cd C:\Users\Vitor\Desktop\mapa-social

# Carregar o SQL no MySQL
mysql -u root -p mapa_social_db < demo\src\main\resources\data\servicos-braganca-paulista.sql
```

💡 **Dica:** Se não tiver senha no MySQL, apenas pressione Enter quando pedir a senha.

### ⚠️ Se o comando `mysql` não for reconhecido:

Você precisa adicionar o MySQL ao PATH ou usar o caminho completo:

```powershell
# Exemplo com caminho completo (ajuste conforme sua instalação):
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe" -u root -p mapa_social_db < demo\src\main\resources\data\servicos-braganca-paulista.sql
```

### ✅ Verificar se funcionou:

No Terminal 3, execute:
```powershell
mysql -u root -p mapa_social_db -e "SELECT COUNT(*) as total FROM servico_social;"
```

Deve retornar o número de serviços cadastrados (provavelmente mais de 50).

---

## 4️⃣ CONFIGURAR GOOGLE MAPS API KEY

### Obter a Chave da API:

1. **Acesse:** https://console.cloud.google.com/
2. **Crie um novo projeto** ou selecione um existente
3. **Ative a API:**
   - Menu → APIs & Services → Library
   - Procure por "Maps JavaScript API"
   - Clique em "Enable"
4. **Crie uma chave:**
   - APIs & Services → Credentials
   - Create Credentials → API Key
   - Copie a chave gerada

### Configurar no Projeto:

Edite o arquivo: `frontend/src/config/maps.config.js`

```javascript
// Substitua YOUR_API_KEY pela sua chave real
export const GOOGLE_MAPS_API_KEY = "AIzaSyDxxxxxxxxxxxxxxxxxxxxxxxx";
```

💡 **Dica:** Por segurança, você pode restringir a chave para aceitar apenas requisições de `localhost:5173` durante o desenvolvimento.

---

## 5️⃣ TESTAR A APLICAÇÃO

### 1. Abra seu navegador em:
```
http://localhost:5173
```

### 2. Verifique:
- ✅ A página carrega sem erros
- ✅ Você vê cards de serviços sociais
- ✅ O contador de serviços por categoria está funcionando
- ✅ O mapa do Google Maps aparece (após configurar a API Key)
- ✅ Os marcadores dos serviços aparecem no mapa

### 3. Teste as funcionalidades:
- 🔍 **Pesquisa:** Digite um termo no campo de busca
- 🗂️ **Filtros:** Clique nas categorias para filtrar
- 🗺️ **Mapa:** Clique nos marcadores para ver detalhes
- 📍 **Localização:** Verifique se os serviços estão nas posições corretas

---

## 🐛 SOLUÇÃO DE PROBLEMAS

### ❌ Backend não inicia (porta 8080 ocupada):

```powershell
# Encontrar o processo:
Get-NetTCPConnection -LocalPort 8080 | Select-Object OwningProcess

# Parar o processo (substitua XXXX pelo número do processo):
Stop-Process -Id XXXX -Force
```

### ❌ Frontend não inicia (porta 5173 ocupada):

```powershell
# Encontrar o processo:
Get-NetTCPConnection -LocalPort 5173 | Select-Object OwningProcess

# Parar o processo:
Stop-Process -Id XXXX -Force
```

### ❌ Erro de conexão com o banco:

Verifique se o MySQL está rodando:
```powershell
Get-Service MySQL* | Select-Object Name, Status
```

Se não estiver rodando:
```powershell
Start-Service MySQL84  # Ajuste o nome conforme sua instalação
```

### ❌ Erro CORS (Cross-Origin):

Se ver erros de CORS no console do navegador, verifique se o backend tem a anotação `@CrossOrigin` nos Controllers.

---

## 📊 ESTRUTURA DOS DADOS

O arquivo SQL contém serviços sociais de Bragança Paulista com:
- **Categorias:** Saúde, Assistência Social, Educação, Habitação, etc.
- **Dados:** Nome, descrição, endereço, telefone, coordenadas GPS
- **Localização:** Latitude e longitude para exibição no mapa

---

## 🎨 PRÓXIMAS MELHORIAS (Opcional)

1. **Autenticação de Usuários:**
   - Sistema de login/registro
   - Favoritar serviços
   - Histórico de buscas

2. **Funcionalidades do Mapa:**
   - Rota até o serviço
   - Filtro por distância
   - Agrupamento de marcadores próximos

3. **Melhorias na Busca:**
   - Autocompletar
   - Busca por proximidade
   - Filtros avançados

4. **Sistema de Avaliações:**
   - Usuários podem avaliar serviços
   - Comentários e feedbacks

---

## 📞 SUPORTE

Se encontrar problemas:
1. Verifique os logs no terminal do backend
2. Abra o console do navegador (F12) para ver erros do frontend
3. Consulte o arquivo `COMO-RODAR.md` para mais detalhes

---

## ✅ FINALIZAÇÃO

Quando terminar de usar a aplicação:
1. Pressione `Ctrl+C` no Terminal 1 (Backend)
2. Pressione `Ctrl+C` no Terminal 2 (Frontend)
3. Feche os terminais

**Boa sorte com seu projeto! 🚀**
