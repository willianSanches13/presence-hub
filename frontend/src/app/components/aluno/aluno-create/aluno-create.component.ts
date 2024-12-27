import { Aluno, Endereco } from '../aluno.model';
import { AlunoService } from '../aluno.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { AlunoForm, EnderecoForm } from '../aluno.form';
import { HttpClient } from '@angular/common/http';
import { SerieEnum, SerieEnumKeys } from '../enums/serie.enum';

@Component({
  selector: 'app-aluno-create',
  templateUrl: './aluno-create.component.html',
  styleUrls: ['./aluno-create.component.css']
})
export class AlunoCreateComponent implements OnInit {

  alunoForm: FormGroup<AlunoForm>;
  series = SerieEnumKeys;

  constructor(private fb: FormBuilder, private alunoService: AlunoService, private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.alunoForm = this.fb.group<AlunoForm>({
      nome: this.fb.control('', Validators.required),
      email: this.fb.control('', [Validators.required, Validators.email]),
      dataNascimento: this.fb.control<Date | null>(null, Validators.required),
      serie: this.fb.control('', Validators.required),
      endereco: this.fb.group<EnderecoForm>({
        logradouro: this.fb.control('', Validators.required),
        bairro: this.fb.control('', Validators.required),
        cidade: this.fb.control('', Validators.required),
        uf: this.fb.control('', Validators.required),
        cep: this.fb.control('', Validators.required),
        numero: this.fb.control(null, Validators.required),
        complemento: this.fb.control('')
      }),
      instituicaoDeEnsino: this.fb.control('', Validators.required),
      telefoneContato: this.fb.control('', Validators.required),
      nomeResponsavel: this.fb.control('', Validators.required),
      telefoneResponsavel: this.fb.control('', Validators.required),
      matriculaProjeto: this.fb.control('', Validators.required),
      dataInscricao: this.fb.control<Date | null>(null, Validators.required),
      observacoes: this.fb.control('')
    });
  }

  buscarEndereco(): void {
    const cep = this.alunoForm.get('endereco.cep')?.value;
    if (cep) {
      this.http.get(`https://viacep.com.br/ws/${cep}/json/`).subscribe((data: any) => {
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

  protected readonly SerieEnum = SerieEnum;
}