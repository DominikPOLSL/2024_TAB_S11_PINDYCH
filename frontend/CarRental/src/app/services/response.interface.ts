import { UserRole } from '../users/role-enum';

export interface Response {
  role: UserRole;
  token: string;
}
