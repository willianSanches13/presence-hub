import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioService } from './../usuario.service';
import { Usuario } from '../usuario.model';


@Component({
  selector: 'app-usuario-create',
  templateUrl: './usuario-create.component.html',
  styleUrls: ['./usuario-create.component.css']
})

export class UsuarioCreateComponent implements OnInit {

  usuario: Usuario = {
    nome: '', nick: '', senha: '', status: true
    }

  constructor(private usuarioService: UsuarioService,
    private router: Router){}

    ngOnInit(): void {
      if(this.usuarioService.salvar){
      this.usuario = {nome: '', nick:'', senha: '', status: true}
      }else{
      this.usuario = this.usuarioService.usuario;
      }
      }

  
  

  criarUsuario(): void{
    if(this.usuarioService.salvar){
      this.usuarioService.create(this.usuario).subscribe( () => {
      this.usuarioService.showMessage('Usuario Cadastrado com Sucesso!')
      this.router.navigate(['./usuario'])
    })
    }else{
    this.usuarioService.update(this.usuario).subscribe( () => {
    this.usuarioService.showMessage('Usuário alterado com sucesso!')
    this.router.navigate(['/usuario'])
    })
    }
  }




    cancelar(): void{
    this.router.navigate(['./'])
    }

}


