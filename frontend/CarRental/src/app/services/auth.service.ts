import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of, tap } from 'rxjs';
import { UserRole } from '../users/role-enum';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loggedIn$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(
    false
  );
  private roleLogged$: BehaviorSubject<UserRole | null> =
    new BehaviorSubject<UserRole | null>(null);

  get isLoggedIn$(): Observable<boolean> {
    return this.loggedIn$;
  }

  get roleLoggedIn$(): Observable<UserRole | null> {
    return this.roleLogged$;
  }

  constructor(private http: HttpClient) {}

  login(credentials: { password: string; username: string }): Observable<any> {
    return this.http
      .post(`http://localhost:8080/authenticate`, credentials, {
        responseType: 'text',
      })
      .pipe(
        tap((response) => {
          this.saveToken(response);
          this.loggedIn$.next(true);
        })
      );
  }

  register(user: any): Observable<any> {
    return of(undefined);
    // return this.http.post(`${this.apiURL}/register`, user);
  }

  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
    this.loggedIn$.next(false);
  }

  setLoggedIn(value: boolean): void {
    this.loggedIn$.next(value);
  }
}
