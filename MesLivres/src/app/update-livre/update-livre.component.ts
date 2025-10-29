import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Livre } from '../model/livre.model';
import { Auteur } from '../model/auteur.model';
import { LivreService } from '../services/livre.service';

@Component({
  selector: 'app-update-livre',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './update-livre.component.html',
  styleUrl: './update-livre.component.css'
})
export class UpdateLivreComponent implements OnInit {
  currentLivre = new Livre();
  auteurs: Auteur[] = [];
  updatedAutId!: number;
  message: string = '';
  messageError: string = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private livreService: LivreService
  ) { }

  ngOnInit(): void {
    // Charger les auteurs via Spring Data REST
    this.livreService.listeAuteurs().subscribe(auteursWrapped => {
      console.log('📦 Données Spring Data REST:', auteursWrapped);
      this.auteurs = auteursWrapped._embedded.auteurs;
      console.log('✅ Auteurs extraits:', this.auteurs);
    });

    // Charger le livre à modifier
    this.livreService.consulterLivre(this.activatedRoute.snapshot.params['id'])
      .subscribe(livre => {
        this.currentLivre = livre;
        this.updatedAutId = this.currentLivre.auteur.idAuteur;
        console.log('✅ Livre chargé:', livre);
      });
  }

  updateLivre() {
    // Validation du prix
    if (!this.currentLivre.prixLivre || this.currentLivre.prixLivre <= 0) {
      this.messageError = "Le prix ne peut pas être négatif ou nul !";
      setTimeout(() => {
        this.messageError = '';
      }, 3000);
      return;
    }

    // Mettre à jour l'auteur
    this.currentLivre.auteur = this.auteurs.find(aut => aut.idAuteur == this.updatedAutId)!;

    // Appel API pour modifier le livre
    this.livreService.updateLivre(this.currentLivre).subscribe({
      next: (livre) => {
        console.log('✅ Livre modifié:', livre);
        this.message = "Livre modifié avec succès !";
        setTimeout(() => {
          this.router.navigate(['livres']);
        }, 1500);
      },
      error: (err) => {
        console.error('❌ Erreur:', err);
        this.messageError = "Erreur lors de la modification du livre !";
      }
    });
  }
}

