import { Routes } from '@angular/router';

export const routes: Routes = [
  //   {
  //     path: '',
  //     redirectTo: 'profil?¿',
  //     pathMatch: 'full',
  //   },
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
