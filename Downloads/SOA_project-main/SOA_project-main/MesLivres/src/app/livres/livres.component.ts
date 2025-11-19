import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Livre } from '../model/livre.model';
import { LivreService } from '../services/livre.service';
import { AuthService } from '../services/auth.service'; // ← AJOUTEZ CETTE LIGNE

@Component({
  selector: 'app-livres',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './livres.component.html',
  styleUrl: './livres.component.css'
})
export class LivresComponent implements OnInit {
  livres: Livre[] = [];

  constructor(
    private livreService: LivreService,
    public authService: AuthService
  ) { }

  ngOnInit(): void {
    this.chargerLivres();
  }

  chargerLivres() {
    this.livreService.listeLivres().subscribe({
      next: (livres) => {
        console.log('✅ Livres récupérés:', livres);
        this.livres = livres;
      },
      error: (err) => {
        console.error('❌ Erreur lors du chargement:', err);
      }
    });
  }

  supprimerLivre(l: Livre) {
    let conf = confirm("Êtes-vous sûr de vouloir supprimer ce livre ?");
    if (conf) {
      this.livreService.supprimerLivre(l.idLivre!).subscribe({
        next: () => {
          console.log("✅ Livre supprimé");
          this.chargerLivres();
        },
        error: (err) => {
          console.error("❌ Erreur suppression:", err);
        }
      });
    }
  }
}
