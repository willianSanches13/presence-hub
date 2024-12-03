import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css';

function Home() {
  return (
    <div className="home-container">
      <h1 className="home-title">Bem-vindo!</h1>
      <div className="button-grid">
        <Link to="/aulas" className="menu-button">Aulas</Link>
        <Link to="/certificados-alunos" className="menu-button"> Certificados (Alunos)</Link>
        <Link to="/certificados-professores" className="menu-button"> Certificados (Professores)</Link>
        <button className="menu-button">Botão 3</button>
        <button className="menu-button">Botão 4</button>
      </div>
    </div>
  );
}

export default Home;