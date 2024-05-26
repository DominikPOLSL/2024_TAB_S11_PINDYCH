import { Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { RentalsComponent } from './rentals/rentals.component';
import { authGuard } from '../services/auth-guard';

export const employeeRoutes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'rezerwacje',
  },
  {
    path: 'dodaj-rezerwacje',
    component: AddReservationComponent,
    canActivate: [authGuard],
  },
  {
    path: 'rezerwacje',
    component: ReservationsComponent,
    canActivate: [authGuard],
  },
  {
    path: 'wypozyczenia',
    component: RentalsComponent,
    canActivate: [authGuard],
  },
];
