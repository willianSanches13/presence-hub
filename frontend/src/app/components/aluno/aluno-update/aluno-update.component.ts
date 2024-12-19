import { Aluno } from "../aluno.model";
import { Router, ActivatedRoute } from "@angular/router";
import { AlunoService } from "../aluno.service";
import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-aluno-update",
  templateUrl: "./aluno-update.component.html",
  styleUrls: ["./aluno-update.component.css"],
})
export class AlunoUpdateComponent implements OnInit {
  aluno: Aluno;

  constructor(
      private alunoService: AlunoService,
      private router: Router,
      private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get("id");
    this.alunoService.readById(id).subscribe((aluno) => {
      this.aluno = aluno;
    });
  }

  updateAluno(): void {
    this.alunoService.update(this.aluno).subscribe(() => {
      this.alunoService.showMessage("Aluno atualizado com sucesso!");
      this.router.navigate(["/alunos"]);
    });
  }

  cancel(): void {
    this.router.navigate(["/alunos"]);
  }
}