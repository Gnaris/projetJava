import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MaterialModule } from '../../shared/material-module';
import { Router, RouterLink } from '@angular/router';
import { DevoirService } from '../../service/devoir.service';
import { HttpClientModule } from '@angular/common/http';
import { ModificationService } from '../../service/modification.service';
import { Devoir } from '../../model/devoirs';


@Component({
  selector: 'app-resultat-devoir',
  standalone: true,
  imports: [MaterialModule, HttpClientModule, RouterLink],
  providers: [DevoirService],
  templateUrl: './resultat-devoir.component.html',
  styleUrl: './resultat-devoir.component.css'
})
export class ResultatDevoirsComponent implements OnInit {
  displayedColumns: string[] = ['id', 'type', 'date', 'coefficient', 'classe', 'matiere'];
  dataSource = new MatTableDataSource<Devoir>();

  @ViewChild(MatPaginator) set paginator(pager: MatPaginator) {
    if (pager) {
      this.dataSource.paginator = pager;
      this.dataSource.paginator._intl = new MatPaginatorIntl();
      this.dataSource.paginator._intl.itemsPerPageLabel = `Total : ${this.dataSource.data.length} - Éléments par page`;
      this.dataSource.paginator._intl.getRangeLabel = function (
        page,
        pageSize,
        length
      ) {
        if (length === 0) {
          return 'Page 1 sur 1';
        }
        const amountPages = Math.ceil(length / pageSize);
        return `Page ${page + 1} sur ${amountPages}`;
      };
    }
  }

  constructor(
    private router: Router,
    private DevoirService: DevoirService,
    private modificationService: ModificationService) {
  }
  
  ngOnInit(): void {
    this.initDevoirList();
    this.dataSource.paginator = this.paginator;
  }

  private initDevoirList(): void {
    this.DevoirService.rechercherDevoirs().subscribe({
      next: value => this.dataSource.data = value.success ? value.data : [],
      error: err => console.error(err)
    });
  }

  ajouterDevoir(): void {
    console.log("zef")
    this.modificationService.envoyerObjetACreerOuModifier({});
    this.router.navigateByUrl('detail-devoir');
  }

  modifierDevoir(DevoirAModifier: Devoir): void {
    this.modificationService.envoyerObjetACreerOuModifier(DevoirAModifier);
    this.router.navigateByUrl('detail-Devoir');
  }

  supprimerDevoir(id: number): void {
    this.DevoirService.supprimerDevoir(id).subscribe({
      next : value => {
        this.dataSource.data = this.dataSource.data.filter(d => d.id != id)
      },
      error : err => console.log(err)
    })
  }
}


