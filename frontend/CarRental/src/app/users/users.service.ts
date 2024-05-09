import { Injectable } from '@angular/core';
import { delay, of } from 'rxjs';
import { users } from './mock-users-data';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  constructor() {}

  getUsers() {
    return of(users).pipe(delay(0));
  }
}
