import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Aulas from './Aulas.js';
import Home from './Home.js';
import CertificadosAlunos from './certificados/CertificadosAlunos.js';
import CertificadosProfessores from './certificados/CertificadosProfessores.js';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/aulas" element={<Aulas />} />
        <Route path="/certificados-alunos" element={<CertificadosAlunos />} />
        <Route path="/certificados-professores" element={<CertificadosProfessores />} />
      </Routes>
    </Router>
  );
}

export default App;