import { Classe } from './classe';
export interface Etudiant {
  id?: number;
  firstname?: string;
  lastname?: string;
  photo?: string;
  classe_id?: number;
  nomClasse?: string;
}