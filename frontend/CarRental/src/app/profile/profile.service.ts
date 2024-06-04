import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, delay, tap } from 'rxjs';
import { LoggedUser } from './logged-user.interface';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  constructor(private http: HttpClient) {}

  getProfileDataById(
    loggedUserId: string | null,
    role: string
  ): Observable<LoggedUser> {
    const mappedRole = this.mapRole(role);

    return this.http
      .get<LoggedUser>(
        `http://localhost:8080/api/${mappedRole}/${loggedUserId}`
      )
      .pipe(delay(1000));
  }

  editProfile(user: any, loggedUserId: string | null, role: string) {
    const mappedRole = this.mapRole(role);
    return this.http
      .put(`http://localhost:8080/api/${mappedRole}/${loggedUserId}`, user)
      .pipe(delay(1000));
  }

  mapRole(role: string) {
    if (role == 'ROLE_EMPLOYEE') {
      return 'employee';
    } else if (role == 'ROLE_ADMIN') {
      return 'admin';
    } else {
      return 'keeper';
    }
  }

  deleteProfile() {}
}
