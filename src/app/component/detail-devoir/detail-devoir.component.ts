import { Component, OnInit, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DevoirService} from '../../service/devoir.service';
import { EtudiantService } from '../../service/etudiant.service';
import { Etudiant } from '../../model/etudiant';
import { Devoir } from '../../model/devoirs';
import { MaterialModule } from '../../shared/material-module';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { SelectionModel } from '@angular/cdk/collections';
import { ModificationService } from '../../service/modification.service';
import { Subscription } from 'rxjs';
import { Classe } from '../../model/classe';
import { ClasseService } from '../../service/classe.service';

@Component({
  selector: 'app-detail-devoir',
  standalone: true,
  imports: [MaterialModule],
  providers: [DevoirService, ClasseService],
  templateUrl: './detail-devoir.component.html',
  styleUrl: './detail-devoir.component.css'
})
export class DetailDevoirComponent implements OnInit {
  devoir!: Devoir;
  classeList: Classe[] = [];
  devoirFormGroup!: FormGroup;
  nomCtrlForm!: FormControl;
  displayedColumns: string[] = ['nom', 'prenom'];
  dataSourceEtudiants = new MatTableDataSource<Etudiant>();
  @ViewChild(MatPaginator) paginator: MatPaginator | null = null;
  selection = new SelectionModel<Etudiant>(true, []);
  private subscriptiondevoirAModifier!: Subscription;
  

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private devoirService: DevoirService,
    private classeService : ClasseService,
    private modificationService: ModificationService
  ) { }

  ngOnInit(): void {
    this.initDevoir();
    this.initClasseList();
    this.initFormControl();
  }

  ngOnDestroy() {
    this.subscriptiondevoirAModifier.unsubscribe();
  }

  /**
   * Cette méthode s'abonner au service de modification pour initialiser la devoir.
   */
  private initDevoir(): void {
    this.subscriptiondevoirAModifier = this.modificationService.objet$.subscribe(
      (devoirAModifier: Devoir) => {
        this.devoir = devoirAModifier;
      });
  }

  /**
   * Initialiser des controles sur le formulaire.
   */
  private initFormControl(): void {
    this.nomCtrlForm = this.formBuilder.control(
      this.devoir.type,
      [Validators.required, Validators.maxLength(100)]
    );
    this.devoirFormGroup = this.formBuilder.group({
      nom: this.nomCtrlForm
    });
  }
  /**
   * Si tous les checkbox sont sélectionnés.
   * @returns
   */
  isTousSelectionnes(): boolean {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSourceEtudiants.data.length;
    return numSelected === numRows;
  }

  private initClasseList(): void {
    this.classeService.rechercherClasses().subscribe({
      next: value => this.classeList = value.data,
      error: err => console.error(err)
    });
  }

  /**
   * action du bouton de "Sélectionner/déselectionner" tous les étudiants
   */
  selectionnerOuDeselectionnerTous(): void {
    this.isTousSelectionnes() ?
      this.selection.clear() :
      this.dataSourceEtudiants.data.forEach(row => this.selection.select(row));
  }

  /**
   * Enregistrer la devoir.
   */
  enregistrer(): void {
    if (this.devoirFormGroup.valid) {
      this.devoirService.enregisterDevoir(this.devoir).subscribe({
        next: value => {
          this.router.navigateByUrl('devoirs');
        },
        error: err => console.error(err)
      });
    }
  }
  /**
   * Récupérer le message d'erreur en fonction de validateur.
   * @param ctrl le formcontrol
   * @returns
   */
  getMessageErreur(ctrl: AbstractControl): string {
    if (!ctrl) {
      return "";
    }
    if (ctrl.hasError('required')) {
      return 'Champs obligatoire';
    } else if (ctrl.hasError('maxlength')) {
      return (
        'Champs de caractère maximum ' +
        ctrl.getError('maxlength').requiredLength
      );
    }
    return "";
  }


}
