import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { sugestaoAPI } from "../services/api";
import { Header } from "../components/layout/Header";
import { Footer } from "../components/layout/Footer";
import "./Sugestão.css";

export function Sugestao() {
  const [formData, setFormData] = useState({
    nomeSugerido: "",
    enderecoSugerido: "",
    descricaoSugerida: "",
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  function handleChange(e) {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  }

  async function handleSubmit(e) {
    e.preventDefault();
    setError("");
    setLoading(true);

    if (!formData.nomeSugerido.trim()) {
      setError("O nome do serviço é obrigatório!");
      setLoading(false);
      return;
    }

    try {
      const userId = localStorage.getItem("userId");
      if (!userId) {
        setError("Você precisa estar logado para sugerir um serviço!");
        setTimeout(() => navigate("/login"), 1500);
        setLoading(false);
        return;
      }

      const payload = {
        nomeSugerido: formData.nomeSugerido,
        enderecoSugerido: formData.enderecoSugerido,
        descricaoSugerida: formData.descricaoSugerida,
        usuario: { id: parseInt(userId) },
        status: "PENDENTE",
      };

      await sugestaoAPI.criar(payload);
      setSuccess(true);
      setFormData({ nomeSugerido: "", enderecoSugerido: "", descricaoSugerida: "" });
      setTimeout(() => navigate("/"), 2000);
    } catch (err) {
      setError(err.message || "Erro ao enviar sugestão. Tente novamente.");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="sugestao-page">
      <Header />
      <main className="sugestao-main">
        <div className="sugestao-wrapper">
        <h2>Sugerir Novo Serviço Social</h2>
        <p className="sugestao-description">
          Conhece algum serviço social gratuito que não está no mapa? Ajude a comunidade sugerindo novos locais!
        </p>
        <div className="sugestao-form-container">
          {success ? (
            <div className="success-message">✅ Sugestão enviada com sucesso! Redirecionando...</div>
          ) : (
            <form onSubmit={handleSubmit} className="sugestao-form">
              {error && <div className="error-message">{error}</div>}

              <div className="field-group">
                <label htmlFor="nomeSugerido">Nome do Serviço *</label>
                <input
                  id="nomeSugerido"
                  name="nomeSugerido"
                  type="text"
                  placeholder="Ex: Centro de Apoio Alimentar"
                  value={formData.nomeSugerido}
                  onChange={handleChange}
                  required
                  disabled={loading}
                />
              </div>

              <div className="field-group">
                <label htmlFor="enderecoSugerido">Endereço (opcional)</label>
                <input
                  id="enderecoSugerido"
                  name="enderecoSugerido"
                  type="text"
                  placeholder="Rua das Flores, 123"
                  value={formData.enderecoSugerido}
                  onChange={handleChange}
                  disabled={loading}
                />
              </div>

              <div className="field-group">
                <label htmlFor="descricaoSugerida">Descrição (opcional)</label>
                <textarea
                  id="descricaoSugerida"
                  name="descricaoSugerida"
                  rows={5}
                  placeholder="Detalhes: horário, público-alvo, documentos necessários..."
                  value={formData.descricaoSugerida}
                  onChange={handleChange}
                  disabled={loading}
                />
              </div>

              <div className="info-box">ℹ️ Sua sugestão será revisada antes de aparecer no mapa.</div>

              <button type="submit" disabled={loading} className="submit-button">
                {loading ? "Enviando..." : "Enviar Sugestão"}
              </button>
              <button type="button" className="cancel-button" disabled={loading} onClick={() => navigate("/")}>
                Cancelar
              </button>
            </form>
          )}
        </div>
      </div>
      </main>
      <Footer />
    </div>
  );
}
