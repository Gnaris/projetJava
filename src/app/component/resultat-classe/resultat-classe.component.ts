import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MaterialModule } from '../../shared/material-module';
import { Router, RouterLink } from '@angular/router';
import { ClasseService } from '../../service/classe.service';
import { HttpClientModule } from '@angular/common/http';
import { ModificationService } from '../../service/modification.service';
import { Classe } from '../../model/classe';


@Component({
  selector: 'app-resultat-classe',
  standalone: true,
  imports: [MaterialModule, HttpClientModule, RouterLink],
  providers: [ClasseService],
  templateUrl: './resultat-classe.component.html',
  styleUrl: './resultat-classe.component.css'
})
export class ResultatClasseComponent implements OnInit {
  displayedColumns: string[] = ['id', 'nom'];
  dataSource = new MatTableDataSource<Classe>();

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
    private classeService: ClasseService,
    private modificationService: ModificationService) {
  }
  ngOnInit(): void {
    this.initClasseList();
    this.dataSource.paginator = this.paginator;
  }

  private initClasseList(): void {
    this.classeService.rechercherClasses().subscribe({
      next: value => this.dataSource.data = value.success ? value.data : [],
      error: err => console.error(err)
    });
  }

  ajouterClasse(): void {
    this.modificationService.envoyerObjetACreerOuModifier({});
    this.router.navigateByUrl('detail-classe');
  }

  modifierClasse(classeAModifier: Classe): void {
    this.modificationService.envoyerObjetACreerOuModifier(classeAModifier);
    this.router.navigateByUrl('detail-classe');
  }

  supprimerClasse(id: number): void {

    this.classeService.supprimerClasse(id).subscribe({
      next: () => {
        this.dataSource.data = this.dataSource.data.filter(item => item.id !== id);
      },
      error: err => console.error('Erreur de suppression: ', err)
    });
  }
}


