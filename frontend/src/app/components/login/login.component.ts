import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { Usuario } from './usuario';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AlunoForm} from "../aluno/aluno.form";
import {LoginForm} from "./login.form";
import {ActivatedRoute, Router} from "@angular/router";
import {HeaderService} from "../template/header/header.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup<LoginForm>;

  constructor(private fb: FormBuilder, private router: Router, private authService: AuthService, private headerService: HeaderService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group<LoginForm>({
      login: this.fb.control('', Validators.required),
      password: this.fb.control('', [Validators.required])
    });
    this.headerService.headerData = {
      title: 'Login',
      icon: 'login',
      routeUrl: this.route.snapshot.url.join('/login')
    };
  }


  fazerLogin(){
    const usuario: Usuario = this.loginForm.getRawValue();
    this.authService.fazerLogin(usuario);
  }


  cadastrar(){
    this.router.navigate(['/users']);
  }

}
