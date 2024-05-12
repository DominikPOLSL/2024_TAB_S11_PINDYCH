import { Injectable } from '@angular/core';
import { Observable, delay, map, of, switchMap, tap } from 'rxjs';
import { users } from './mock-users-data';
import { User, UserDTO } from './user-interface';
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
    return this.http.get<UserDTO[]>('http://localhost:8080/api/employee').pipe(
      delay(1000),
      map((users) => mapUsers(users))
    );
  }

  getUser(id: string): Observable<User> {
    return this.http.get<UserDTO>(`http://localhost:8080/api/employee/${id}`).pipe(
      delay(1000),
      map((user) => mapUser(user)),
      tap(() => (this.userId = +id))
    );
  }

  deleteUser(): Observable<UserDTO> {
    return this.http.delete<UserDTO>(
      `http://localhost:8080/api/employee/${this.userId}`
    );
  }

  addUser(user: User): Observable<UserDTO> {
    const params: UserDTO = mapUserDTO(user);

    return this.http
      .post<UserDTO>(`http://localhost:8080/api/employee`, params)
      .pipe(delay(1000));
  }

  editUser() {
    //  TODO
  }
}
