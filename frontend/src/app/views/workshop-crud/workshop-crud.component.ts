import { HeaderService } from '../../components/template/header/header.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-workshop-crud',
    templateUrl: './workshop-crud.component.html',
    styleUrls: ['./workshop-crud.component.css']
})
export class WorkshopCrudComponent implements OnInit {

    constructor(private router: Router, private headerService: HeaderService) {
        headerService.headerData = {
            title: 'Cadastro de Workshops',
            icon: 'event',
            routeUrl: '/workshops'
        };
    }

    ngOnInit(): void {
    }

    navigateToWorkshopCreate(): void {
        this.router.navigate(['/workshops/create']);
    }

}