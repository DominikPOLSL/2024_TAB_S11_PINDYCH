import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, debounceTime, delay } from 'rxjs';
import { Rental } from './rental.interface';

@Injectable({
  providedIn: 'root',
})
export class RentalsService {
  constructor(private http: HttpClient) {}

  getRentals(): Observable<Rental[]> {
    return this.http
      .get<Rental[]>(
        `http://localhost:8080/reservations/PrintAllRentsByUserId/9`
      )
      .pipe(delay(1000));
  }

  searchRenatals(query: string): Observable<Rental[]> {
    return this.http
      .get<Rental[]>(
        `http://localhost:8080/reservations/getRentByAttribute/${query}`
      )
      .pipe(debounceTime(400), delay(500));
  }

  deleteRental(rentalId: number) {
    console.log(rentalId);
    return this.http.delete(
      `http://localhost:8080/rents/deleteRent/${rentalId}`
    );
  }
}
