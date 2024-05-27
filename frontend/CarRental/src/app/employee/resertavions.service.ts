import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Reservation } from './reservation.interface';
import { Observable, delay } from 'rxjs';
import { Model } from '../vehicles/model.interface';
import { Brand } from '../vehicles/brand.interface';

@Injectable({
  providedIn: 'root',
})
export class ResertavionsService {
  constructor(private http: HttpClient) {}

  getAvailableModels(): Observable<Model[]> {
    return this.http.get<Model[]>(
      'http://localhost:8080/api/vehicle/printAvailableModels'
    );
  }

  getAvailableBrands(): Observable<Brand[]> {
    return this.http.get<Brand[]>(
      'http://localhost:8080/api/vehicle/printAvailableBrands'
    );
  }

  getAllReservations(): Observable<Reservation[]> {
    return this.http
      .get<Reservation[]>('http://localhost:8080/reservations')
      .pipe(delay(1000));
  }

  endReservation(reservation: Reservation) {
    return this.http.delete(
      `http://localhost:8080/reservations/${reservation.reservationId}`
    );
  }

  //TODO
  // searchReservations(query: string): Observable<Reservation[]> {
  //   return this.http
  //     .get<Reservation[]>(
  //       `http://localhost:8080/api/employee/searchEmployee/${query}`
  //     )
  //     .pipe(debounceTime(400), delay(500));
  // }

  //TODO
  addReservation(
    brandName: string,
    modelName: string,
    dateFrom: string,
    dateTo: string
  ): Observable<any> {
    console.log(brandName);
    console.log(modelName);
    console.log(dateFrom);
    console.log(dateTo);
    const params = {
      model: modelName,
      brand: brandName,
      startTime: dateFrom,
      endTime: dateTo,
      employeeId: 9,
      carGiverId: 1,
    };
    return this.http.post(
      'http://localhost:8080/reservations/AddReservation',
      params
    );
  }
}
