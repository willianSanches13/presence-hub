import { Injectable } from "@angular/core";
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from "@angular/material/snack-bar";
import { HttpClient } from "@angular/common/http";
import { Aluno } from "./aluno.model";
import { Observable, EMPTY } from "rxjs";
import { environment } from 'src/environments/environment';
import { map, catchError } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class AlunoService {
  baseUrl = `${environment.environmentbaseUrl}/alunos`;

  constructor(private snackBar: MatSnackBar, private http: HttpClient) {}

  showMessage(msg: string, isError: boolean = false): void {
    const snackBarRef: MatSnackBarRef<SimpleSnackBar> = this.snackBar.open(msg, "✖", {
      duration: 3000,
      horizontalPosition: "right",
      verticalPosition: "top",
      panelClass: isError ? ["msg-error"] : ["msg-success"],
    });

    snackBarRef.onAction().subscribe(() => {
      snackBarRef.dismiss();
    });
  }

  create(Aluno: Aluno): Observable<Aluno> {
    return this.http.post<Aluno>(this.baseUrl, Aluno).pipe(
        map((obj) => obj),
        catchError((e) => this.errorHandler(e))
    );
  }


  read(): Observable<Aluno[]> {
    return this.http.get<Aluno[]>(this.baseUrl).pipe(
        map((obj) => obj),
        catchError((e) => this.errorHandler(e))
    );
  }

  readById(id: number): Observable<Aluno> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Aluno>(url).pipe(
        map((obj) => obj),
        catchError((e) => this.errorHandler(e))
    );
  }

  update(Aluno: Aluno): Observable<Aluno> {
    const url = `${this.baseUrl}/${Aluno.id}`;
    return this.http.put<Aluno>(url, Aluno).pipe(
        map((obj) => obj),
        catchError((e) => this.errorHandler(e))
    );
  }

  delete(id: number): Observable<Aluno> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete<Aluno>(url).pipe(
        map((obj) => obj),
        catchError((e) => this.errorHandler(e))
    );
  }

  buscarEndereco(cep: string): Observable<any> {
    return this.http.get(`https://viacep.com.br/ws/${cep}/json/`).pipe(
        map((data: any) => data),
        catchError((e) => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage("Ocorreu um erro!", true);
    return EMPTY;
  }
}