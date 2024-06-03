import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loggedIn$: BehaviorSubject<string> = new BehaviorSubject<string>(
    'Zaloguj'
  );

  get LoggedIn(): Observable<string> {
    return this.loggedIn$.asObservable();
  }

  constructor(private http: HttpClient) {}

  login(credentials: { password: string; username: string }): Observable<any> {
    return this.http
      .post(`http://localhost:8080/authenticate`, credentials, {
        responseType: 'text',
      })
      .pipe(
        tap(() => {
          this.loggedIn$.next('Wyloguj');
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
    this.loggedIn$.next('Zaloguj');
  }

  setLoggedIn(value: string): void {
    this.loggedIn$.next(value);
  }
}
