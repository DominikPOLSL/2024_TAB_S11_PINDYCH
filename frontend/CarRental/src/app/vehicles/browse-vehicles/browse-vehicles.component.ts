import { Component, OnDestroy, OnInit } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { VehiclesService } from '../vehicles.service';
import { Vehicle } from '../vehicle.interface';
import { Subject, takeUntil } from 'rxjs';
import { SpinnerComponent } from '../../components/spinner/spinner.component';

@Component({
  selector: 'app-browse-vehicles',
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
  templateUrl: './browse-vehicles.component.html',
  styleUrl: './browse-vehicles.component.scss',
})
export class BrowseVehiclesComponent implements OnInit, OnDestroy {
  vehicles: Vehicle[] = [];
  results: Vehicle[] = [];
  isLoading = true;

  searchedVehicle: string = '';

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private router: Router,
    private vehicleService: VehiclesService
  ) {}

  ngOnInit(): void {
    this.vehicleService
      .getVehicles()
      .pipe(takeUntil(this._destroying$))
      .subscribe((vehicles) => {
        this.isLoading = false;
        this.vehicles = vehicles;
        this.results = vehicles;
      });
  }

  onSearch(): void {
    if (this.searchedVehicle.trim() === '') {
      this.results = this.vehicles;
      return;
    }

    const searchTextLower = this.searchedVehicle.toLowerCase();
    this.results = this.vehicles.filter(
      (vehicle) =>
        vehicle.brand.toLowerCase().includes(searchTextLower) ||
        vehicle.model.toLowerCase().includes(searchTextLower) ||
        vehicle.id.toString().includes(searchTextLower) ||
        vehicle.guardianSurname.toString().includes(searchTextLower)
    );
  }

  onEditVehicle(vehicle: Vehicle): void {
    this.router.navigate(['pojazdy', 'edytuj', vehicle.id]);
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
