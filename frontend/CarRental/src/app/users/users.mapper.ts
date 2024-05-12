import { UserRole } from './role-enum';
import { User, UserDTO } from './user-interface';

export function mapUsers(users: UserDTO[]): User[] {
  return users.map((user: UserDTO) => mapUser(user));
}

export function mapUser(user: UserDTO): User {
  return {
    id: user.employeeId,
    name: user.employeeName,
    surname: user.employeeSurname,
    login: 'any',
    password: 'any',
    role: UserRole.ADMIN,
  };
}

export function mapUserDTO(user: User): UserDTO {
  return {
    employeeId: user.id,
    employeeName: user.name,
    employeeSurname: user.surname,
    employeeLogin: user.login,
    employeePassword: user.password,
    roleType: user.role,
  };
}
