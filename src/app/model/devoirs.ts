import { Classe } from "./classe"
import { Matiere } from "./matiere"

export interface Devoir {
    id? : number
    coefficient : number,
    date : string,
    type : string,
    classe : Classe,
    subject : Matiere
}