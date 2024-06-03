export interface Reservation {
  id?: number;
  model: string;
  brand: string;
  startTime: string;
  endTime: string;
  employeeId: number;
  carGiverId: number;
  reserved: boolean;
}
