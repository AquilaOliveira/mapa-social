import { useState } from "react";
import { Header } from "../components/layout/Header";
import { Footer } from "../components/layout/Footer";
import { Link, useNavigate } from "react-router-dom";
import { usuarioAPI } from "../services/api";
import "./Cadastro.css";

export function Cadastro() {
  const [formData, setFormData] = useState({
    nome: "",
    email: "",
    senhaHash: "",
    confirmPassword: "",
    cpf: "",
    telefone: "",
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    // Validações
    if (formData.senhaHash !== formData.confirmPassword) {
      setError("As senhas não coincidem!");
      setLoading(false);
      return;
    }

    if (formData.senhaHash.length < 6) {
      setError("A senha deve ter pelo menos 6 caracteres!");
      setLoading(false);
      return;
    }

    try {
      // Prepara dados para enviar ao backend
      const usuario = {
        nome: formData.nome,
        email: formData.email,
        senhaHash: formData.senhaHash, // O backend vai criptografar
        cpf: formData.cpf,
        telefone: formData.telefone,
        tipo: "COMUM", // Define tipo padrão como COMUM
      };

      // Chama a API de cadastro
      await usuarioAPI.cadastrar(usuario);
      
      // Cadastro bem-sucedido
      setSuccess(true);
      
      // Redireciona para login após 2 segundos
      setTimeout(() => {
        navigate("/login");
      }, 2000);
      
    } catch (err) {
      setError(err.message || "Erro ao cadastrar. Tente novamente.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="cadastro-page">
      <Header />

      <main className="cadastro-main">
                <div className="cadastro-wrapper">
          <h2>Criar Conta</h2>
            <p>Preencha os campos abaixo para se cadastrar</p>

          <div className="cadastro-form-container">
            {success ? (
              <div className="success-message">
                ✅ Cadastro realizado com sucesso! Redirecionando para o login...
              </div>
            ) : (
              <form onSubmit={handleSubmit} className="cadastro-form">
                {error && (
                  <div className="error-message">
                    {error}
                  </div>
                )}

                <div>
                  <label>Nome Completo</label>
                  <input
                    type="text"
                    name="nome"
                    placeholder="Ana Paula Silva"
                    value={formData.nome}
                    onChange={handleChange}
                    required
                    disabled={loading}
                  />
                </div>

                <div>
                  <label>E-mail</label>
                  <input
                    type="email"
                    name="email"
                    placeholder="anapaulaa@gmail.com"
                    value={formData.email}
                    onChange={handleChange}
                    required
                    disabled={loading}
                  />
                </div>

                <div>
                  <label>CPF</label>
                  <input
                    type="text"
                    name="cpf"
                    placeholder="000.000.000-00"
                    value={formData.cpf}
                    onChange={handleChange}
                    required
                    disabled={loading}
                  />
                </div>

                <div>
                  <label>Telefone</label>
                  <input
                    type="tel"
                    name="telefone"
                    placeholder="(00) 00000-0000"
                    value={formData.telefone}
                    onChange={handleChange}
                    required
                    disabled={loading}
                  />
                </div>

                <div>
                  <label>Senha</label>
                  <input
                    type="password"
                    name="senhaHash"
                    placeholder="Mínimo 6 caracteres"
                    value={formData.senhaHash}
                    onChange={handleChange}
                    required
                    disabled={loading}
                    minLength={6}
                  />
                </div>

                <div>
                  <label>Confirmar Senha</label>
                  <input
                    type="password"
                    name="confirmPassword"
                    placeholder="Digite a senha novamente"
                    value={formData.confirmPassword}
                    onChange={handleChange}
                    required
                    disabled={loading}
                    minLength={6}
                  />
                </div>

                <button type="submit" className="submit-button" disabled={loading}>
                  {loading ? "Cadastrando..." : "Criar Conta"}
                </button>
              </form>
            )}

            <div className="login-redirect">
              <span>Já tem uma conta?</span>
              <Link to="/login" className="login-link">
                Fazer Login
              </Link>
            </div>
          </div>
        </div>
      </main>

      <Footer />
    </div>
  );
}
