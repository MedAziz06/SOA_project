import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Auteur } from '../model/auteur.model';
import { LivreService } from '../services/livre.service';
import { UpdateAuteurComponent } from '../update-auteur/update-auteur.component';

@Component({
  selector: 'app-liste-auteurs',
  standalone: true,
  imports: [CommonModule, UpdateAuteurComponent],
  templateUrl: './liste-auteurs.component.html',
  styles: ``
})
export class ListeAuteursComponent implements OnInit {
  auteurs!: Auteur[];
  updatedAut: Auteur = {
    idAuteur: 0,
    nomAuteur: "",
    prenomAuteur: "",
    nationalite: ""
  };
  ajout: boolean = true;

  constructor(private livreService: LivreService) { }

  ngOnInit(): void {
    this.chargerAuteurs();
  }

  chargerAuteurs() {
    this.livreService.listeAuteurs().subscribe({
      next: (auteursWrapped) => {
        this.auteurs = auteursWrapped._embedded.auteurs;
        console.log('✅ Auteurs chargés:', this.auteurs);
      },
      error: (err) => {
        console.error('❌ Erreur chargement auteurs:', err);
      }
    });
  }

  auteurUpdated(aut: Auteur) {
    console.log('📥 Événement auteurUpdated reçu');
    console.log('📝 Auteur reçu:', aut);
    console.log('🔄 Mode:', this.ajout ? 'AJOUT' : 'MODIFICATION');

    if (this.ajout) {
      // Mode AJOUT
      console.log('➕ Appel de ajouterAuteur()');
      this.livreService.ajouterAuteur(aut).subscribe({
        next: (result) => {
          console.log('✅ Auteur ajouté avec succès:', result);
          this.chargerAuteurs();
          this.resetForm();
        },
        error: (err) => {
          console.error('❌ Erreur lors de l\'ajout:', err);
          alert('Erreur lors de l\'ajout : ' + err.message);
        }
      });
    } else {
      // Mode MODIFICATION
      console.log('✏️ Appel de updateAuteur()');
      this.livreService.updateAuteur(aut).subscribe({
        next: (result) => {
          console.log('✅ Auteur modifié avec succès:', result);
          this.chargerAuteurs();
          this.resetForm();
        },
        error: (err) => {
          console.error('❌ Erreur lors de la modification:', err);
          alert('Erreur lors de la modification : ' + err.message);
        }
      });
    }
  }

  updateAut(aut: Auteur) {
    console.log('✏️ Edition de l\'auteur:', aut);
    this.updatedAut = { ...aut }; // Clone
    this.ajout = false;
  }

  supprimerAuteur(aut: Auteur) {
    let conf = confirm(`Êtes-vous sûr de vouloir supprimer ${aut.prenomAuteur} ${aut.nomAuteur} ?`);
    if (conf) {
      this.livreService.supprimerAuteur(aut.idAuteur!).subscribe({
        next: () => {
          console.log("✅ Auteur supprimé");
          this.chargerAuteurs();
        },
        error: (err) => {
          console.error("❌ Erreur suppression:", err);
        }
      });
    }
  }

  resetForm() {
    this.updatedAut = {
      idAuteur: 0,
      nomAuteur: "",
      prenomAuteur: "",
      nationalite: ""
    };
    this.ajout = true;
  }
}
