import { AlunoDeleteComponent } from './components/aluno/aluno-delete/aluno-delete.component';
import { AlunoUpdateComponent } from './components/aluno/aluno-update/aluno-update.component';
import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { HomeComponent } from "./views/home/home.component";
import { AlunoCrudComponent } from "./views/product-crud/aluno-crud.component";
import { AlunoCreateComponent } from './components/aluno/aluno-create/aluno-create.component';
import { AuthGuard } from "./guards/auth.guard";
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  {
    path: "",
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "alunos",
    component: AlunoCrudComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "alunos/create",
    component: AlunoCreateComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "alunos/update/:id",
    component: AlunoUpdateComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "alunos/delete/:id",
    component: AlunoDeleteComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "login",
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}