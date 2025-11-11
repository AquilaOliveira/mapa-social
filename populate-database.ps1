########################################
#   SCRIPT PARA POPULAR BANCO DE DADOS #
#     MAPA SOCIAL - DATABASE            #
########################################

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "   POPULAR BANCO DE DADOS" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Caminho do arquivo SQL
$sqlFile = "$PSScriptRoot\demo\src\main\resources\data\servicos-braganca-paulista.sql"

# Verificar se o arquivo SQL existe
if (-Not (Test-Path $sqlFile)) {
    Write-Host "❌ ERRO: Arquivo SQL não encontrado!" -ForegroundColor Red
    Write-Host "Procurando em: $sqlFile" -ForegroundColor Yellow
    exit 1
}

Write-Host "✅ Arquivo SQL encontrado!" -ForegroundColor Green
Write-Host ""

# Tentar encontrar o MySQL
$mysqlPaths = @(
    "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe",
    "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe",
    "C:\MySQL\bin\mysql.exe",
    "mysql"  # Se estiver no PATH
)

$mysqlPath = $null
foreach ($path in $mysqlPaths) {
    if ($path -eq "mysql") {
        # Testar se mysql está no PATH
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
    Write-Host ""
    Write-Host "Por favor, execute manualmente:" -ForegroundColor Yellow
    Write-Host 'mysql -u root -p mapa_social_db < demo\src\main\resources\data\servicos-braganca-paulista.sql' -ForegroundColor White
    Write-Host ""
    Write-Host "Ou informe o caminho do MySQL:" -ForegroundColor Yellow
    Write-Host '& "C:\Caminho\Para\MySQL\bin\mysql.exe" -u root -p mapa_social_db < demo\src\main\resources\data\servicos-braganca-paulista.sql' -ForegroundColor White
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
Write-Host "⚠️  A senha será digitada de forma oculta" -ForegroundColor Yellow
$password = Read-Host "Senha" -AsSecureString
$passwordPlain = [Runtime.InteropServices.Marshal]::PtrToStringAuto([Runtime.InteropServices.Marshal]::SecureStringToBSTR($password))

Write-Host ""
Write-Host "🔄 Carregando dados no banco..." -ForegroundColor Yellow
Write-Host ""

# Executar o comando
try {
    if ($mysqlPath -eq "mysql") {
        Get-Content $sqlFile | & mysql -u $username --password=$passwordPlain mapa_social_db 2>&1
    }
    else {
        Get-Content $sqlFile | & $mysqlPath -u $username --password=$passwordPlain mapa_social_db 2>&1
    }
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host ""
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host "   ✅ SUCESSO!" -ForegroundColor Green
        Write-Host "========================================" -ForegroundColor Cyan
        Write-Host ""
        Write-Host "Banco de dados populado com sucesso!" -ForegroundColor Green
        Write-Host ""
        
        # Verificar quantos registros foram inseridos
        Write-Host "Verificando dados inseridos..." -ForegroundColor Yellow
        if ($mysqlPath -eq "mysql") {
            & mysql -u $username --password=$passwordPlain mapa_social_db -e "SELECT COUNT(*) as 'Total de Servicos' FROM servico_social;" 2>&1
        }
        else {
            & $mysqlPath -u $username --password=$passwordPlain mapa_social_db -e "SELECT COUNT(*) as 'Total de Servicos' FROM servico_social;" 2>&1
        }
    }
    else {
        Write-Host ""
        Write-Host "❌ ERRO ao popular banco de dados!" -ForegroundColor Red
        Write-Host "Verifique as credenciais e tente novamente." -ForegroundColor Yellow
    }
}
catch {
    Write-Host ""
    Write-Host "❌ ERRO: $_" -ForegroundColor Red
}

Write-Host ""
Write-Host "Pressione qualquer tecla para sair..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
