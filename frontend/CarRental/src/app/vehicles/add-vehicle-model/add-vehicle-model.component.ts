import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { InputSwitchModule } from 'primeng/inputswitch';

@Component({
  selector: 'app-add-vehicle-model',
  standalone: true,
  imports: [
    DropdownModule,
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    CommonModule,
    InputSwitchModule,
    FormsModule,
  ],
  templateUrl: './add-vehicle-model.component.html',
  styleUrl: './add-vehicle-model.component.scss',
})
export class AddVehicleModelComponent {
  brands: string[] = ['test', 'test2'];
  models: string[] = ['test4', 'test3'];

  newBrand: boolean = false;

  addModelForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.addModelForm = this.fb.group({
      brand: ['', Validators.required],
      model: ['', Validators.required],
    });
  }

  onSubmit(): void {
    this.addModelForm.reset();
  }
}
