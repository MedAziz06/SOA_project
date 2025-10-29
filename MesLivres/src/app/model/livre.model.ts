import { Auteur } from "./auteur.model";

export class Livre {
  idLivre?: number;
  nomLivre?: string;
  prixLivre?: number;
  dateCreation?: Date;
  auteur!: Auteur;
}
