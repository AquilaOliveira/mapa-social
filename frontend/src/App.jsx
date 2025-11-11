import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { HomePage } from "./pages/HomePage.jsx";
import { Sobre } from "./pages/Sobre.jsx";
import { Contato } from "./pages/Contato.jsx";
import { Login } from "./pages/Login.jsx";
import { Cadastro } from "./pages/Cadastro.jsx";
import { Sugestao } from "./pages/Sugestao.jsx";
import { Acesso } from "./pages/Acesso.jsx";


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/sobre" element={<Sobre />} />
        <Route path="/contato" element={<Contato />} />
        <Route path="/login" element={<Login onLogin={(email) => console.log("Logou com:", email)} />} />
        <Route path="/cadastro" element={<Cadastro />} />
        <Route path="/criar-conta" element={<Cadastro />} />
        <Route path="/sugestao" element={<Sugestao />} />
        <Route path="/acesso" element={<Acesso onLogin={(email) => console.log("Logou com:", email)} />} />
      </Routes>
    </Router>
  );
}

export default App;

