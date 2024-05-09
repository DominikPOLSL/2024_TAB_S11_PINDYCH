import { UserRole } from './role-enum';

export interface User {
  id: string;
  name: string;
  surname: string;
  role: UserRole;
}
