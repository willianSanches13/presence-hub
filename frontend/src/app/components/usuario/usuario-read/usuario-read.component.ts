import { UsuarioService } from './../usuario.service';
import { Usuario } from './../usuario.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';



@Component({
  selector: 'app-usuario-read',
  templateUrl: './usuario-read.component.html',
  styleUrls: ['./usuario-read.component.css']
})

export class UsuarioReadComponent implements OnInit {

usuario: Usuario[] = []
colunas = ['nome', 'senha','acoes']

constructor(private usuarioService: UsuarioService,
            private router: Router) { }

  ngOnInit(): void {
    this.usuarioService.read().subscribe(usuario => {
      this.usuario = usuario
      console.log(usuario)
      })
  }


  editar(usuario: Usuario): void {
    console.log("testando editar", usuario);
    this.usuarioService.salvar = false;
    this.usuarioService.usuario = usuario;
    this.router.navigate(['/usuario/create'])
    }


    excluir(usuario: Usuario): void {
      this.usuarioService.usuario = usuario;
      this.router.navigate(['/usuario/delete'])
      }
}


