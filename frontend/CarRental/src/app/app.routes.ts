import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { authGuard } from './services/auth-guard';

export const routes: Routes = [
  //   {
  //     path: '',
  //     redirectTo: 'profil?¿',
  //     pathMatch: 'full',
  //   },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'uzytkownik',
    loadChildren: () =>
      import('./employee/employee.routes').then((m) => m.employeeRoutes),
    canActivate: [authGuard],
  },
  {
    path: 'profil',
    loadChildren: () =>
      import('./profile/profile.routes').then((m) => m.profileRoutes),
    canActivate: [authGuard],
  },
  {
    path: 'pojazdy',
    loadChildren: () =>
      import('./vehicles/vehicles.routes').then((m) => m.vehicleRoutes),
    canActivate: [authGuard],
  },
  {
    path: 'uzytkownicy',
    loadChildren: () =>
      import('./users/users.routes').then((m) => m.usersRoutes),
    canActivate: [authGuard],
  },
];
