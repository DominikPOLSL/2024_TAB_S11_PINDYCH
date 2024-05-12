import { Component, OnInit, model } from '@angular/core';
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
import { BrandsService } from '../brands.service';
import { Brand } from '../brand.interface';
import { Subject, takeUntil } from 'rxjs';
import { ModelsService } from '../models.service';
import { VehiclesService } from '../vehicles.service';
import { Model } from '../model.interface';

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
  brands: Brand[] = [];
  models: Model[] = [];

  addCarForm: FormGroup;

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private brandsService: BrandsService,
    private modelsService: ModelsService,
    private vehiclesService: VehiclesService
  ) {
    this.addCarForm = this.fb.group({
      brand: ['', Validators.required],
      model: ['', Validators.required],
      fuel: ['', [Validators.required]],
      totalDistance: ['', [Validators.required]],
      yearOfProduction: ['', [Validators.required]],
      power: ['', [Validators.required, Validators.min(0)]],
    });
  }

  ngOnInit(): void {
    this.brandsService
      .getBrands()
      .pipe(takeUntil(this._destroying$))
      .subscribe((brands) => {
        this.brands = brands;
      });
  }

  getModelsByBrand(): void {
    const brandId = this.addCarForm.get('brand')?.value.brandId;
    this.modelsService
      .getModelsByBrand(brandId)
      .pipe(takeUntil(this._destroying$))
      .subscribe((models) => {
        this.models = models;
      });
  }

  onSubmit(): void {
    this.vehiclesService;
    this.addCarForm.reset();
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
