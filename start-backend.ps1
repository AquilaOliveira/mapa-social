# ================================================
# Script PowerShell para iniciar o Backend
# ================================================

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "   INICIANDO BACKEND - MAPA SOCIAL" -ForegroundColor Cyan  
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Navegar para a pasta demo
Set-Location -Path "$PSScriptRoot\demo"

Write-Host "[1/2] Verificando se a porta 8080 esta livre..." -ForegroundColor Yellow

$port8080 = Get-NetTCPConnection -LocalPort 8080 -ErrorAction SilentlyContinue
if ($port8080) {
    Write-Host "ERRO: A porta 8080 ja esta em uso!" -ForegroundColor Red
    Write-Host "PID usando a porta: $($port8080.OwningProcess)" -ForegroundColor Yellow
    Write-Host "Execute: Stop-Process -Id $($port8080.OwningProcess) -Force" -ForegroundColor Yellow
    Read-Host "Pressione Enter para sair"
    exit 1
}

Write-Host "[2/2] Iniciando Spring Boot..." -ForegroundColor Green
Write-Host ""
Write-Host "Backend estara disponivel em: http://localhost:8080" -ForegroundColor Cyan
Write-Host "Pressione Ctrl+C para parar o servidor" -ForegroundColor Yellow
Write-Host ""

# Iniciar o backend mantendo janela aberta para evitar finalizacao do lote
Write-Host "Iniciando processo persistente do Spring Boot..." -ForegroundColor Green
Start-Process cmd -ArgumentList '/K', 'mvnw.cmd spring-boot:run' -WorkingDirectory "$PSScriptRoot\demo"
Write-Host "Janela separada iniciada. Feche-a ou use Ctrl+C nela para parar o backend." -ForegroundColor Yellow
