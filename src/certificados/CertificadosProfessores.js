const handleDownload = async (id) => {
    try {
      const response = await fetch('https://api.example.com/gerar-certificado', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ certificadoId: id }),
      });
  
      if (response.ok) {
        alert(`Certificado (ID: ${id}) gerado com sucesso!`);
      } else {
        alert('Erro ao gerar certificado. Tente novamente.');
      }
    } catch (error) {
      console.error('Erro na requisição:', error);
      alert('Erro ao conectar ao servidor.');
    }
  };
  