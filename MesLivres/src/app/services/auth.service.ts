import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // Base de données d'utilisateurs (simulation)
  users: User[] = [
    { username: "admin", password: "123", roles: ['ADMIN'] },
    { username: "user", password: "123", roles: ['USER'] }
  ];

  public loggedUser!: string;
  public isloggedIn: Boolean = false;
  public roles!: string[];

  constructor(private router: Router) { }

  // Méthode de déconnexion
  logout() {
    this.isloggedIn = false;
    this.loggedUser = undefined!;
    this.roles = undefined!;
    localStorage.removeItem('loggedUser');
    localStorage.setItem('isloggedIn', String(this.isloggedIn));
    this.router.navigate(['/login']);
    console.log('👋 Déconnexion');
  }

  // Méthode de connexion
  SignIn(user: User): Boolean {
    let validUser: Boolean = false;

    this.users.forEach((curUser) => {
      if (user.username == curUser.username && user.password == curUser.password) {
        validUser = true;
        this.loggedUser = curUser.username;
        this.isloggedIn = true;
        this.roles = curUser.roles;

        // Sauvegarder dans LocalStorage
        localStorage.setItem('loggedUser', this.loggedUser);
        localStorage.setItem('isloggedIn', String(this.isloggedIn));

        console.log('✅ Utilisateur connecté:', this.loggedUser);
        console.log('🔑 Rôles:', this.roles);
      }
    });

    return validUser;
  }

  // Vérifier si l'utilisateur est Admin
  isAdmin(): Boolean {
    if (!this.roles) {
      return false;
    }
    return (this.roles.indexOf('ADMIN') > -1);
  }

  // Restaurer l'utilisateur depuis LocalStorage
  setLoggedUserFromLocalStorage(login: string) {
    this.loggedUser = login;
    this.isloggedIn = true;
    this.getUserRoles(login);
    console.log('🔄 Session restaurée pour:', login);
  }

  // Récupérer les rôles d'un utilisateur
  getUserRoles(username: string) {
    this.users.forEach((curUser) => {
      if (curUser.username == username) {
        this.roles = curUser.roles;
      }
    });
  }
}
