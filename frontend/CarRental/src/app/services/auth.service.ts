import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of, tap } from 'rxjs';
import { UserRole } from '../users/role-enum';
import { Response } from './response.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loggedIn$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(
    false
  );
  private roleLogged$: BehaviorSubject<UserRole> =
    new BehaviorSubject<UserRole>(UserRole.ADMIN);

  private idLogged$: BehaviorSubject<string> = new BehaviorSubject<string>('');

  get idLoggedIn$(): Observable<string | null> {
    return this.idLogged$;
  }

  get isLoggedIn$(): Observable<boolean> {
    return this.loggedIn$;
  }

  get roleLoggedIn$(): Observable<UserRole> {
    return this.roleLogged$;
  }

  constructor(private http: HttpClient) {}

  login(credentials: { password: string; username: string }): Observable<any> {
    return this.http
      .post<Response>(`http://localhost:8080/authenticate`, credentials)
      .pipe(
        tap((response: Response) => {
          this.saveLoginData(response, true);
          this.loggedIn$.next(true);
          this.roleLogged$.next(response.role);
        })
      );
  }

  register(user: any): Observable<any> {
    return of(undefined);
    // return this.http.post(`${this.apiURL}/register`, user);
  }

  saveLoginData(response: Response, isLogged: boolean): void {
    localStorage.setItem('token', response.token);
    localStorage.setItem('role', response.role);
    localStorage.setItem('id', response.id);
    localStorage.setItem('isLogged', JSON.stringify(isLogged));
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout(): void {
    localStorage.clear();
    this.loggedIn$.next(false);
  }

  setLoggedIn(value: boolean): void {
    this.loggedIn$.next(value);
  }
  setRole(value: UserRole): void {
    this.roleLogged$.next(value);
  }

  setId(value: string): void {
    this.idLogged$.next(value);
  }
}
