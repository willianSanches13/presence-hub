import React, { useState } from 'react';
import './Aulas.css';

function Aulas() {
  const [formData, setFormData] = useState({
    professor: '',
    turma: '',
    dataHora: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('https://api.example.com/cadastrar-aula', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        alert('Aula cadastrada com sucesso!');
        setFormData({ professor: '', turma: '', dataHora: '' });
      } else {
        alert('Erro ao cadastrar aula. Tente novamente.');
      }
    } catch (error) {
      console.error('Erro na requisição:', error);
      alert('Erro ao conectar ao servidor.');
    }
  };

  return (
    <div className="aulas-container">
      <h1>Cadastro de Aulas</h1>
      <form className="aulas-form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="professor">Professor:</label>
          <select
            name="professor"
            id="professor"
            value={formData.professor}
            onChange={handleChange}
          >
            <option value="" disabled>
              Selecione um professor
            </option>
            <option value="professor1">Professor 1</option>
            <option value="professor2">Professor 2</option>
          </select>
        </div>

        <div className="form-group">
          <label htmlFor="turma">Turma:</label>
          <select
            name="turma"
            id="turma"
            value={formData.turma}
            onChange={handleChange}
          >
            <option value="" disabled>
              Selecione uma turma
            </option>
            <option value="turma1">Turma 1</option>
            <option value="turma2">Turma 2</option>
          </select>
        </div>

        <div className="form-group">
          <label htmlFor="dataHora">Data e Hora:</label>
          <input
            type="datetime-local"
            name="dataHora"
            id="dataHora"
            value={formData.dataHora}
            onChange={handleChange}
          />
        </div>

        <button type="submit" className="submit-button">
          Cadastrar Aula
        </button>
      </form>
    </div>
  );
}

export default Aulas;
