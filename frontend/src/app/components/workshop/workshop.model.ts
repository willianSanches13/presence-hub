import {Aluno} from "../aluno/aluno.model";

export interface Professor {
    id?: number
    nome: string;
    email: string;
    telefoneContato: string;
    especializacao: string;
    instituicaoVinculo: string;
}

export interface Workshop {
    id?: number;
    titulo: string;
    descricao: string;
    data: string;
    professor: Professor;
    certificadosGerados: boolean;
    horaInicio: string;
    horaFim: string;
    participacoes?: Participacao[];
}

export interface Participacao {
    id?: number;
    aluno: Aluno;
    workshop: Workshop;
    presente: boolean;
}