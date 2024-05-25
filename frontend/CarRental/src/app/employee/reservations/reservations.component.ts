import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { SpinnerComponent } from '../../components/spinner/spinner.component';
import { Subject, takeUntil } from 'rxjs';
import { Vehicle } from '../../vehicles/vehicle.interface';
import { VehiclesService } from '../../vehicles/vehicles.service';

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
  reservations: Vehicle[] = [];
  results: Vehicle[] = [];
  isLoading = true;

  searchedReservation: string = '';

  private readonly _destroying$ = new Subject<void>();

  constructor(private vehicleService: VehiclesService) {}

  ngOnInit(): void {
    this.vehicleService
      .getVehicles()
      .pipe(takeUntil(this._destroying$))
      .subscribe((reservations) => {
        this.isLoading = false;
        this.reservations = reservations;
        this.results = reservations;
      });
  }

  onSearch(): void {
    if (this.searchedReservation.trim() === '') {
      this.results = this.reservations;
      return;
    }

    const searchTextLower = this.searchedReservation.toLowerCase();
    this.results = this.reservations.filter(
      (reservation) =>
        reservation.brand.toLowerCase().includes(searchTextLower) ||
        reservation.model.toLowerCase().includes(searchTextLower) ||
        reservation.id.toString().includes(searchTextLower)
    );
  }

  onRentVehicle(vehicle: Vehicle): void {
    this.isLoading = true;
    //TODO
  }

  onCancelReservation(vehicle: Vehicle): void {
    this.isLoading = true;
    //TODO
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
