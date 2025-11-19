import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Livre } from '../model/livre.model';
import { Auteur } from '../model/auteur.model';
import { LivreService } from '../services/livre.service';

@Component({
  selector: 'app-add-livre',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './add-livre.component.html',
  styleUrl: './add-livre.component.css'
})
export class AddLivreComponent implements OnInit {
  newLivre = new Livre();
  auteurs: Auteur[] = [];
  newIdAuteur!: number;
  message: string = '';
  messageError: string = '';

  constructor(
    private livreService: LivreService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // Charger les auteurs via Spring Data REST
    this.livreService.listeAuteurs().subscribe(auteursWrapped => {
      console.log('📦 Données Spring Data REST:', auteursWrapped);
      this.auteurs = auteursWrapped._embedded.auteurs;
      console.log('✅ Auteurs extraits:', this.auteurs);
    });
  }

  addLivre() {
    // Validation du prix
    if (!this.newLivre.prixLivre || this.newLivre.prixLivre <= 0) {
      this.messageError = "Le prix ne peut pas être négatif ou nul !";
      setTimeout(() => {
        this.messageError = '';
      }, 3000);
      return;
    }

    // Validation de l'auteur
    if (!this.newIdAuteur) {
      this.messageError = "Veuillez sélectionner un auteur !";
      setTimeout(() => {
        this.messageError = '';
      }, 3000);
      return;
    }

    // Trouver l'auteur sélectionné
    this.newLivre.auteur = this.auteurs.find(aut => aut.idAuteur == this.newIdAuteur)!;

    // Appel API pour ajouter le livre
    this.livreService.ajouterLivre(this.newLivre).subscribe({
      next: (livre) => {
        console.log('✅ Livre ajouté:', livre);
        this.message = "Livre ajouté avec succès !";
        setTimeout(() => {
          this.router.navigate(['livres']);
        }, 1500);
      },
      error: (err) => {
        console.error('❌ Erreur:', err);
        this.messageError = "Erreur lors de l'ajout du livre !";
      }
    });
  }
}
