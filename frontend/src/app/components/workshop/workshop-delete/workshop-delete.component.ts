import { Router, ActivatedRoute } from "@angular/router";
import { WorkshopService } from "../workshop.service";
import { Workshop } from "../workshop.model";
import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-workshop-delete",
  templateUrl: "./workshop-delete.component.html",
  styleUrls: ["./workshop-delete.component.css"],
})
export class WorkshopDeleteComponent implements OnInit {
  workshop: Workshop;

  constructor(
      private workshopService: WorkshopService,
      private router: Router,
      private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.workshopService.readById(id).subscribe((workshop) => {
      this.workshop = workshop;
    });
  }

  deleteWorkshop(): void {
    this.workshopService.delete(this.workshop.id).subscribe(() => {
      this.workshopService.showMessage("Workshop excluído com sucesso!");
      this.router.navigate(["/workshops"]);
    });
  }

  cancel(): void {
    this.router.navigate(["/workshops"]);
  }
}