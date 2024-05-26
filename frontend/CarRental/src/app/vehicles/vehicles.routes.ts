import { Routes } from '@angular/router';
import { AddVehicleComponent } from './add-vehicle/add-vehicle.component';
import { BrowseVehiclesComponent } from './browse-vehicles/browse-vehicles.component';
import { EditVehicleComponent } from './edit-vehicle/edit-vehicle.component';
import { AddVehicleModelComponent } from './add-vehicle-model/add-vehicle-model.component';
import { authGuard } from '../services/auth-guard';

export const vehicleRoutes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'przegladaj',
  },
  {
    path: 'przegladaj',
    component: BrowseVehiclesComponent,
    canActivate: [authGuard],
  },
  {
    path: 'dodaj',
    component: AddVehicleComponent,
    canActivate: [authGuard],
  },
  {
    path: 'modele',
    component: AddVehicleModelComponent,
    canActivate: [authGuard],
  },
  {
    path: 'edytuj/:id',
    component: EditVehicleComponent,
    canActivate: [authGuard],
  },
];
