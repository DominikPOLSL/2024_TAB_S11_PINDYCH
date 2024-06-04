import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { SpinnerComponent } from '../../components/spinner/spinner.component';
import { Observable, Subject, finalize, takeUntil } from 'rxjs';
import { ResertavionsService } from '../resertavions.service';
import { Reservation } from '../reservation.interface';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-reservations',
  standalone: true,
  imports: [
    IconFieldModule,
    InputIconModule,
    InputTextModule,
    CommonModule,
    ButtonModule,
    FormsModule,
    SpinnerComponent,
  ],
  templateUrl: './reservations.component.html',
  styleUrl: './reservations.component.scss',
})
export class ReservationsComponent implements OnInit, OnDestroy {
  reservations$!: Observable<Reservation[]>;
  isLoading = true;
  query: string = '';
  userId: string | null = '';

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private reservationsService: ResertavionsService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.authService.idLoggedIn$
      .pipe(takeUntil(this._destroying$))
      .subscribe((id) => {
        this.userId = id;
      });
    this.reservations$ = this.reservationsService.getAllReservations(
      this.userId
    );
  }

  onRentVehicle(reservation: Reservation): void {
    this.isLoading = true;
    this.reservationsService
      .addRental(reservation)
      .pipe(
        finalize(() => (this.isLoading = false)),
        takeUntil(this._destroying$)
      )
      .subscribe(() => {
        this.reservations$ = this.reservationsService.getAllReservations(
          this.userId
        );
      });
  }

  onCancelReservation(reservation: Reservation): void {
    this.isLoading = true;
    this.reservationsService
      .endReservation(reservation)
      .pipe(
        finalize(() => (this.isLoading = false)),
        takeUntil(this._destroying$)
      )
      .subscribe(() => {
        this.reservations$ = this.reservationsService.getAllReservations(
          this.userId
        );
      });
  }

  onSearch(): void {
    if (this.query === '') {
      this.reservations$ = this.reservationsService.getAllReservations(
        this.userId
      );
    } else {
      this.reservations$ = this.reservationsService.searchReservations(
        this.query
      );
    }
  }

  getSearchIconName(): string {
    if (this.query === '') {
      return 'search';
    } else {
      return 'times';
    }
  }

  clearQuery() {
    this.reservations$ = this.reservationsService.getAllReservations(
      this.userId
    );
    this.query = '';
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
