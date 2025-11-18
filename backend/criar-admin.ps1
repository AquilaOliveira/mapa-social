# Script para criar usuário ADMIN automaticamente

Write-Host "=== CRIANDO USUÁRIO ADMINISTRADOR ===" -ForegroundColor Green

# Dados do admin
$adminData = @{
    nome = "Administrador"
    email = "admin@mapasocial.com"
    senhaHash = "admin123"
    tipo = "ADMIN"
} | ConvertTo-Json

Write-Host "Enviando requisição para http://localhost:8080/usuarios/cadastro..." -ForegroundColor Yellow

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/usuarios/cadastro" -Method POST -Body $adminData -ContentType "application/json"
    
    Write-Host "`n✅ ADMIN CRIADO COM SUCESSO!" -ForegroundColor Green
    Write-Host "ID: $($response.id)" -ForegroundColor Cyan
    Write-Host "Nome: $($response.nome)" -ForegroundColor Cyan
    Write-Host "Email: $($response.email)" -ForegroundColor Cyan
    Write-Host "Tipo: $($response.tipo)" -ForegroundColor Cyan
    
    Write-Host "`n📝 CREDENCIAIS DE LOGIN:" -ForegroundColor Magenta
    Write-Host "Email: admin@mapasocial.com" -ForegroundColor White
    Write-Host "Senha: admin123" -ForegroundColor White
    
    # Agora promover para ADMIN (role)
    Write-Host "`n🔐 Promovendo usuário para role ADMIN..." -ForegroundColor Yellow
    
    # Como H2 é em memória, precisa ser feito manualmente após login
    Write-Host "`n⚠️  IMPORTANTE: O campo 'role' já foi criado como USER." -ForegroundColor Yellow
    Write-Host "Para transformar em ADMIN, você precisa:" -ForegroundColor Yellow
    Write-Host "1. Acessar http://localhost:8080/h2-console" -ForegroundColor White
    Write-Host "2. JDBC URL: jdbc:h2:mem:mapasocialdb" -ForegroundColor White
    Write-Host "3. User: sa" -ForegroundColor White
    Write-Host "4. Password: (vazio)" -ForegroundColor White
    Write-Host "5. Executar SQL: UPDATE USUARIO SET ROLE = 'ADMIN' WHERE EMAIL = 'admin@mapasocial.com';" -ForegroundColor White
    
} catch {
    Write-Host "`n❌ ERRO ao criar admin:" -ForegroundColor Red
    Write-Host $_.Exception.Message -ForegroundColor Red
    
    if ($_.Exception.Message -like "*already exists*" -or $_.Exception.Message -like "*E-mail já cadastrado*") {
        Write-Host "`n✅ Usuário já existe! Use as credenciais:" -ForegroundColor Green
        Write-Host "Email: admin@mapasocial.com" -ForegroundColor White
        Write-Host "Senha: admin123" -ForegroundColor White
    }
}

Write-Host "`n======================================" -ForegroundColor Green
