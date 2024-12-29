export interface Professor {
    id?: number
    nome: string;
    email: string;
    telefoneContato: string;
    especializacao: string;
    instituicaoVinculo: string;
}

export interface Workshop {
    id?: number
    titulo: string;
    descricao: string;
    data: string;
    professor: Professor;
}