import { Component } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';

interface Vehicle {
  model: string;
  version: string;
  id: string;
  carGiver: string;
}

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
export class BrowseVehiclesComponent {
  vehicles: Vehicle[] = [
    {
      model: 'Audi',
      version: 'A4',
      id: 'f1nsdufnosidfsd',
      carGiver: 'brak',
    },
    {
      model: 'Volkswagen',
      version: 'T4',
      id: 'f2nsdufnosidfsd',
      carGiver: 'brak',
    },
    {
      model: 'Seat',
      version: 'Ibiza',
      id: 'f3nsdufnosidfsd',
      carGiver: 'brak',
    },
    {
      model: 'Volkswagen',
      version: 'T1',
      id: 'f4nsdufnosidfsd',
      carGiver: 'Robert Kubica',
    },
  ];

  results = [...this.vehicles];
  searchedVehicle: string = '';

  onSearch(): void {
    if (this.searchedVehicle.trim() === '') {
      this.results = this.vehicles;
      return;
    }

    const searchTextLower = this.searchedVehicle.toLowerCase();
    this.results = this.vehicles.filter(
      (vehicle) =>
        vehicle.model.toLowerCase().includes(searchTextLower) ||
        vehicle.version.toLowerCase().includes(searchTextLower) ||
        vehicle.carGiver.toLowerCase().includes(searchTextLower) ||
        vehicle.id.toLowerCase().includes(searchTextLower)
    );
  }
}
