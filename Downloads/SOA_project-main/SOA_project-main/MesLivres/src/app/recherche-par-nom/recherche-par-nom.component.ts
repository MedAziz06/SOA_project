import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Livre } from '../model/livre.model';
import { LivreService } from '../services/livre.service';
import { SearchFilterPipe } from '../search-filter.pipe';

@Component({
  selector: 'app-recherche-par-nom',
  standalone: true,
  imports: [FormsModule, CommonModule, SearchFilterPipe],
  templateUrl: './recherche-par-nom.component.html',
  styles: ``
})
export class RechercheParNomComponent implements OnInit {
  nomLivre!: string;
  livres!: Livre[];
  searchTerm: string = '';

  constructor(private livreService: LivreService) { }

  ngOnInit(): void {
    this.livreService.listeLivres().subscribe(livres => {
      console.log('✅ Tous les livres:', livres);
      this.livres = livres;
    });
  }

  rechercherLivres() {
    if (this.nomLivre) {
      this.livreService.rechercherParNom(this.nomLivre).subscribe(livres => {
        console.log('✅ Livres trouvés:', livres);
        this.livres = livres;
      });
    } else {
      this.livreService.listeLivres().subscribe(livres => {
        console.log('✅ Tous les livres:', livres);
        this.livres = livres;
      });
    }
  }
}
