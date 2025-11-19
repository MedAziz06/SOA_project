import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styles: ``
})
export class LoginComponent {
  user = new User();
  erreur = 0;

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  onLoggedin() {
    console.log('🔐 Tentative de connexion:', this.user);

    if (!this.user.username || !this.user.password) {
      console.log('❌ Veuillez remplir tous les champs');
      this.erreur = 1;
      return;
    }

    this.authService.login({
      username: this.user.username,
      password: this.user.password
    }).subscribe({
      next: (response) => {
        console.log('✅ Connexion réussie', response);
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.log('❌ Échec de connexion', error);
        if (error.status === 400) {
          console.log('🔍 Erreur 400: Requête invalide - vérifiez les données envoyées');
        } else if (error.status === 401) {
          console.log('🔍 Erreur 401: Identifiants incorrects');
        } else if (error.status === 403) {
          console.log('🔍 Erreur 403: Accès refusé');
        } else if (error.status === 0) {
          console.log('🔍 Erreur réseau: serveur inaccessible');
        }
        this.erreur = 1;
      }
    });
  }
}
