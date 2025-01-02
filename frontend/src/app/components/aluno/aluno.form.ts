// src/app/components/aluno/aluno.form.ts
import { FormControl, FormGroup } from "@angular/forms";

export interface EnderecoForm {
    logradouro: FormControl<string | null>;
    bairro: FormControl<string | null>;
    cidade: FormControl<string>;
    uf: FormControl<string>;
    cep: FormControl<string | null>;
    numero: FormControl<number | null>;
    complemento: FormControl<string | null>;
}

export interface AlunoForm {
    nome: FormControl<string>;
    email: FormControl<string | null>;
    dataNascimento: FormControl<Date>;
    serie: FormControl<string>;
    endereco: FormGroup<EnderecoForm>;
    instituicaoDeEnsino: FormControl<string | null>;
    telefoneContato: FormControl<string | null>;
    nomeResponsavel: FormControl<string | null>;
    telefoneResponsavel: FormControl<string | null>;
    matriculaProjeto: FormControl<string | null>;
    dataInscricao: FormControl<Date | null>;
    observacoes: FormControl<string | null>;
}