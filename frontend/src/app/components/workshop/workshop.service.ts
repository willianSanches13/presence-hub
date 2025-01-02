import { Injectable } from "@angular/core";
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from "@angular/material/snack-bar";
import { HttpClient } from "@angular/common/http";
import { Workshop } from "./workshop.model";
import { Observable, EMPTY } from "rxjs";
import { environment } from 'src/environments/environment';
import { map, catchError } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class WorkshopService {
  baseUrl = `${environment.environmentbaseUrl}/workshops`;

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

  create(Workshop: Workshop): Observable<Workshop> {
    return this.http.post<Workshop>(this.baseUrl, Workshop).pipe(
        map((obj) => obj),
        catchError((e) => this.errorHandler(e))
    );
  }


  read(): Observable<Workshop[]> {
    return this.http.get<Workshop[]>(this.baseUrl).pipe(
        map((obj) => obj),
        catchError((e) => this.errorHandler(e))
    );
  }

  readById(id: number): Observable<Workshop> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Workshop>(url).pipe(
        map((obj) => obj),
        catchError((e) => this.errorHandler(e))
    );
  }

  update(Workshop: Workshop): Observable<Workshop> {
    const url = `${this.baseUrl}/${Workshop.id}`;
    return this.http.put<Workshop>(url, Workshop).pipe(
        map((obj) => obj),
        catchError((e) => this.errorHandler(e))
    );
  }

  delete(id: number): Observable<Workshop> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete<Workshop>(url).pipe(
        map((obj) => obj),
        catchError((e) => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage("Ocorreu um erro!", true);
    return EMPTY;
  }
}