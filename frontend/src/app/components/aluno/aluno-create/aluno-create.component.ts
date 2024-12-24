import { Aluno } from '../aluno.model';
import { AlunoService } from '../aluno.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

interface AlunoForm {
  nome: FormControl<string>;
  email: FormControl<string>;
  idade: FormControl<number>;
  serie: FormControl<string>;
  instituicaoDeEnsino: FormControl<string>;
  telefoneContato: FormControl<string>;
  cidade: FormControl<string>;
  estado: FormControl<string>;
  nomeResponsavel: FormControl<string>;
  telefoneResponsavel: FormControl<string>;
  matriculaProjeto: FormControl<string>;
  statusParticipacao: FormControl<string>;
  dataInscricao: FormControl<Date>;
  observacoes: FormControl<string>;
}

@Component({
  selector: 'app-aluno-create',
  templateUrl: './aluno-create.component.html',
  styleUrls: ['./aluno-create.component.css']
})
export class AlunoCreateComponent implements OnInit {

  alunoForm: FormGroup<AlunoForm>;

  constructor(private fb: FormBuilder, private alunoService: AlunoService, private router: Router) { }

  ngOnInit(): void {
    this.alunoForm = this.fb.group<AlunoForm>({
      nome: this.fb.control('', Validators.required),
      email: this.fb.control('', [Validators.required, Validators.email]),
      idade: this.fb.control(null, Validators.required),
      serie: this.fb.control('', Validators.required),
      instituicaoDeEnsino: this.fb.control('', Validators.required),
      telefoneContato: this.fb.control('', [Validators.required, Validators.pattern(/^\(\d{2}\) \d{5}-\d{4}$/)]),
      cidade: this.fb.control('', Validators.required),
      estado: this.fb.control('', Validators.required),
      nomeResponsavel: this.fb.control('', Validators.required),
      telefoneResponsavel: this.fb.control('', [Validators.required, Validators.pattern(/^\(\d{2}\) \d{5}-\d{4}$/)]),
      matriculaProjeto: this.fb.control('', Validators.required),
      statusParticipacao: this.fb.control('', Validators.required),
      dataInscricao: this.fb.control(null, Validators.required),
      observacoes: this.fb.control('')
    });
  }

  createAluno(): void {
    if (this.alunoForm.valid) {
      const aluno: Aluno = this.alunoForm.getRawValue();
      this.alunoService.create(aluno).subscribe(() => {
        this.alunoService.showMessage('Aluno criado!');
        this.router.navigate(['/alunos']);
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/alunos']);
  }
}