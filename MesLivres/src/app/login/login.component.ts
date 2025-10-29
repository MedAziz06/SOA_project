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

    let isValidUser: Boolean = this.authService.SignIn(this.user);

    if (isValidUser) {
      console.log('✅ Connexion réussie');
      this.router.navigate(['/']);
    } else {
      console.log('❌ Échec de connexion');
      this.erreur = 1;
    }
  }
}
