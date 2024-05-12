import { UserRole } from './role-enum';
import { User } from './user-interface';

export function mapUsers(users: any): User[] {
  return users.map((user: any) => mapUser(user));
}

export function mapUser(user: any): User {
  return {
    id: user.employeeId,
    name: user.employeeName,
    surname: user.employeeSurname,
    login: 'any',
    role: UserRole.ADMIN,
  };
}

export function mapUserDTO(user: User): any {
  return {
    employeeName: user.name,
    employeeSurname: user.surname,
  };
}
