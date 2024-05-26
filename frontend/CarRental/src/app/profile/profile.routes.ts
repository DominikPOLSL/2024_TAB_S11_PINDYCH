import { Routes } from '@angular/router';
import { ProfileComponent } from './profile.component';
import { authGuard } from '../services/auth-guard';

export const profileRoutes: Routes = [
  {
    path: '',
    component: ProfileComponent,
    canActivate: [authGuard],
  },
];
