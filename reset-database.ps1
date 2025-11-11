########################################
#   SCRIPT PARA RESETAR BANCO DE DADOS #
#     MAPA SOCIAL - DATABASE RESET      #
########################################

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "   RESETAR BANCO DE DADOS" -ForegroundColor Red
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "⚠️  ATENÇÃO: Este script vai APAGAR todos os dados!" -ForegroundColor Red
Write-Host ""

# Tentar encontrar o MySQL
$mysqlPaths = @(
    "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe",
    "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe",
    "C:\MySQL\bin\mysql.exe",
    "mysql"
)

$mysqlPath = $null
foreach ($path in $mysqlPaths) {
    if ($path -eq "mysql") {
        try {
            $result = Get-Command mysql -ErrorAction SilentlyContinue
            if ($result) {
                $mysqlPath = "mysql"
                break
            }
        }
        catch {
            continue
        }
    }
    elseif (Test-Path $path) {
        $mysqlPath = $path
        break
    }
}

if (-Not $mysqlPath) {
    Write-Host "❌ ERRO: MySQL não encontrado!" -ForegroundColor Red
    exit 1
}

Write-Host "✅ MySQL encontrado em: $mysqlPath" -ForegroundColor Green
Write-Host ""

# Solicitar credenciais
Write-Host "Digite as credenciais do MySQL:" -ForegroundColor Yellow
$username = Read-Host "Usuario (default: root)"
if ([string]::IsNullOrWhiteSpace($username)) {
    $username = "root"
}

Write-Host ""
$password = Read-Host "Senha" -AsSecureString
$passwordPlain = [Runtime.InteropServices.Marshal]::PtrToStringAuto([Runtime.InteropServices.Marshal]::SecureStringToBSTR($password))

Write-Host ""
Write-Host "🗑️  Limpando tabelas..." -ForegroundColor Yellow
Write-Host ""

# SQL para limpar as tabelas
$cleanSQL = @"
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE historico;
TRUNCATE TABLE favorito;
TRUNCATE TABLE servico_social;
TRUNCATE TABLE endereco;
TRUNCATE TABLE categoria;
TRUNCATE TABLE usuario;
TRUNCATE TABLE sugestao_servico;
SET FOREIGN_KEY_CHECKS = 1;
"@

try {
    if ($mysqlPath -eq "mysql") {
        $cleanSQL | & mysql -u $username --password=$passwordPlain mapa_social_db 2>&1
    }
    else {
        $cleanSQL | & $mysqlPath -u $username --password=$passwordPlain mapa_social_db 2>&1
    }
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host ""
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host "   ✅ BANCO LIMPO COM SUCESSO!" -ForegroundColor Green
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host ""
        Write-Host "Agora você pode executar:" -ForegroundColor Yellow
        Write-Host ".\populate-database.ps1" -ForegroundColor Green
        Write-Host ""
    }
    else {
        Write-Host ""
        Write-Host "❌ ERRO ao limpar banco de dados!" -ForegroundColor Red
    }
}
catch {
    Write-Host ""
    Write-Host "❌ ERRO: $_" -ForegroundColor Red
}

Write-Host ""
Write-Host "Pressione qualquer tecla para sair..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
