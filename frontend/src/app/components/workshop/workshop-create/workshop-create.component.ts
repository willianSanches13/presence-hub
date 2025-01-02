import { Workshop, Professor } from '../workshop.model';
import { WorkshopService } from '../workshop.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-workshop-create',
  templateUrl: './workshop-create.component.html',
  styleUrls: ['./workshop-create.component.css']
})
export class WorkshopCreateComponent implements OnInit {

  workshopForm: FormGroup;

  constructor(private fb: FormBuilder, private workshopService: WorkshopService, private router: Router) { }

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
  }

  createWorkshop(): void {
    if (this.workshopForm.valid) {
      const workshop: Workshop = this.workshopForm.getRawValue();
      this.workshopService.create(workshop).subscribe(() => {
        this.workshopService.showMessage('Workshop criado!');
        this.router.navigate(['/workshops']);
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/workshops']);
  }
}