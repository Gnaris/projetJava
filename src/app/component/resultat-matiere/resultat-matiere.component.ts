import { Component } from '@angular/core';
import { MAT_TOOLTIP_DEFAULT_OPTIONS, MatTooltipDefaultOptions } from '@angular/material/tooltip';
import { MaterialModule } from '../../shared/material-module';
import { MatTableDataSource } from '@angular/material/table';
import { HttpClientModule } from '@angular/common/http';
import { RouterLink, Router } from '@angular/router';
import { ModificationService } from '../../service/modification.service';
import { MatiereService } from '../../service/matiere.service';
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

  displayedColumns: string[] = ['id', 'name'];
  dataSource = new MatTableDataSource<Matiere>();

  ngOnInit() : void {
    this.matiereService.recupererMatieres().subscribe({
      next : (value) => {
        if(value.success){
          this.dataSource.data = value.data
        }
      },
      error : err => console.log(err)
    })
  }

  ajouterMatiere(): void {
    this.router.navigateByUrl('detail-matiere')
  }

  modifierMatiere(matiere : Matiere): void {
      this.modificationService.envoyerObjetACreerOuModifier(matiere)
      this.router.navigateByUrl("detail-matiere")
  }

  supprimerMatiere(matiereId : number): void {
    this.matiereService.supprimerMatiere(matiereId).subscribe({
      next : value => {
        this.dataSource.data = this.dataSource.data.filter(m => m.id !== m.id)
      },
      error : err => console.log(err)
    })
  }
}
