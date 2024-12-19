export interface Aluno {
    id?: number;
    nome: string;
    email: string;
    senha: string;
    idade: number;
    serie: string;
    instituicaoDeEnsino: string;
    telefoneContato: string;
    cidade: string;
    estado: string;
    nomeResponsavel: string;
    telefoneResponsavel: string;
    matriculaProjeto: string;
    statusParticipacao: string;
    dataInscricao: string; // Use string for LocalDate
    observacoes: string;
    //participacoes?: Participacao[];
    //certificados?: Certificado[];
}

// export interface Participacao {
//     // Defina os campos de ParticipacaoDTO aqui
// }
//
// export interface Certificado {
//     // Defina os campos de CertificadoDTO aqui
// }