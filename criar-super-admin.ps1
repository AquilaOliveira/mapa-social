$baseUrl = "http://localhost:8080/api/admin"

$superAdmin = @{
    nome = "Super Admin"
    email = "superadmin@mapasocial.com"
    senha = "super123"
    tipo = "SUPERUSUARIO"
    role = "SUPER_ADMIN"
} | ConvertTo-Json

Write-Host "🔱 Criando SUPER ADMIN..." -ForegroundColor Cyan
Write-Host ""

try {
    $response = Invoke-RestMethod -Uri "$baseUrl/usuarios" -Method POST -Body $superAdmin -ContentType "application/json"
    Write-Host "✅ SUPER ADMIN criado com sucesso!" -ForegroundColor Green
    Write-Host ""
    Write-Host "Credenciais de acesso:" -ForegroundColor Yellow
    Write-Host "Email: superadmin@mapasocial.com" -ForegroundColor Cyan
    Write-Host "Senha: super123" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "Acesse: http://localhost:5173/login" -ForegroundColor Magenta
} catch {
    Write-Host "❌ Erro ao criar SUPER ADMIN: $_" -ForegroundColor Red
    Write-Host ""
    Write-Host "⚠️ Use o Console H2 como alternativa:" -ForegroundColor Yellow
    Write-Host "1. Acesse: http://localhost:8080/h2-console" -ForegroundColor Cyan
    Write-Host "2. JDBC URL: jdbc:h2:mem:mapasocialdb" -ForegroundColor Cyan
    Write-Host "3. User: sa (senha em branco)" -ForegroundColor Cyan
    Write-Host "4. Execute o SQL que está em CRIAR-SUPER-ADMIN.md" -ForegroundColor Cyan
}
