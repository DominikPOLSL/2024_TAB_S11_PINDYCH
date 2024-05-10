import { UserRole } from './role-enum';

export interface User {
  id: string;
  name: string;
  surname: string;
  login: string;
  role: UserRole;
}
