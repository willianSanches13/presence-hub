import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { WorkshopService } from '../workshop.service';
import { AlunoService } from '../../aluno/aluno.service';
import { Workshop } from '../workshop.model';
import { Aluno } from '../../aluno/aluno.model';
import { HeaderService } from '../../template/header/header.service';
import {ConfirmationDialogComponent} from "./confirmation-dialog.component";
import {MatDialog} from '@angular/material/dialog';

@Component({
    selector: 'app-workshop-attendance',
    templateUrl: './workshop-attendance.component.html',
    styleUrls: ['./workshop-attendance.component.css'], encapsulation: ViewEncapsulation.None
})
export class WorkshopAttendanceComponent implements OnInit {
    workshop: Workshop;
    alunos: Aluno[];

    constructor(
        private workshopService: WorkshopService,
        private alunoService: AlunoService,
        private route: ActivatedRoute,
        private headerService: HeaderService,
        private router: Router,
        private dialog: MatDialog
    ) {}

    ngOnInit(): void {
        const id = +this.route.snapshot.paramMap.get('id');
        this.workshopService.readById(id).subscribe(workshop => {
            this.workshop = workshop;
        });
        this.alunoService.read().subscribe(alunos => {
            this.alunos = alunos;
        });

        this.headerService.headerData = {
            title: 'Registro de Presença',
            icon: 'assignment',
            routeUrl: this.route.snapshot.url.join('/')
        };
    }

    registerAttendance(alunoId: number, presenca: boolean): void {
        this.workshopService.registerAttendance(alunoId, this.workshop.id, presenca).subscribe(() => {
            const message = presenca ? 'Presença registrada com sucesso!' : 'Presença removida com sucesso!';
            this.workshopService.showMessage(message);
        });
    }

    createCertificados(): void {
        const dialogRef = this.dialog.open(ConfirmationDialogComponent);

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                const workshopId = this.workshop.id;
                const alunosIds = this.alunos.map(aluno => aluno.id);
                this.workshopService.createCertificados(workshopId, alunosIds).subscribe(() => {
                    this.workshopService.showMessage('Gerando Certificados!');
                    this.router.navigate(['/workshops']).then(() => {
                        window.location.reload();
                    });
                });
            }
        });
    }
}