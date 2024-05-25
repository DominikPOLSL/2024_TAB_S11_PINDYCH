import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';

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
    path: 'profil',
    loadChildren: () =>
      import('./profile/profile.routes').then((m) => m.profileRoutes),
  },
  {
    path: 'pojazdy',
    loadChildren: () =>
      import('./vehicles/vehicles.routes').then((m) => m.vehicleRoutes),
  },
  {
    path: 'uzytkownicy',
    loadChildren: () =>
      import('./users/users.routes').then((m) => m.usersRoutes),
  },

  // {
  //   path: '**',
  //   component: komponentprofilu,
  // },
];
