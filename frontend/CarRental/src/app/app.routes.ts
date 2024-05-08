import { Routes } from '@angular/router';

export const routes: Routes = [
  //   {
  //     path: '',
  //     redirectTo: 'profil?¿',
  //     pathMatch: 'full',
  //   },
  {
    path: 'pojazdy/dodaj',
    loadComponent: () =>
      import('./vehicles/add-vehicle/add-vehicle.component').then(
        (m) => m.AddVehicleComponent
      ),
  },
  {
    path: 'pojazdy/przegladaj',
    loadComponent: () =>
      import('./vehicles/browse-vehicles/browse-vehicles.component').then(
        (m) => m.BrowseVehiclesComponent
      ),
  },
  {
    path: 'pojazdy/przegladaj',
    loadComponent: () =>
      import('./vehicles/edit-vehicles/edit-vehicles.component').then(
        (m) => m.EditVehiclesComponent
      ),
  },
  //   {
  //     path: '**',
  //     component: komponentprofilu,
  //   },
];
