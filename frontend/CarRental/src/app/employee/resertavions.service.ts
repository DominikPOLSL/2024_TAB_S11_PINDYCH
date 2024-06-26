import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Reservation } from './reservation.interface';
import { Observable, debounceTime, delay, tap } from 'rxjs';
import { Model } from '../vehicles/model.interface';
import { Brand } from '../vehicles/brand.interface';

@Injectable({
  providedIn: 'root',
})
export class ResertavionsService {
  constructor(private http: HttpClient) {}

  getAvailableModels(brandId: number): Observable<Model[]> {
    return this.http.get<Model[]>(
      `http://localhost:8080/api/vehicle/printAvailableModelsByBrandId/${brandId}`
    );
  }

  getAvailableBrands(): Observable<Brand[]> {
    return this.http.get<Brand[]>(
      'http://localhost:8080/api/vehicle/printAvailableBrands'
    );
  }

  getAllReservations(userId: string | null): Observable<Reservation[]> {
    return this.http
      .get<Reservation[]>(
        `http://localhost:8080/reservations/getAllReservationsByEmployeeId/${userId}`
      )
      .pipe(delay(1000));
  }

  endReservation(reservation: Reservation) {
    return this.http.delete(
      `http://localhost:8080/reservations/${reservation.id}`
    );
  }

  searchReservations(query: string): Observable<Reservation[]> {
    return this.http
      .get<Reservation[]>(
        `http://localhost:8080/reservations/getReservationByAttribute/${query}`
      )
      .pipe(debounceTime(400), delay(500));
  }

  addRental(reservation: Reservation) {
    return this.http.get(
      `http://localhost:8080/rents/createNewRent/${reservation.id}`
    );
  }

  addReservation(
    brandName: string,
    modelName: string,
    dateFrom: string,
    dateTo: string,
    userId: string | null
  ): Observable<Reservation> {
    const params = {
      model: modelName,
      brand: brandName,
      startTime: dateFrom,
      endTime: dateTo,
      employeeId: userId,
      carGiverId: 1,
    };
    return this.http
      .post<Reservation>('http://localhost:8080/reservations', params)
      .pipe(delay(1000));
  }
}
