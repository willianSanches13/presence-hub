import { Injectable } from "@angular/core";
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from "@angular/material/snack-bar";
import { HttpClient } from "@angular/common/http";
import { User } from "./user.model";
import {Observable, EMPTY, tap} from "rxjs";
import { environment } from 'src/environments/environment';
import { map, catchError } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class UserService {
  baseUrl = `${environment.environmentbaseUrl}/users/register`;

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

  create(User: User): Observable<User> {
    return this.http.post<User>(this.baseUrl, User).pipe(
        tap(response => console.log('API Response:', response)),
        map((obj) => obj),
        catchError((e) => {
          console.error('API Error:', e);
          return this.errorHandler(e);
        })
    );
  }


  errorHandler(e: any): Observable<any> {
    this.showMessage("Ocorreu um erro!", true);
    return EMPTY;
  }
}