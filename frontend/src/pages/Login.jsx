import { useState } from "react";
import { Header } from "../components/layout/Header";
import { Footer } from "../components/layout/Footer";
import { Link, useNavigate } from "react-router-dom";
import { usuarioAPI } from "../services/api";
import "./Login.css";

export function Login({ onLogin }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    try {
      // Chama a API do backend
      const response = await usuarioAPI.login(email, password);
      
      // Se login bem-sucedido
      if (response.message === "Login bem-sucedido!" && response.usuario) {
        // Salva os dados do usuário no localStorage
        localStorage.setItem("userId", response.usuario.id);
        localStorage.setItem("userName", response.usuario.nome);
        localStorage.setItem("userEmail", response.usuario.email);
        localStorage.setItem("userTipo", response.usuario.tipo);
        
        // Chama callback do componente pai
        if (onLogin) {
          onLogin(response.usuario.email);
        }
        
        // Redireciona para página inicial
        navigate("/");
      }
    } catch (err) {
      // Trata erros de autenticação
      setError(err.message || "Credenciais inválidas. Tente novamente.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-page">
      <Header />

      <main className="login-main">
        <div className="login-wrapper">
          <h2>Faça Login</h2>

          <div className="login-form-container">
            <form onSubmit={handleSubmit} className="login-form">
              {error && (
                <div className="error-message" style={{ 
                  color: 'red', 
                  padding: '10px', 
                  marginBottom: '15px', 
                  backgroundColor: '#ffebee',
                  borderRadius: '4px',
                  textAlign: 'center'
                }}>
                  {error}
                </div>
              )}

              <div>
                <label>E-mail</label>
                <input
                  type="email"
                  placeholder="anapaulaa@gmail.com"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                  disabled={loading}
                />
              </div>

              <div>
                <label>Senha</label>
                <input
                  type="password"
                  placeholder="******"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                  disabled={loading}
                />
              </div>

              <div className="forgot-password">
                <Link to="#" className="forgot-link">
                  Esqueceu a senha?
                </Link>
              </div>

              <button type="submit" className="submit-button" disabled={loading}>
                {loading ? "Entrando..." : "Entrar"}
              </button>
            </form>

            <div className="divider">
              <span>Ou</span>
            </div>

            <div className="social-buttons">
              <button className="social-button google">
                <span>G</span> Faça login com Google
              </button>
              <button className="social-button x">
                <span>𝕏</span> Faça login com X
              </button>
            </div>

            <div className="create-account">
              <span>Não tem uma conta? </span>
              <Link to="/criar-conta" className="create-account-button">
                Criar Conta
              </Link>
            </div>
          </div>
        </div>
      </main>

      <Footer />
    </div>
  );
}
