import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Classe, CreateOrUpdateClasse } from '../model/classe';
import { ResponseData } from '../model/ResponseData';

/**
 * Le service pour connecter aux APIs de gestion des classes.
 */
@Injectable({
  providedIn: 'root'
})
export class ClasseService {

  constructor(private http: HttpClient) { }

  rechercherClasses() {
    return this.http.get(environment.rechercherClasses) as Observable<ResponseData<Classe[]>>;
  }

  supprimerClasse(classeId: number
  ): Observable<HttpResponse<string>> {
    return this.http.post(environment.supprimerClasse, { id: classeId }) as Observable<HttpResponse<string>>;
  }


  enregistrerClasse(classe: Classe
  ): Observable<CreateOrUpdateClasse> {
    if (classe.id && classe.name) {
      const updateClasse: CreateOrUpdateClasse = { id: classe.id, name: classe.name, students: classe.students.map(s => s.id) }
      return this.http.put(environment.updateClasse, updateClasse) as Observable<CreateOrUpdateClasse>
    } else {
      const createClasse: CreateOrUpdateClasse = { name: classe.name, students: classe.students.map(s => s.id) }
      return this.http.post(environment.enregistrerClasse, createClasse) as Observable<CreateOrUpdateClasse>;
    }
  }
}
