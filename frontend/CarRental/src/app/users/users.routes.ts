import { Routes } from '@angular/router';
import { BrowseUsersComponent } from './browse-users/browse-users.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { AddUserComponent } from './add-user/add-user.component';

export const usersRoutes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'przegladaj',
  },
  {
    path: 'przegladaj',
    component: BrowseUsersComponent,
  },
  {
    path: 'dodaj',
    component: AddUserComponent,
  },
  {
    path: 'edytuj/:id',
    component: EditUserComponent,
  },
];
