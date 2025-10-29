import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Livre } from '../model/livre.model';
import { Auteur } from '../model/auteur.model';
import { AuteurWrapped } from '../model/auteur-wrapped.model';
import { environment } from '../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class LivreService {
  apiURL: string = environment.apiURL;
  apiURLAut: string = 'http://localhost:8080/livres/aut';

  constructor(private http: HttpClient) { }

  // ========== MÉTHODES POUR LES LIVRES ==========

  listeLivres(): Observable<Livre[]> {
    return this.http.get<Livre[]>(this.apiURL);
  }

  ajouterLivre(livre: Livre): Observable<Livre> {
    return this.http.post<Livre>(this.apiURL, livre, httpOptions);
  }

  supprimerLivre(id: number): Observable<void> {
    const url = `${this.apiURL}/${id}`;
    return this.http.delete<void>(url, httpOptions);
  }

  consulterLivre(id: number): Observable<Livre> {
    const url = `${this.apiURL}/${id}`;
    return this.http.get<Livre>(url);
  }

  updateLivre(livre: Livre): Observable<Livre> {
    return this.http.put<Livre>(this.apiURL, livre, httpOptions);
  }

  // ========== MÉTHODES POUR LES AUTEURS ==========

  listeAuteurs(): Observable<AuteurWrapped> {
    return this.http.get<AuteurWrapped>(this.apiURLAut);
  }

  consulterAuteur(id: number): Observable<Auteur> {
    const url = `${this.apiURLAut}/${id}`;
    return this.http.get<Auteur>(url);
  }

  // ========== MÉTHODES DE RECHERCHE ==========

  rechercherParAuteur(idAut: number): Observable<Livre[]> {
    const url = `${this.apiURL}/livresaut/${idAut}`;
    return this.http.get<Livre[]>(url);
  }

  rechercherParNom(nom: string): Observable<Livre[]> {
    const url = `${this.apiURL}/livresByName/${nom}`;
    return this.http.get<Livre[]>(url);
  }
  ajouterAuteur(auteur: Auteur): Observable<Auteur> {
    const url = 'http://localhost:8080/livres/api/aut';
    return this.http.post<Auteur>(url, auteur, httpOptions);
  }

  updateAuteur(auteur: Auteur): Observable<Auteur> {
    const url = 'http://localhost:8080/livres/api/aut';
    return this.http.put<Auteur>(url, auteur, httpOptions);
  }

  supprimerAuteur(id: number): Observable<void> {
    const url = `http://localhost:8080/livres/api/aut/${id}`;
    return this.http.delete<void>(url, httpOptions);
  }
}
