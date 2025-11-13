import { useState, useEffect } from "react";
import { Header } from "../components/layout/Header";
import { Footer } from "../components/layout/Footer";
import { ServiceCard } from "../components/cards/ServiceCard";
// Importar imagens localmente em vez de usar caminhos absolutos /src/...
import saudeIcon from "../assets/images/saude-publica.png";
import educacaoIcon from "../assets/images/educacao-publica.png";
import lazerIcon from "../assets/images/lazer.png";
import alimentacaoIcon from "../assets/images/alimentacao.png";
import cursosIcon from "../assets/images/cursos-profissionalizantes.png";
import documentosIcon from "../assets/images/emissao-de-documentos.png";
import transporteIcon from "../assets/images/transporte-publico.png";
import moradiaIcon from "../assets/images/moradia.png";
import assistenciaIcon from "../assets/images/assistencia-social.png";
import { OpenStreetMap } from "../components/map/OpenStreetMap";
import { Link } from "react-router-dom";
import { servicoSocialAPI } from "../services/api";
import "./HomePage.css";

export function HomePage() {
  const [servicos, setServicos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");

  // Carregar serviços do backend
  useEffect(() => {
    async function fetchServicos() {
      try {
        setLoading(true);
        const data = await servicoSocialAPI.buscarTodos();
        setServicos(data || []);
      } catch (err) {
        console.error("Erro ao buscar serviços:", err);
        setError("Não foi possível carregar os serviços.");
      } finally {
        setLoading(false);
      }
    }

    fetchServicos();
  }, []);

  // Contar serviços por categoria
  const contarPorCategoria = (nomeCategoria) => {
    return servicos.filter(
      (s) => s.categoria?.nome?.toLowerCase() === nomeCategoria.toLowerCase()
    ).length;
  };

  // Filtrar serviços por busca
  const servicosFiltrados = searchTerm
    ? servicos.filter(
        (s) =>
          s.nome?.toLowerCase().includes(searchTerm.toLowerCase()) ||
          s.categoria?.nome?.toLowerCase().includes(searchTerm.toLowerCase())
      )
    : servicos;

  return (
    <div className="home-page">
      <Header currentPage="home" />

      <main className="home-main">
        {/* Hero Section */}
        <section className="hero-section">
          <div className="hero-content">
            <h2 className="hero-title">
              Bem-vindo ao <span className="highlight">Mapa Social</span>
            </h2>

            <div className="hero-subtitle">
              <p className="hero-subtitle-main">
                Encontre aqui os serviços sociais disponíveis para você!
              </p>
              <p className="hero-subtitle-secondary">
                Pensado para facilitar o acesso à saúde, educação, lazer e muito mais.
              </p>
            </div>

            {/* Search Bar */}
            <div className="search-bar-container">
              <div className="search-bar">
                <input
                  type="text"
                  placeholder="Pesquise e explore o mapa social ..."
                  className="search-input"
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                />
                <img
                  src="/src/assets/icons/lupa.png"
                  alt="Buscar"
                  className="search-icon"
                  style={{ cursor: "pointer" }}
                />
              </div>
            </div>

            {/* Status Messages */}
            {loading && (
              <div className="status-message loading">
                Carregando serviços sociais...
              </div>
            )}
            {error && <div className="status-message error">{error}</div>}

            {/* Interactive Map */}
            {!loading && !error && servicos.length > 0 && (
              <OpenStreetMap
                servicos={servicosFiltrados}
                selectedCategory={selectedCategory}
              />
            )}

            {/* Fallback quando não há serviços */}
            {!loading && !error && servicos.length === 0 && (
              <div className="map-placeholder">
                <div className="map-placeholder-content">
                  <h3>🗺️ Mapa Interativo de Bragança Paulista</h3>
                  <p style={{ marginTop: "16px", fontSize: "13px", color: "#666" }}>
                    Nenhum serviço cadastrado ainda.<br />
                    Para adicionar serviços, execute o script SQL em:<br />
                    <code>demo/src/main/resources/data/servicos-braganca-paulista.sql</code>
                  </p>
                </div>
              </div>
            )}

            {/* Serviços encontrados */}
            {!loading && searchTerm && (
              <div className="search-results">
                <p>
                  {servicosFiltrados.length} serviço(s) encontrado(s) para "{searchTerm}"
                </p>
              </div>
            )}
          </div>
        </section>

        {/* Services Section */}
        <section className="services-section">
          <h3 className="services-title">Serviços disponíveis:</h3>

          {!loading && (
            <div className="services-grid">
              <div onClick={() => setSelectedCategory("Saúde")}>
                <ServiceCard
                  iconSrc={saudeIcon}
                  title="Saúde"
                  subtitle="Pública"
                  count={contarPorCategoria("Saúde")}
                />
              </div>
              <div onClick={() => setSelectedCategory("Educação")}>
                <ServiceCard
                  iconSrc={educacaoIcon}
                  title="Educação"
                  subtitle="Pública"
                  count={contarPorCategoria("Educação")}
                />
              </div>
              <div onClick={() => setSelectedCategory("Lazer")}>
                <ServiceCard
                  iconSrc={lazerIcon}
                  title="Lazer"
                  count={contarPorCategoria("Lazer")}
                />
              </div>
              <div onClick={() => setSelectedCategory("Alimentação")}>
                <ServiceCard
                  iconSrc={alimentacaoIcon}
                  title="Alimentação"
                  count={contarPorCategoria("Alimentação")}
                />
              </div>
              <div onClick={() => setSelectedCategory("Cursos")}>
                <ServiceCard
                  iconSrc={cursosIcon}
                  title="Cursos"
                  subtitle="Profissionalizantes"
                  count={contarPorCategoria("Cursos")}
                />
              </div>
              <div onClick={() => setSelectedCategory("Documentos")}>
                <ServiceCard
                  iconSrc={documentosIcon}
                  title="Emissão de"
                  subtitle="Documentos Gratuitos"
                  count={contarPorCategoria("Documentos")}
                />
              </div>
              <div onClick={() => setSelectedCategory("Transporte")}>
                <ServiceCard
                  iconSrc={transporteIcon}
                  title="Transporte"
                  subtitle="Público"
                  count={contarPorCategoria("Transporte")}
                />
              </div>
              <div onClick={() => setSelectedCategory("Moradia")}>
                <ServiceCard
                  iconSrc={moradiaIcon}
                  title="Moradia"
                  count={contarPorCategoria("Moradia")}
                />
              </div>
              <div onClick={() => setSelectedCategory("Assistência Social")}>                <ServiceCard
                  iconSrc={assistenciaIcon}
                  title="Assistência"
                  subtitle="Social"
                  count={contarPorCategoria("Assistência Social")}
                />
              </div>
            </div>
          )}

          {selectedCategory && (
            <div className="category-filter-info">
              <p>
                Mostrando serviços de: <strong>{selectedCategory}</strong>
              </p>
              <button
                onClick={() => setSelectedCategory(null)}
                className="clear-category-btn"
              >
                Limpar filtro
              </button>
            </div>
          )}
        </section>

        {/* CTA Section */}
        <section className="cta-section">
          <p className="cta-text">
            Quer indicar um serviço? Clique aqui e faça sua sugestão!
          </p>
          <Link to="/sugestao" className="cta-button">
            Indicar
          </Link>
        </section>
      </main>

      <Footer />
    </div>
  );
}
