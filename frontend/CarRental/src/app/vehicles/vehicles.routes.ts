import { Routes } from '@angular/router';
import { AddVehicleComponent } from './add-vehicle/add-vehicle.component';
import { BrowseVehiclesComponent } from './browse-vehicles/browse-vehicles.component';
import { EditVehicleComponent } from './edit-vehicle/edit-vehicle.component';

export const vehicleRoutes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'przegladaj',
  },
  {
    path: 'przegladaj',
    component: BrowseVehiclesComponent,
  },
  {
    path: 'dodaj',
    component: AddVehicleComponent,
  },
  {
    path: 'edytuj/:id',
    component: EditVehicleComponent,
  },
];
