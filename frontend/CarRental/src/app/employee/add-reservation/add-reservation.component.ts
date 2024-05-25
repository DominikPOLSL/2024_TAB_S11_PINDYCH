import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  ReactiveFormsModule,
  FormGroup,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { Subject, takeUntil } from 'rxjs';
import { SpinnerComponent } from '../../components/spinner/spinner.component';
import { BrandsService } from '../../vehicles/brands.service';
import { ModelsService } from '../../vehicles/models.service';
import { Brand } from '../../vehicles/brand.interface';
import { Model } from '../../vehicles/model.interface';
import { CalendarModule } from 'primeng/calendar';

@Component({
  selector: 'app-add-reservation',
  standalone: true,
  imports: [
    DropdownModule,
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    CommonModule,
    SpinnerComponent,
    CalendarModule,
  ],
  templateUrl: './add-reservation.component.html',
  styleUrl: './add-reservation.component.scss',
})
export class AddReservationComponent implements OnInit, OnDestroy {
  isLoading = false;
  form: FormGroup;
  brands: Brand[] = [];
  models: Model[] = [];

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private brandsService: BrandsService,
    private modelsService: ModelsService
  ) {
    this.form = this.fb.group({
      brand: ['', [Validators.required]],
      model: [{ value: '', disabled: true }, [Validators.required]],
      date: ['', [Validators.required]],
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
    const brandId = this.form.get('brand')?.value.brandId;
    this.modelsService
      .getModelsByBrand(brandId)
      .pipe(takeUntil(this._destroying$))
      .subscribe((models) => {
        this.models = models;
        this.form.get('model')?.enable();
      });
  }

  isControlValid(controlName: string): boolean {
    return (
      this.form.controls[controlName].invalid &&
      (this.form.controls[controlName].dirty ||
        this.form.controls[controlName].touched)
    );
  }

  onSubmit(): void {
    if (this.form.valid) {
      //this.isLoading = true;
      //TODO
      console.log(this.form.get('date')?.value);
    } else {
      this.form.markAllAsTouched();
    }
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
