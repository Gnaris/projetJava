import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Classe, UpdateClasse } from '../model/classe';
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
    let paramList: HttpParams = new HttpParams();
    paramList = paramList.set('id', classeId);
    return this.http.delete(environment.supprimerClasse, {
      params: paramList,
    }) as Observable<HttpResponse<string>>;
  }


  enregistrerClasse(classe: Classe
  ): Observable<Classe> {
    if(classe.id && classe.name)
    {
      const updateClasse : UpdateClasse = {id : classe.id, name : classe.name, students : (classe.students ? classe.students.map(s => (s.id ? s.id : 0)) : [])}
    }
    return this.http.post(environment.enregistrerClasse, classe) as Observable<Classe>;
  }
}
