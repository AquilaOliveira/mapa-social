import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { HomePage } from "./pages/HomePage";
import { Sobre } from "./pages/Sobre";
import { Contato } from "./pages/Contato";
import { Login } from "./pages/Login";
import { Cadastro } from "./pages/Cadastro";
import { Sugestao } from "./pages/Sugestao";
import { Acesso } from "./pages/Acesso";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/sobre" element={<Sobre />} />
        <Route path="/contato" element={<Contato />} />
        <Route path="/login" element={<Login />} />
        <Route path="/cadastro" element={<Cadastro />} />
        <Route path="/criar-conta" element={<Cadastro />} />
        <Route path="/sugestao" element={<Sugestao />} />
        <Route path="/acesso" element={<Acesso />} />
      </Routes>
    </Router>
  );
}

export default App;

