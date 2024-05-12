import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { VehiclesService } from '../vehicles.service';
import { Subject, takeUntil } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Brand } from '../brand.interface';
import { Model } from '../model.interface';
import { ModelsService } from '../models.service';
import { BrandsService } from '../brands.service';

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
export class EditVehicleComponent implements OnInit {
  editCarForm: FormGroup;
  id: number = 0;
  brands: Brand[] = [];
  models: Model[] = [];

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private vehiclesService: VehiclesService,
    private route: ActivatedRoute,
    private router: Router,
    private modelsService: ModelsService,
    private brandsService: BrandsService
  ) {
    this.editCarForm = this.fb.group({
      brand: ['', Validators.required],
      model: [{ value: '', disabled: true }, Validators.required],
      fuel: ['', [Validators.required]],
      totalDistance: ['', [Validators.required]],
      yearOfProduction: ['', [Validators.required]],
      power: ['', [Validators.required, Validators.min(0)]],
      carGiver: [{ value: '', disabled: true }],
    });
  }

  ngOnInit(): void {
    this.route.params.pipe(takeUntil(this._destroying$)).subscribe((params) => {
      this.id = params['id'];
    });

    this.vehiclesService
      .getVehicleById(this.id)
      .pipe(takeUntil(this._destroying$))
      .subscribe((vehicle) => {
        this.editCarForm.patchValue(vehicle);
      });

    this.brandsService
      .getBrands()
      .pipe(takeUntil(this._destroying$))
      .subscribe((brands) => {
        this.brands = brands;
      });
  }

  getModelsByBrand(): void {
    const brandId = this.editCarForm.get('brand')?.value.brandId;
    this.modelsService
      .getModelsByBrand(brandId)
      .pipe(takeUntil(this._destroying$))
      .subscribe((models) => {
        this.models = models;
        this.editCarForm.get('model')?.enable();
      });
  }

  onEditVehicle(): void {
    this.vehiclesService
      .editVehicle(
        this.id,
        this.editCarForm.value,
        this.editCarForm.get('model')?.value.modelId
      )
      .pipe(takeUntil(this._destroying$))
      .subscribe(() => {
        this.router.navigateByUrl('pojazdy/przegladaj');
      });
  }

  onDeleteVehicle(): void {
    this.vehiclesService.deleteVehicle(this.id).subscribe(() => {
      this.router.navigateByUrl('pojazdy/przegladaj');
    });
  }
  onEndReservation(): void {}

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
