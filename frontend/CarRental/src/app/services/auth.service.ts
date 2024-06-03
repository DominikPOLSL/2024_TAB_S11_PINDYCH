import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(credentials: { password: string; username: string }): Observable<any> {
    //return of(undefined);
    console.log(credentials);
    return this.http.post(`http://localhost:8080/authenticate`, credentials, {
      responseType: 'text',
    });
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
  }
}
