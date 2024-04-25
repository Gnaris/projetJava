import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../shared/material-module';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ModificationService } from '../../service/modification.service';
import { Matiere } from '../../model/matiere';
import { MatiereService } from '../../service/matiere.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-detail-matiere',
  standalone: true,
  imports: [MaterialModule
  ],
  providers: [MatiereService],
  templateUrl: './detail-matiere.component.html',
  styleUrl: './detail-matiere.component.css'
})
export class DetailMatiereComponent implements OnInit {
  matiere!: Matiere;

  matiereFormGroup!: FormGroup;
  nomCtrlForm!: FormControl;
  private subscriptionMatiereAModifier! : Subscription

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private matiereService: MatiereService,
    private modificationService: ModificationService
  ) { }

  message !: string;

  ngOnInit(): void {
    this.initLaMatiere()
    this.initFormControl();
  }

  ngOnDestroy(): void {
    this.subscriptionMatiereAModifier.unsubscribe()
    this.modificationService.reinitialiserObjetAModifier()
  }

  private initFormControl(): void {
    this.nomCtrlForm = this.formBuilder.control(
      this.matiere.name,
      [Validators.required, Validators.maxLength(100)]
    );
    this.matiereFormGroup = this.formBuilder.group({
      name : this.nomCtrlForm
    })
  }

  private initLaMatiere(): void {
    this.subscriptionMatiereAModifier = this.modificationService.objet$.subscribe(
      (matiereAModifier: Matiere) => {
        this.matiere = matiereAModifier;
      });
  }

  public getMessageErreur(ctrl: AbstractControl) {
    if (!ctrl) {
      return;
    }
    if (ctrl.hasError('required')) {
      return 'Champs obligatoire';
    } else if (ctrl.hasError('maxlength')) {
      return (
        'Champs de caractère maximum ' +
        ctrl.getError('maxlength').requiredLength
      );
    }
    return;
  }


  public enregistrer(): void {
    console.log(this.matiere)
    this.matiereService.enregistrerMatiere(this.matiere).subscribe({
      next: value => {
        this.router.navigateByUrl('matieres');
      },
      error: err => console.error(err)
    });
  }

}
