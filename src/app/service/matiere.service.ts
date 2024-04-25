import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ResponseData } from "../model/ResponseData";
import { Matiere } from "../model/matiere";
import { HttpClient } from "@angular/common/http";
import { environment } from "../environments/environment";

@Injectable({
    providedIn: "root"
})
export class MatiereService {

    constructor(private http : HttpClient){}

    recupererMatieres() : Observable<ResponseData<Matiere[]>> {
        return this.http.get(environment.rechercherMatieres) as Observable<ResponseData<Matiere[]>>
    }

    enregistrerMatiere(matiere : Matiere) : Observable<ResponseData<Matiere[]>>{
        if(matiere.id)
        {
            return this.http.put(environment.updateMatiere, matiere) as Observable<ResponseData<Matiere[]>>
        }else{
            return this.http.post(environment.enregistrerMatiere, matiere) as Observable<ResponseData<Matiere[]>>
        }
    }

    supprimerMatiere(id : number) : Observable<ResponseData<string>>{
        return this.http.post(environment.supprimerMatiere, {id}) as Observable<ResponseData<string>>
    }
}