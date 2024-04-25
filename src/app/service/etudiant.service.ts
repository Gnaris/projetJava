import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateOrUpdateEtudiant, Etudiant } from '../model/etudiant';
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
  ): Observable<CreateOrUpdateEtudiant> {
    let currentEtudiant : CreateOrUpdateEtudiant = {firstname : etudiant.firstname, lastname : etudiant.lastname, photo : etudiant.photo, classe_id : etudiant.classe_id}
    if(etudiant.id)
    {
      currentEtudiant.id = etudiant.id
      return this.http.put(environment.updateEtudiant, currentEtudiant) as Observable<CreateOrUpdateEtudiant>
    }else{
      return this.http.post(environment.enregistrerEtudiant, currentEtudiant) as Observable<CreateOrUpdateEtudiant>;
    }
  }

  supprimerEtudiant(etudiantId: number
  ): Observable<ResponseData<string>> {
    return this.http.post(environment.supprimerEtudiant, { id: etudiantId, }) as Observable<ResponseData<string>>;
  }
}
