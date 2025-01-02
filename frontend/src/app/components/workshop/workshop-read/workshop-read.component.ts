import { WorkshopService } from '../workshop.service';
import { Workshop } from '../workshop.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-workshop-read',
  templateUrl: './workshop-read.component.html',
  styleUrls: ['./workshop-read.component.css']
})
export class WorkshopReadComponent implements OnInit {

  workshops: Workshop[];
  displayedColumns = ['id', 'titulo', 'descricao', 'data', 'professorNome', 'action'];

  constructor(private workshopService: WorkshopService) { }

  ngOnInit(): void {
    this.workshopService.read().subscribe(workshops => {
      this.workshops = workshops;
    });
  }

}