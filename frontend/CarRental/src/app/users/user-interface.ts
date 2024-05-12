import { UserRole } from './role-enum';

export interface User {
  id: string;
  name: string;
  surname: string;
  login: string;
  password: string;
  role: UserRole;
}

export interface UserDTO{
  employeeId: string;
  employeeName: string;
  employeeSurname: string;
  employeeLogin: string;
  employeePassword: string;
  roleType: string;
}