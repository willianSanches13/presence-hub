import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlunoService } from '../aluno.service';
import { SerieEnum, SerieEnumKeys } from '../enums/serie.enum';

@Component({
  selector: 'app-aluno-details',
  templateUrl: './aluno-details.component.html',
  styleUrls: ['./aluno-details.component.css']
})
export class AlunoDetailsComponent implements OnInit {
  alunoForm: FormGroup;
  series = SerieEnumKeys;
  certificados: any[] = [];

  constructor(
      private fb: FormBuilder,
      private alunoService: AlunoService,
      private router: Router,
      private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.alunoForm = this.fb.group({
      nome: [''],
      email: [''],
      dataNascimento: [null],
      serie: [''],
      endereco: this.fb.group({
        logradouro: [''],
        bairro: [''],
        cidade: [''],
        uf: [''],
        cep: [''],
        numero: [null],
        complemento: ['']
      }),
      instituicaoDeEnsino: [''],
      telefoneContato: [''],
      nomeResponsavel: [''],
      telefoneResponsavel: [''],
      matriculaProjeto: [''],
      dataInscricao: [null],
      observacoes: ['']
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.alunoService.readById(+id).subscribe(aluno => {
        this.alunoForm.patchValue(aluno);
        this.certificados = aluno.certificados;
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/alunos']);
  }

  protected readonly SerieEnum = SerieEnum;
}