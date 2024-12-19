import { Router } from '@angular/router';
import { UsuarioService } from './../usuario.service';
import { Usuario } from './../usuario.model';
import { Component, OnInit } from '@angular/core';

@Component({
selector: 'app-usuario-delete',
templateUrl: './usuario-delete.component.html',
styleUrls: ['./usuario-delete.component.css']
})

export class UsuarioDeleteComponent implements OnInit {
usuario: Usuario = { nome: '', nick:'', senha: '', status: true}
constructor(
private usuarioService: UsuarioService, private router: Router
) { }
ngOnInit(): void {
this.usuario = this.usuarioService.usuario;
}

delete(): void{
this.usuarioService.delete(this.usuario).subscribe(() => {
this.usuarioService.showMessage('Usuário excluído com sucesso!')
this.router.navigate(['/usuario'])
})
}


cancelar(): void {
this.router.navigate(['/usuario'])
}
}