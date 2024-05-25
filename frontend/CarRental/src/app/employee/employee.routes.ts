import { Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { RentalsComponent } from './rentals/rentals.component';

export const employeeRoutes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'rezerwacje',
  },
  {
    path: 'dodaj-rezerwacje',
    component: AddReservationComponent,
  },
  {
    path: 'rezerwacje',
    component: ReservationsComponent,
  },
  {
    path: 'wypozyczenia',
    component: RentalsComponent,
  },
];
