// import { Product } from '../aluno.model';
// import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
// import { MatPaginator } from '@angular/material/paginator';
// import { MatSort } from '@angular/material/sort';
// import { MatTable } from '@angular/material/table';
// import { AlunoRead2Datasource } from './aluno-read2-datasource';
//
// @Component({
//   selector: 'app-aluno-read2',
//   templateUrl: './aluno-read2.component.html',
//   styleUrls: ['./aluno-read2.component.css']
// })
// export class AlunoRead2Component implements AfterViewInit, OnInit {
//   @ViewChild(MatPaginator) paginator: MatPaginator;
//   @ViewChild(MatSort) sort: MatSort;
//   @ViewChild(MatTable) table: MatTable<Product>;
//   dataSource: AlunoRead2Datasource;
//
//   /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
//   displayedColumns = ['id', 'name', 'price'];
//
//   ngOnInit() {
//     this.dataSource = new AlunoRead2Datasource();
//   }
//
//   ngAfterViewInit() {
//     this.dataSource.sort = this.sort;
//     this.dataSource.paginator = this.paginator;
//     this.table.dataSource = this.dataSource;
//   }
// }
