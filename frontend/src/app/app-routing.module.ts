import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { HomeComponent } from "./views/home/home.component";
import { AlunoCrudComponent } from "./views/aluno-crud/aluno-crud.component";
import { AlunoCreateComponent } from './components/aluno/aluno-create/aluno-create.component';
import { AlunoUpdateComponent } from './components/aluno/aluno-update/aluno-update.component';
import { AlunoDeleteComponent } from './components/aluno/aluno-delete/aluno-delete.component';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from "./guards/auth.guard";
import { WorkshopCrudComponent } from './views/workshop-crud/workshop-crud.component';
import { WorkshopCreateComponent } from './components/workshop/workshop-create/workshop-create.component';
import { WorkshopUpdateComponent } from './components/workshop/workshop-update/workshop-update.component';
import { WorkshopDeleteComponent } from './components/workshop/workshop-delete/workshop-delete.component';

const routes: Routes = [
  {
    path: "",
    component: HomeComponent,
    // canActivate: [AuthGuard]
  },
  {
    path: "alunos",
    component: AlunoCrudComponent,
    // canActivate: [AuthGuard]
  },
  {
    path: "alunos/create",
    component: AlunoCreateComponent,
    // canActivate: [AuthGuard]
  },
  {
    path: "alunos/update/:id",
    component: AlunoUpdateComponent,
    // canActivate: [AuthGuard]
  },
  {
    path: "alunos/delete/:id",
    component: AlunoDeleteComponent,
    // canActivate: [AuthGuard]
  },
  {
    path: "workshops",
    component: WorkshopCrudComponent,
    // canActivate: [AuthGuard]
  },
  {
    path: "workshops/create",
    component: WorkshopCreateComponent,
    // canActivate: [AuthGuard]
  },
  {
    path: "workshops/update/:id",
    component: WorkshopUpdateComponent,
    // canActivate: [AuthGuard]
  },
  {
    path: "workshops/delete/:id",
    component: WorkshopDeleteComponent,
    // canActivate: [AuthGuard]
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