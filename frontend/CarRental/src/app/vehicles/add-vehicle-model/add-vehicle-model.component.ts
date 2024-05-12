import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
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
import { ModelsService } from '../models.service';
import { Brand } from '../brand.interface';
import { Subject, takeUntil } from 'rxjs';
import { BrandsService } from '../brands.service';

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
export class AddVehicleModelComponent implements OnInit, OnDestroy {
  brands: Brand[] = [];
  newBrand: boolean = false;
  addModelForm: FormGroup;

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private brandsService: BrandsService,
    private modelsService: ModelsService
  ) {
    this.addModelForm = this.fb.group({
      brand: ['', Validators.required],
      model: ['', Validators.required],
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

  onInputChange(): void {
    this.addModelForm.reset();
  }

  onSubmit(): void {
    const modelName = this.addModelForm.get('model')?.value;
    let brandName: string;
    if (this.newBrand) {
      brandName = this.addModelForm.get('brand')?.value;
    } else {
      brandName = this.addModelForm.get('brand')?.value.brandName;
    }
    this.modelsService
      .addModel(modelName, brandName)
      .pipe(takeUntil(this._destroying$))
      .subscribe();
    this.addModelForm.reset();
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
