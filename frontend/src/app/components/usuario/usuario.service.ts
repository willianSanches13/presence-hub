import { HttpClient } from '@angular/common/http';
import { MatSnackBar} from '@angular/material/snack-bar';
import { Injectable } from '@angular/core';
import { Usuario } from './usuario.model';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  url = "http://localhost:3000/usuario"
  salvar = false;
  usuario: Usuario = {
    nome: '', nick:'', senha: '', status: true
}

  constructor(
    private snackBar: MatSnackBar,
    private http: HttpClient
    ) {}

    showMessage(msg: string): void {
      this.snackBar.open(msg, "X", {
      duration: 3000,
      horizontalPosition: "right",
      verticalPosition: "top"
    });
}

create(usuario: Usuario): Observable<Usuario>{
return this.http.post<Usuario>(this.url, usuario)
  }

read():Observable<Usuario[]>{
  return this.http.get<Usuario[]>(this.url);
  }

  update(usuario: Usuario): Observable<Usuario> {
    const urlAlterar = `${this.url}/${usuario._id}`;
    return this.http.patch<Usuario>(urlAlterar, usuario)
    }

    delete(usuario: Usuario): Observable<Usuario> {
      const urlExcluir = `${this.url}/${usuario._id}`;
      return this.http.delete<Usuario>(urlExcluir)
      }
}


