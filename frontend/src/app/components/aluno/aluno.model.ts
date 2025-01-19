export interface Endereco {
    id?: number;
    logradouro?: string;
    bairro?: string;
    cidade: string;
    uf: string;
    cep?: string;
    numero?: number;
    complemento?: string;
}

export interface Aluno {
    id?: number;
    nome: string;
    email?: string;
    dataNascimento: Date;
    serie: string;
    endereco: Endereco;
    instituicaoDeEnsino?: string;
    telefoneContato?: string;
    nomeResponsavel?: string;
    telefoneResponsavel?: string;
    matriculaProjeto?: string;
    dataInscricao?: Date;
    observacoes?: string;
    certificados?: Certificado[];
}

export interface Certificado {
    id: number;
    dataEmissao: string;
    workshopName: string;
    path: string;
}