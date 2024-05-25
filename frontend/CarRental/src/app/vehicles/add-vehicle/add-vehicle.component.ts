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
import { Subject, finalize, takeUntil } from 'rxjs';
import { ModelsService } from '../models.service';
import { VehiclesService } from '../vehicles.service';
import { Model } from '../model.interface';
import { SpinnerComponent } from '../../components/spinner/spinner.component';

@Component({
  selector: 'app-add-vehicle',
  standalone: true,
  imports: [
    DropdownModule,
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    CommonModule,
    SpinnerComponent,
  ],
  templateUrl: './add-vehicle.component.html',
  styleUrl: './add-vehicle.component.scss',
})
export class AddVehicleComponent implements OnInit {
  brands: Brand[] = [];
  models: Model[] = [];
  isLoading = false;

  addCarForm: FormGroup;
  isModelDisabled: boolean = false;

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private brandsService: BrandsService,
    private modelsService: ModelsService,
    private vehiclesService: VehiclesService
  ) {
    this.addCarForm = this.fb.group({
      brand: ['', Validators.required],
      model: [{ value: '', disabled: true }, Validators.required],
      fuel: ['', [Validators.required]],
      totalDistance: ['', [Validators.required, Validators.min(0)]],
      yearOfProduction: ['', [Validators.required, Validators.min(0)]],
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
        this.addCarForm.get('model')?.enable();
      });
  }

  onSubmit(): void {
    this.isLoading = true;
    this.vehiclesService
      .addVehicle(
        this.addCarForm.value,
        this.addCarForm.get('model')?.value.modelId
      )
      .pipe(
        finalize(() => (this.isLoading = false)),
        takeUntil(this._destroying$)
      )
      .subscribe();
    this.addCarForm.reset();
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
