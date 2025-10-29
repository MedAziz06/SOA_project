import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Auteur } from '../model/auteur.model';

@Component({
  selector: 'app-update-auteur',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './update-auteur.component.html',
  styles: ``
})
export class UpdateAuteurComponent implements OnInit {

  // Données reçues du composant parent
  @Input()
  auteur!: Auteur;

  @Input()
  ajout!: boolean;

  // Événement émis vers le composant parent
  @Output()
  auteurUpdated = new EventEmitter<Auteur>();

  constructor() { }

  ngOnInit(): void {
    console.log('🔧 ngOnInit du composant UpdateAuteur');
    console.log('📦 Auteur reçu:', this.auteur);
    console.log('➕ Mode ajout:', this.ajout);
  }

  saveAuteur() {
    console.log('💾 Sauvegarde de l\'auteur:', this.auteur);

    // Validation basique
    if (!this.auteur.nomAuteur || !this.auteur.prenomAuteur) {
      alert('Le nom et le prénom sont obligatoires !');
      return;
    }

    // Émettre l'événement vers le parent
    this.auteurUpdated.emit(this.auteur);
  }
}
