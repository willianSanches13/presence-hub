import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { WorkshopService } from '../workshop.service';

@Component({
  selector: 'app-workshop-details',
  templateUrl: './workshop-details.component.html',
  styleUrls: ['./workshop-details.component.css']
})
export class WorkshopDetailsComponent implements OnInit {
  workshopForm: FormGroup;
  participacoes: any[] = [];

  constructor(
      private fb: FormBuilder,
      private workshopService: WorkshopService,
      private router: Router,
      private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.workshopForm = this.fb.group({
      titulo: [''],
      descricao: [''],
      data: [null],
      professor: this.fb.group({
        nome: [''],
        email: [''],
        telefoneContato: [''],
        especializacao: [''],
        instituicaoVinculo: ['']
      })
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.workshopService.readById(+id).subscribe(workshop => {
        this.workshopForm.patchValue(workshop);
        this.participacoes = workshop.participacoes;
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/workshops']);
  }
}