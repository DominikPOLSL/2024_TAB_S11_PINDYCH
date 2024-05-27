import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  return true;

  // if (authService.getToken()) {
  //   return true;
  // } else {
  //   router.navigate(['/login']);
  //   return false;
  // }
};
