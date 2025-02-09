import { HttpClient } from '@angular/common/http';
import { Injectable, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from './usuario';
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl = `${environment.environmentbaseUrl}/users/login`;

  private usuarioAutenticado: boolean = false;
  private token: string | null = null;

  mostrarMenuEmitter = new EventEmitter<boolean>();

  constructor(private router: Router, private http: HttpClient) {}

  fazerLogin(usuario: Usuario) {
    this.http.post<{ token: string }>(this.baseUrl, usuario).subscribe(response => {
      this.token = response.token;
      console.log("token:" + this.token)
      this.usuarioAutenticado = true;
      this.mostrarMenuEmitter.emit(true);
      console.log("aqui foi")
      this.router.navigate(['/']);
    });
  }

  getToken() {
    return this.token;
  }

  usuarioEstaAutenticado() {
    return this.usuarioAutenticado;
  }
}