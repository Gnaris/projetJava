import { Classe } from "./classe";

export interface Etudiant {
  id: number;
  firstname: string;
  lastname: string;
  photo?: string;
  classe?: string;
}