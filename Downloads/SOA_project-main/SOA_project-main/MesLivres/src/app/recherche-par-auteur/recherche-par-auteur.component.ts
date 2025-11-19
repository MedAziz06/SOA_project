import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Livre } from '../model/livre.model';
import { Auteur } from '../model/auteur.model';
import { LivreService } from '../services/livre.service';

@Component({
  selector: 'app-recherche-par-auteur',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './recherche-par-auteur.component.html',
  styles: ``
})
export class RechercheParAuteurComponent implements OnInit {
  livres!: Livre[];
  IdAuteur!: number;
  auteurs!: Auteur[];

  constructor(private livreService: LivreService) { }

  ngOnInit(): void {
    this.livreService.listeAuteurs().subscribe(auteursWrapped => {
      this.auteurs = auteursWrapped._embedded.auteurs;
      console.log('✅ Auteurs chargés:', this.auteurs);
    });
  }

  onChange() {
    this.livreService.rechercherParAuteur(this.IdAuteur).subscribe(livres => {
      this.livres = livres;
      console.log('✅ Livres trouvés:', livres);
    });
  }
}
