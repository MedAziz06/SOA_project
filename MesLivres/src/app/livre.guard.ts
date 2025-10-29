import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './services/auth.service';

export const livreGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isAdmin()) {
    console.log('✅ Accès autorisé (Admin)');
    return true;
  } else {
    console.log('❌ Accès refusé (Non-Admin)');
    router.navigate(['app-forbidden']);
    return false;
  }
};
