import { Component, OnInit, ViewChild } from '@angular/core';
import { MAT_TOOLTIP_DEFAULT_OPTIONS, MatTooltipDefaultOptions } from '@angular/material/tooltip';
import { Etudiant } from '../../model/etudiant';
import { EtudiantService } from '../../service/etudiant.service';
import { MaterialModule } from '../../shared/material-module';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { HttpClientModule } from '@angular/common/http';
import { RouterLink, Router } from '@angular/router';
import { ModificationService } from '../../service/modification.service';
import { MatiereService } from '../../service/matiere.service';
import { MatIconModule } from '@angular/material/icon'
import { Matiere } from '../../model/matiere';

export const myCustomTooltipDefaults: MatTooltipDefaultOptions = {
  showDelay: 1000,
  hideDelay: 1000,
  touchendHideDelay: 1000,
};

@Component({
  selector: 'app-resultat-matiere',
  standalone: true,
  imports: [MaterialModule, HttpClientModule, RouterLink],
  providers: [MatiereService, { provide: MAT_TOOLTIP_DEFAULT_OPTIONS, useValue: myCustomTooltipDefaults }],
  templateUrl: './resultat-matiere.component.html',
  styleUrl: './resultat-matiere.component.css'
})
export class ResultatMatiereComponent {

  constructor(
    private router: Router,
    private matiereService: MatiereService,
    private modificationService: ModificationService
  ) { }

  displayedColumns: string[] = ['id', 'nom', 'prenom', 'classe', 'photo'];
  dataSource = new MatTableDataSource<Matiere>();

  ajouterMatiere(): void {

  }

  modifierMatiere(): void {

  }

  supprimerMatiere(): void {

  }
}
