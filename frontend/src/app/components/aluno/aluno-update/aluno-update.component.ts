import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlunoService } from '../aluno.service';
import { SerieEnum, SerieEnumKeys } from '../enums/serie.enum';

@Component({
  selector: 'app-aluno-update',
  templateUrl: './aluno-update.component.html',
  styleUrls: ['./aluno-update.component.css']
})
export class AlunoUpdateComponent implements OnInit {
  alunoForm: FormGroup;
  series = SerieEnumKeys;

  constructor(
      private fb: FormBuilder,
      private alunoService: AlunoService,
      private router: Router,
      private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.alunoForm = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      dataNascimento: [null, Validators.required],
      serie: ['', Validators.required],
      endereco: this.fb.group({
        logradouro: ['', Validators.required],
        bairro: ['', Validators.required],
        cidade: ['', Validators.required],
        uf: ['', Validators.required],
        cep: ['', Validators.required],
        numero: [null, Validators.required],
        complemento: ['']
      }),
      instituicaoDeEnsino: ['', Validators.required],
      telefoneContato: ['', Validators.required],
      nomeResponsavel: ['', Validators.required],
      telefoneResponsavel: ['', Validators.required],
      matriculaProjeto: ['', Validators.required],
      dataInscricao: [null, Validators.required],
      observacoes: ['']
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.alunoService.readById(+id).subscribe(aluno => {
        this.alunoForm.patchValue(aluno);
      });
    }
  }

  buscarEndereco(): void {
    const cep = this.alunoForm.get('endereco.cep')?.value;
    if (cep) {
      this.alunoService.buscarEndereco(cep).subscribe((data: any) => {
        this.alunoForm.patchValue({
          endereco: {
            logradouro: data.logradouro,
            bairro: data.bairro,
            cidade: data.localidade,
            uf: data.uf
          }
        });
      });
    }
  }

  updateAluno(): void {
    if (this.alunoForm.valid) {
      const aluno = this.alunoForm.getRawValue();
      this.alunoService.update(aluno).subscribe(() => {
        this.alunoService.showMessage('Aluno atualizado!');
        this.router.navigate(['/alunos']);
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/alunos']);
  }

  protected readonly SerieEnum = SerieEnum;
}