########################################
#   SCRIPT PARA INICIAR FRONTEND      #
#     MAPA SOCIAL - FRONTEND           #
########################################

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "   INICIANDO FRONTEND - MAPA SOCIAL" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Navega para a pasta frontend
Set-Location "$PSScriptRoot\frontend"

Write-Host "[1/2] Verificando se a porta 5173 esta livre..." -ForegroundColor Yellow
$frontendPort = Get-NetTCPConnection -LocalPort 5173 -ErrorAction SilentlyContinue

if ($frontendPort) {
    Write-Host "Porta 5173 ja esta em uso!" -ForegroundColor Red
    Write-Host "Frontend provavelmente ja esta rodando em http://localhost:5173" -ForegroundColor Yellow
    exit 1
}

Write-Host "[2/2] Iniciando Vite Dev Server..." -ForegroundColor Yellow
Write-Host ""
Write-Host "Frontend estara disponivel em: http://localhost:5173" -ForegroundColor Green
Write-Host "Pressione Ctrl+C para parar o servidor" -ForegroundColor Yellow
Write-Host ""

# Inicia o servidor de desenvolvimento
npm run dev
