import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

interface Employee {
  employeeId: number;
  employeeName: string;
  employeeSurname: string;
  employeeLogin: string;
  employeePassword: string;
  roleType: string;
}

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  constructor(private http: HttpClient) {}

  getProfileDataById(): Observable<Employee> {
    return this.http.get<Employee>(`http://localhost:8080/api/employee/14`);
  }

  editProfile() {}

  deleteProfile() {}
}
