import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { UserRole } from '../users/role-enum';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const role = localStorage.getItem('role');

  if (authService.getToken()) {
    authService.setLoggedIn(true);
    if (role) {
      authService.setRole(role as UserRole);
    }
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
