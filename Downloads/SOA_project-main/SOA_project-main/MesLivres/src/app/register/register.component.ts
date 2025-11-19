import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, RegisterRequest } from '../services/auth.service';
import { User } from '../model/user.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  user: User = new User();
  erreur: string|null = null;
  succes: string|null = null;
  enCours = false;

  constructor(private authService: AuthService, private router: Router) {}

  onRegister() {
    if (!this.user.username || !this.user.email || !this.user.password) {
      this.erreur = 'Tous les champs sont obligatoires.';
      return;
    }
    this.enCours = true;
    this.authService.register({
      username: this.user.username,
      email: this.user.email,
      password: this.user.password,
    } as RegisterRequest).subscribe({
      next: (reponse) => {
        this.succes = "Inscription réussie ! Connectez-vous.";
        this.erreur = null;
        setTimeout(() => this.router.navigate(['/login']), 1200);
      },
      error: (err) => {
        this.succes = null;
        if (err?.error?.message) {
          this.erreur = err.error.message;
        } else if (typeof err.error === 'string') {
          this.erreur = err.error;
        } else {
          this.erreur = "Erreur technique. Réessayez.";
        }
      },
      complete: () => {
        this.enCours = false;
      }
    });
  }
}
