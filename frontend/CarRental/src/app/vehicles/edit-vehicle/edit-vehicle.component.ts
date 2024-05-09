import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  selector: 'app-edit-vehicle',
  standalone: true,
  imports: [
    DropdownModule,
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    CommonModule,
  ],
  templateUrl: './edit-vehicle.component.html',
  styleUrl: './edit-vehicle.component.scss',
})
export class EditVehicleComponent {
  brands: string[] = ['test', 'test2'];
  models: string[] = ['test4', 'test3'];

  addCarForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.addCarForm = this.fb.group({
      brand: ['', Validators.required],
      model: ['', Validators.required],
      version: ['', [Validators.required]],
      mileage: ['', [Validators.required]],
      yearOfProduction: ['', [Validators.required]],
      power: ['', [Validators.required, Validators.min(0)]],
      carGiver: [''],
    });
  }

  onEditVehicle(): void {}
  onDeleteVehicle(): void {}
  onEndReservation(): void {}
}
