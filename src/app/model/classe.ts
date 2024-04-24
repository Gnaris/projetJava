import { Etudiant } from "./etudiant";

export interface Classe {
  id: number;
  name: string;
  students: Etudiant[];
}

export interface CreateOrUpdateClasse {
  id?: number,
  name: string,
  students: number[]
}
