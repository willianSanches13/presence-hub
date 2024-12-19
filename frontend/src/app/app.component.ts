import {Component, OnInit} from '@angular/core';
import {AuthService} from "./components/login/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})
export class AppComponent implements OnInit {
  mostrarMenu: boolean = false;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.authService.mostrarMenuEmitter.subscribe(
        mostrar => this.mostrarMenu = mostrar
    );
  }
}