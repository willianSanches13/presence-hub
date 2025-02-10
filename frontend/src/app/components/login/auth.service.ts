import { HttpClient } from '@angular/common/http';
import { Injectable, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from './usuario';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl = `${environment.environmentbaseUrl}/users/login`;

  mostrarMenuEmitter = new EventEmitter<boolean>();

  constructor(private router: Router, private http: HttpClient) {}

  fazerLogin(usuario: Usuario) {
    this.http.post<{ token: string }>(this.baseUrl, usuario).subscribe(response => {
      localStorage.setItem('authToken', response.token);
      this.mostrarMenuEmitter.emit(true);
      this.router.navigate(['/']);
    });
  }

  getToken() {
    return localStorage.getItem('authToken');
  }

  usuarioEstaAutenticado() {
    return !!this.getToken();
  }

  logout() {
    localStorage.removeItem('authToken');
    this.mostrarMenuEmitter.emit(false);
    this.router.navigate(['/login']);
  }
}