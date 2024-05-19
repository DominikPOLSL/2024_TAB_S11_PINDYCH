import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Vehicle } from './vehicle.interface';
import { Observable, delay } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class VehiclesService {
  constructor(private http: HttpClient) {}

  getVehicles(): Observable<Vehicle[]> {
    return this.http
      .get<Vehicle[]>('http://localhost:8080/api/vehicle/printVehicle')
      .pipe(delay(1000));
  }

  getVehicleById(id: number): Observable<Vehicle> {
    return this.http
      .get<Vehicle>(`http://localhost:8080/api/vehicle/${id}`)
      .pipe(delay(1000));
  }

  editVehicle(
    id: number,
    vehicle: Vehicle,
    modelId: number
  ): Observable<Vehicle> {
    const params = {
      totalDistance: vehicle.totalDistance,
      modelId: modelId,
      fuel: vehicle.fuel,
      yearOfProduction: vehicle.yearOfProduction,
      power: vehicle.power,
    };

    return this.http
      .put<Vehicle>(`http://localhost:8080/api/vehicle/${id}`, params)
      .pipe(delay(1000));
  }

  addVehicle(vehicle: Vehicle, modelId: string): Observable<Vehicle> {
    const params = {
      fuel: vehicle.fuel,
      power: vehicle.power,
      totalDistance: vehicle.totalDistance,
      yearOfProduction: vehicle.yearOfProduction,
      modelId: modelId,
    };
    return this.http
      .post<Vehicle>('http://localhost:8080/api/vehicle', params)
      .pipe(delay(1000));
  }

  deleteVehicle(id: number) {
    return this.http
      .delete(`http://localhost:8080/api/vehicle/${id}`)
      .pipe(delay(1000));
  }
}
