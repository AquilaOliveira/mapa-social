import { useEffect } from "react";
import { MapContainer, TileLayer, Marker, Popup, useMap } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import "./OpenStreetMap.css";

// Fix leaflet icons
delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl: "https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon-2x.png",
  iconUrl: "https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon.png",
  shadowUrl: "https://unpkg.com/leaflet@1.9.4/dist/images/marker-shadow.png",
});

function MapBounds({ servicos }) {
  const map = useMap();
  useEffect(() => {
    if (!servicos || servicos.length === 0) return;
    const bounds = servicos
      .filter((s) => s.endereco?.latitude && s.endereco?.longitude)
      .map((s) => [parseFloat(s.endereco.latitude), parseFloat(s.endereco.longitude)]);
    if (bounds.length > 0) map.fitBounds(bounds, { padding: [50, 50] });
  }, [servicos, map]);
  return null;
}

export function OpenStreetMap({ servicos = [], selectedCategory = null }) {
  const BRAGANCA_CENTER = [-22.9519, -46.5428];
  const servicosFiltrados = selectedCategory
    ? servicos.filter((s) => s.categoria?.nome?.toLowerCase() === selectedCategory.toLowerCase())
    : servicos;
  const servicosComCoordenadas = servicosFiltrados.filter((s) => s.endereco?.latitude && s.endereco?.longitude);

  return (
    <div className="openstreetmap-container">
      <MapContainer center={BRAGANCA_CENTER} zoom={13} className="leaflet-map" scrollWheelZoom={true}>
        <TileLayer
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        {servicosComCoordenadas.map((servico) => (
          <Marker
            key={servico.id}
            position={[parseFloat(servico.endereco.latitude), parseFloat(servico.endereco.longitude)]}
          >
            <Popup maxWidth={300}>
              <div className="popup-content">
                <h3>{servico.nome}</h3>
                <p>
                  <strong>Categoria:</strong> {servico.categoria?.nome || "N/A"}
                </p>
                {servico.telefone && (
                  <p>
                    <strong>Telefone:</strong> {servico.telefone}
                  </p>
                )}
                {servico.descricao && (
                  <p>
                    <strong>Descrição:</strong> {servico.descricao}
                  </p>
                )}
                <p>
                  <strong>Endereço:</strong> {servico.endereco.rua}, {servico.endereco.numero} - {servico.endereco.bairro}
                </p>
              </div>
            </Popup>
          </Marker>
        ))}
        <MapBounds servicos={servicosComCoordenadas} />
      </MapContainer>
      <div className="map-info">📍 {servicosComCoordenadas.length} serviço(s) no mapa</div>
    </div>
  );
}
