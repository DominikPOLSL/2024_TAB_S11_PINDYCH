import { Injectable } from '@angular/core';
import { Observable, delay, map, of, switchMap, tap } from 'rxjs';
import { users } from './mock-users-data';
import { User } from './user-interface';
import { UserRole } from './role-enum';
import { HttpClient } from '@angular/common/http';
import { mapUser, mapUserDTO, mapUsers } from './users.mapper';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private userId!: number;

  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('http://localhost:8080/api/employee').pipe(
      delay(1000),
      map((users) => mapUsers(users))
    );
  }

  getUser(id: string): Observable<User> {
    return this.http.get<User>(`http://localhost:8080/api/employee/${id}`).pipe(
      delay(1000),
      map((user) => mapUser(user)),
      tap(() => (this.userId = +id))
    );
  }

  deleteUser(): Observable<User> {
    return this.http.delete<User>(
      `http://localhost:8080/api/employee/${this.userId}`
    );
  }

  addUser(user: User): Observable<User> {
    const params: any = mapUserDTO(user);

    return this.http
      .post<User>(`http://localhost:8080/api/employee`, params)
      .pipe(delay(1000));
  }

  editUser() {
    //  TODO
  }
}
