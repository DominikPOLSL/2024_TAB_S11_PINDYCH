import { Injectable } from '@angular/core';
import { Observable, delay, of } from 'rxjs';
import { users } from './mock-users-data';
import { User } from './user-interface';
import { UserRole } from './role-enum';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  constructor() {}

  getUsers() {
    return of(users).pipe(delay(0));
  }

  getUser(id: string): Observable<User> {
    return of({
      id: id,
      name: 'jadwiga',
      surname: 'hymel',
      login: 'jadhym01',
      role: UserRole.ADMIN,
    }).pipe(delay(1000));
  }
}
