export interface Aluno {
    id?: number;
    nome: string;
    email: string;
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
    dataInscricao: Date;
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