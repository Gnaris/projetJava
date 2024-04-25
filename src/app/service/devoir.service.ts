import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { ResponseData } from '../model/ResponseData';
import { Devoir } from '../model/devoirs';

/**
 * Le service pour connecter aux APIs de gestion des Devoirs.
 */
@Injectable({
  providedIn: 'root'
})
export class DevoirService {

  constructor(private http: HttpClient) { }

  rechercherDevoirs() : Observable<ResponseData<Devoir[]>> {
    return this.http.get(environment.rechercherDevoirs) as Observable<ResponseData<Devoir[]>>;
  }

  supprimerDevoir(id : number) : Observable<ResponseData<string>>
  {
    return this.http.post(environment.supprimerDevoir, {id}) as Observable<ResponseData<string>>
  }

  enregisterDevoir(devoir : Devoir) : Observable<ResponseData<Devoir>> {
    return this.http.post(environment.enregistrerDevoir, devoir) as Observable<ResponseData<Devoir>>
  }
}
