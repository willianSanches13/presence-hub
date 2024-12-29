import { FormControl, FormGroup } from "@angular/forms";

export interface ProfessorForm {
    nome: FormControl<string>;
    email: FormControl<string>;
    telefoneContato: FormControl<string>;
    especializacao: FormControl<string>;
    instituicaoVinculo: FormControl<string>;
}

export interface WorkshopForm {
    titulo: FormControl<string>;
    descricao: FormControl<string>;
    data: FormControl<string>;
    professor: FormGroup<ProfessorForm>;
}