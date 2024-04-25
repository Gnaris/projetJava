import { Routes } from '@angular/router';
import { AccueilComponent } from './component/accueil/accueil.component';
import { ResultatClasseComponent } from './component/resultat-classe/resultat-classe.component';
import { ResultatEtudiantComponent } from './component/resultat-etudiant/resultat-etudiant.component';
import { DetailEtudiantComponent } from './component/detail-etudiant/detail-etudiant.component';
import { DetailClasseComponent } from './component/detail-classe/detail-classe.component';
import { ResultatMatiereComponent } from './component/resultat-matiere/resultat-matiere.component';
import { DetailMatiereComponent } from './component/detail-matiere/detail-matiere.component';
import { ResultatDevoirsComponent } from './component/resultat-devoir/resultat-devoir.component';
import { DetailDevoirComponent } from './component/detail-devoir/detail-devoir.component';

export const routes: Routes = [
  { path: '', component: AccueilComponent, },
  { path: 'classes', component: ResultatClasseComponent },
  { path: 'etudiants', component: ResultatEtudiantComponent },
  { path: 'matieres', component: ResultatMatiereComponent },
  { path: 'devoirs', component: ResultatDevoirsComponent },
  { path: 'detail-classe', component: DetailClasseComponent },
  { path: 'detail-etudiant', component: DetailEtudiantComponent },
  {path : 'detail-matiere', component : DetailMatiereComponent },
  {path : 'detail-devoir', component : DetailDevoirComponent },
];
