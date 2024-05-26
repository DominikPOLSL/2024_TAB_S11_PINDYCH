import { Routes } from '@angular/router';
import { BrowseUsersComponent } from './browse-users/browse-users.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { AddUserComponent } from './add-user/add-user.component';
import { authGuard } from '../services/auth-guard';

export const usersRoutes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'przegladaj',
  },
  {
    path: 'przegladaj',
    component: BrowseUsersComponent,
    canActivate: [authGuard],
  },
  {
    path: 'dodaj',
    component: AddUserComponent,
    canActivate: [authGuard],
  },
  {
    path: 'edytuj/:id',
    component: EditUserComponent,
    canActivate: [authGuard],
  },
];
