import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Vehicle } from './vehicle.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class VehiclesService {
  constructor(private http: HttpClient) {}

  getVehicles(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>('http://localhost:8080/api/vehicle');
  }

  getVehicleById(id: number): Observable<Vehicle> {
    return this.http.get<Vehicle>(`http://localhost:8080/api/vehicle/${id}`);
  }

  editVehicle(id: number, vehicle: Vehicle): Observable<Vehicle> {
    return this.http.put<Vehicle>(
      'http://localhost:8080/api/vehicle/1',
      vehicle
    );
  }

  addVehicle(vehicle: Vehicle) {
    return this.http.post('http://localhost:8080/api/vehicle', vehicle);
  }

  deleteVehicle(id: number) {
    return this.http.delete(`http://localhost:8080/api/vehicle/${id}`);
  }
}
