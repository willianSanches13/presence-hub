import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { Usuario } from './usuario';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AlunoForm} from "../aluno/aluno.form";
import {LoginForm} from "./login.form";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup<LoginForm>;

  constructor(private fb: FormBuilder, private authService: AuthService) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group<LoginForm>({
      login: this.fb.control('', Validators.required),
      password: this.fb.control('', [Validators.required])
    });
  }

  fazerLogin(){
    const usuario: Usuario = this.loginForm.getRawValue();
    this.authService.fazerLogin(usuario);
  }

}
