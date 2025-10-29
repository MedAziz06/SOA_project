import { Auteur } from './auteur.model';

export class AuteurWrapped {
  _embedded!: {
    auteurs: Auteur[];
  };
}
