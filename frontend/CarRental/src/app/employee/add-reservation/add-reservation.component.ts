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
import { Observable, Subject, finalize, takeUntil } from 'rxjs';
import { SpinnerComponent } from '../../components/spinner/spinner.component';
import { Brand } from '../../vehicles/brand.interface';
import { Model } from '../../vehicles/model.interface';
import { CalendarModule } from 'primeng/calendar';
import { ResertavionsService } from '../resertavions.service';

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
  brands$!: Observable<Brand[]>;

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private reservationsService: ResertavionsService
  ) {
    this.form = this.fb.group({
      brand: ['', [Validators.required]],
      model: [{ value: '', disabled: true }, [Validators.required]],
      date: ['', [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.brands$ = this.reservationsService.getAvailableBrands();
  }

  getModelsByBrand(): void {
    const brandId = this.form.get('brand')?.value.brandId;
    this.reservationsService
      .getAvailableModels(brandId)
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
      this.isLoading = true;
      const modelName = this.form.get('model')?.value.modelName;
      const brandName = this.form.get('brand')?.value.brandName;
      const date: Date[] = this.form.get('date')?.value;
      this.reservationsService
        .addReservation(
          brandName,
          modelName,
          date[0].toJSON(),
          date[1].toJSON()
        )
        .pipe(
          finalize(() => (this.isLoading = false)),
          takeUntil(this._destroying$)
        )
        .subscribe(() => {
          this.form.reset();
          this.form.get('model')?.disable();
        });
    } else {
      this.form.markAllAsTouched();
    }
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
