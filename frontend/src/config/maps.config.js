// ======================================
// CONFIGURAÇÃO DO GOOGLE MAPS
// ======================================
// 
// Para usar o mapa interativo, você precisa de uma API Key do Google Maps.
// 
// PASSOS PARA OBTER A API KEY:
// 
// 1. Acesse: https://console.cloud.google.com/
// 2. Crie um novo projeto ou selecione um existente
// 3. Vá em "APIs & Services" > "Library"
// 4. Procure e ative: "Maps JavaScript API" e "Places API"
// 5. Vá em "APIs & Services" > "Credentials"
// 6. Clique em "Create Credentials" > "API Key"
// 7. Copie a API Key gerada
// 8. Cole abaixo substituindo "YOUR_API_KEY"
//
// IMPORTANTE: 
// - Restrinja a API Key para seu domínio em produção
// - Para desenvolvimento local, pode usar sem restrições
// 
// ======================================

export const GOOGLE_MAPS_API_KEY = "YOUR_API_KEY";

// Caso não queira usar o Google Maps, você pode usar alternativas gratuitas:
// - OpenStreetMap com Leaflet
// - Mapbox (tem plano gratuito)
