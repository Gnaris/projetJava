import { Etudiant } from "./etudiant";

export interface Classe {
  id?: number;
  name?: string;
  students?: Etudiant[];
}

export interface UpdateClasse {
  id : number,
  name : string,
  students : number[]
}
