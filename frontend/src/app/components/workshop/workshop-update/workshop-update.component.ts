import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { WorkshopService } from '../workshop.service';
import { Workshop } from '../workshop.model';

@Component({
  selector: 'app-workshop-update',
  templateUrl: './workshop-update.component.html',
  styleUrls: ['./workshop-update.component.css']
})
export class WorkshopUpdateComponent implements OnInit {
  workshopForm: FormGroup;

  constructor(
      private fb: FormBuilder,
      private workshopService: WorkshopService,
      private router: Router,
      private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.workshopForm = this.fb.group({
      titulo: ['', Validators.required],
      descricao: ['', Validators.required],
      data: [null, Validators.required],
      professor: this.fb.group({
        nome: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        telefoneContato: ['', Validators.required],
        especializacao: ['', Validators.required],
        instituicaoVinculo: ['', Validators.required]
      })
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.workshopService.readById(+id).subscribe(workshop => {
        this.workshopForm.patchValue(workshop);
      });
    }
  }

  updateWorkshop(): void {
    if (this.workshopForm.valid) {
      const workshop = this.workshopForm.getRawValue();
      this.workshopService.update(workshop).subscribe(() => {
        this.workshopService.showMessage('Workshop atualizado!');
        this.router.navigate(['/workshops']);
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/workshops']);
  }
}