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
import { CdkVirtualScrollable, ScrollingModule } from '@angular/cdk/scrolling';

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
  ],
  templateUrl: './browse-vehicles.component.html',
  styleUrl: './browse-vehicles.component.scss',
})
export class BrowseVehiclesComponent implements OnInit, OnDestroy {
  vehicles: Vehicle[] = [];
  results: Vehicle[] = [];

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
        vehicle.equipment.toLowerCase().includes(searchTextLower) ||
        vehicle.version.toLowerCase().includes(searchTextLower) ||
        vehicle.purpose.toLowerCase().includes(searchTextLower) ||
        vehicle.vehicleId.toString().includes(searchTextLower)
    );
  }

  onEditVehicle(vehicle: Vehicle): void {
    this.router.navigate(['pojazdy', 'edytuj', vehicle.vehicleId]);
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
