import { Aluno } from '../aluno.model';
import { AlunoService } from '../aluno.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-aluno-create',
  templateUrl: './aluno-create.component.html',
  styleUrls: ['./aluno-create.component.css']
})
export class AlunoCreateComponent implements OnInit {

  aluno: Aluno = {
    nome: '',
    email: '',
    senha: '',
    idade: null,
    serie: '',
    instituicaoDeEnsino: '',
    telefoneContato: '',
    cidade: '',
    estado: '',
    nomeResponsavel: '',
    telefoneResponsavel: '',
    matriculaProjeto: '',
    statusParticipacao: '',
    dataInscricao: '',
    observacoes: ''
  }

  constructor(private alunoService: AlunoService,
              private router: Router) { }

  ngOnInit(): void {

  }

  createAluno(): void {
    this.alunoService.create(this.aluno).subscribe(() => {
      this.alunoService.showMessage('Aluno criado!')
      this.router.navigate(['/alunos'])
    })
  }

  cancel(): void {
    this.router.navigate(['/alunos'])
  }
}