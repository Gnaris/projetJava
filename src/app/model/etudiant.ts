export interface Etudiant {
  id: number;
  firstname: string;
  lastname: string;
  photo?: string;
  classe_id?: number;
}

export interface CreateOrUpdateEtudiant{
  id? : number,
  firstname : string,
  lastname : string,
  photo? : string;
  classe_id? : number;
}