import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Etudiant } from '../model/etudiant';
import { environment } from '../environments/environment';
import { ResponseData } from '../model/ResponseData';


/**
 * Le service pour connecter aux APIs de gestion des classes.
 */
@Injectable({
  providedIn: 'root'
})
export class EtudiantService {

  constructor(private http: HttpClient) { }

  rechercherEtudiants(
  ): Observable<ResponseData<Etudiant[]>> {
    return this.http.get(environment.rechercherEtudiants) as Observable<ResponseData<Etudiant[]>>;
  }

  rechercherEtudiantsDisponibles(
  ): Observable<ResponseData<Etudiant[]>> {
    return this.http.get(environment.rechercherEtudiantsDisponibles) as Observable<ResponseData<Etudiant[]>>;
  }

  enregistrerEtudiant(etudiant: Etudiant
  ): Observable<Etudiant> {
    return this.http.post(environment.enregistrerEtudiant, etudiant) as Observable<Etudiant>;
  }

  supprimerEtudiant(etudiantId: number
  ): Observable<ResponseData<string>> {
    return this.http.post(environment.supprimerEtudiant, { id: etudiantId, }) as Observable<ResponseData<string>>;
  }
}
