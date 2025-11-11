import { useState } from "react";
import { Header } from "../components/layout/Header";
import { Footer } from "../components/layout/Footer";
import { AcessoCard } from "../components/cards/AcessoCard";
import "./Acesso.css"; 

export function Acesso({ onLogin, userName = "Usuário" }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (email && password) {
      onLogin(email);
    }
  };

  return (
    <div className="acesso-page">
      <Header />

      <main className="acesso-main">
        <div className="acesso-wrapper">
          <h2 className="hero-title">
            Olá {userName} <span className="highlight-red">Seja Bem-vindo(a)</span>
          </h2>

          <section className="acesso-section">
            <h3 className="services-title">O que deseja fazer?</h3>

            <div className="acesso-grid">
                <AcessoCard title="Acessar o Mapa Social" linkTo="/" />
                <AcessoCard title="Serviços Favoritos" linkTo="#" />
                <AcessoCard title="Acompanhar Sugestões" linkTo="/sugestao" />
                <AcessoCard title="Visualizar Notícias" linkTo="#" />
            </div>
          </section>
        </div>
      </main>

      <Footer />
    </div>
  );
}
