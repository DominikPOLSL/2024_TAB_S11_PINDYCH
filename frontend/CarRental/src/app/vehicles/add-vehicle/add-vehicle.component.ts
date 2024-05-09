import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-vehicle',
  standalone: true,
  imports: [
    DropdownModule,
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    CommonModule,
  ],
  templateUrl: './add-vehicle.component.html',
  styleUrl: './add-vehicle.component.scss',
})
export class AddVehicleComponent implements OnInit {
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
    });
  }

  ngOnInit() {}
}
