import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Livre } from '../model/livre.model';
import { Auteur } from '../model/auteur.model';
import { AuteurWrapped } from '../model/auteur-wrapped.model';
import { AuthService } from './auth.service';
import { environment } from '../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class LivreService {
  apiURL: string = environment.apiURL;
  apiURLAut: string = 'http://localhost:8090/livres/aut';

  constructor(private http: HttpClient, private authService: AuthService) { }

  private getAuthHeaders(): HttpHeaders {
    return this.authService.getAuthHeaders();
  }

  private getHttpOptions() {
    return { headers: this.getAuthHeaders() };
  }

  // ========== MÉTHODES POUR LES LIVRES ==========

  listeLivres(): Observable<Livre[]> {
    return this.http.get<Livre[]>(this.apiURL, this.getHttpOptions());
  }

  ajouterLivre(livre: Livre): Observable<Livre> {
    return this.http.post<Livre>(this.apiURL, livre, this.getHttpOptions());
  }

  supprimerLivre(id: number): Observable<void> {
    const url = `${this.apiURL}/${id}`;
    return this.http.delete<void>(url, this.getHttpOptions());
  }

  consulterLivre(id: number): Observable<Livre> {
    const url = `${this.apiURL}/${id}`;
    return this.http.get<Livre>(url, this.getHttpOptions());
  }

  updateLivre(livre: Livre): Observable<Livre> {
    return this.http.put<Livre>(this.apiURL, livre, this.getHttpOptions());
  }

  // ========== MÉTHODES POUR LES AUTEURS ==========

  listeAuteurs(): Observable<AuteurWrapped> {
    return this.http.get<AuteurWrapped>(this.apiURLAut, this.getHttpOptions());
  }

  consulterAuteur(id: number): Observable<Auteur> {
    const url = `${this.apiURLAut}/${id}`;
    return this.http.get<Auteur>(url, this.getHttpOptions());
  }

  // ========== MÉTHODES DE RECHERCHE ==========

  rechercherParAuteur(idAut: number): Observable<Livre[]> {
    const url = `${this.apiURL}/livresaut/${idAut}`;
    return this.http.get<Livre[]>(url, this.getHttpOptions());
  }

  rechercherParNom(nom: string): Observable<Livre[]> {
    const url = `${this.apiURL}/livresByName/${nom}`;
    return this.http.get<Livre[]>(url, this.getHttpOptions());
  }
  ajouterAuteur(auteur: Auteur): Observable<Auteur> {
    const url = 'http://localhost:8090/livres/api/aut';
    return this.http.post<Auteur>(url, auteur, this.getHttpOptions());
  }

  updateAuteur(auteur: Auteur): Observable<Auteur> {
    const url = 'http://localhost:8090/livres/api/aut';
    return this.http.put<Auteur>(url, auteur, this.getHttpOptions());
  }

  supprimerAuteur(id: number): Observable<void> {
    const url = `http://localhost:8090/livres/api/aut/${id}`;
    return this.http.delete<void>(url, this.getHttpOptions());
  }
}
