import { Routes } from '@angular/router';
import { LivresComponent } from './livres/livres.component';
import { AddLivreComponent } from './add-livre/add-livre.component';
import { UpdateLivreComponent } from './update-livre/update-livre.component';
import { RechercheParAuteurComponent } from './recherche-par-auteur/recherche-par-auteur.component';
import { RechercheParNomComponent } from './recherche-par-nom/recherche-par-nom.component';
import { ListeAuteursComponent } from './liste-auteurs/liste-auteurs.component';
import { LoginComponent } from './login/login.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { livreGuard } from './livre.guard';
export const routes: Routes = [
  { path: "livres", component: LivresComponent },
  { path: "add-livre", component: AddLivreComponent, canActivate: [livreGuard] },
  { path: "updateLivre/:id", component: UpdateLivreComponent, canActivate: [livreGuard] },
  { path: "rechercheParAuteur", component: RechercheParAuteurComponent },
  { path: "rechercheParNom", component: RechercheParNomComponent },
  { path: "listeAuteurs", component: ListeAuteursComponent, canActivate: [livreGuard] },
  { path: "login", component: LoginComponent },
  { path: "app-forbidden", component: ForbiddenComponent },
  { path: "", redirectTo: "livres", pathMatch: "full" }
];
