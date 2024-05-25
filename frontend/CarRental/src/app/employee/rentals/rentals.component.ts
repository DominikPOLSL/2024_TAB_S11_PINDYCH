import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { Subject, takeUntil } from 'rxjs';
import { SpinnerComponent } from '../../components/spinner/spinner.component';
import { Vehicle } from '../../vehicles/vehicle.interface';
import { VehiclesService } from '../../vehicles/vehicles.service';

@Component({
  selector: 'app-rentals',
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
  templateUrl: './rentals.component.html',
  styleUrl: './rentals.component.scss',
})
export class RentalsComponent implements OnInit, OnDestroy {
  rentals: Vehicle[] = [];
  results: Vehicle[] = [];
  isLoading = true;

  searchedRental: string = '';

  private readonly _destroying$ = new Subject<void>();

  constructor(private vehicleService: VehiclesService) {}

  ngOnInit(): void {
    this.vehicleService
      .getVehicles()
      .pipe(takeUntil(this._destroying$))
      .subscribe((rentals) => {
        this.isLoading = false;
        this.rentals = rentals;
        this.results = rentals;
      });
  }

  onSearch(): void {
    if (this.searchedRental.trim() === '') {
      this.results = this.rentals;
      return;
    }

    const searchTextLower = this.searchedRental.toLowerCase();
    this.results = this.rentals.filter(
      (rental) =>
        rental.brand.toLowerCase().includes(searchTextLower) ||
        rental.model.toLowerCase().includes(searchTextLower) ||
        rental.id.toString().includes(searchTextLower)
    );
  }

  onEndRental(vehicle: Vehicle): void {
    this.isLoading = true;
    //TODO
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
