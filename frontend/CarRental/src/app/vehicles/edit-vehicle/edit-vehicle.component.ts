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
import { Observable, Subject, takeUntil } from 'rxjs';
import { Vehicle } from '../vehicle.interface';
import { ActivatedRoute, Router } from '@angular/router';

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

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private vehiclesService: VehiclesService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.editCarForm = this.fb.group({
      brand: ['', Validators.required],
      model: ['', Validators.required],
      fuel: ['', [Validators.required]],
      totalDistance: ['', [Validators.required]],
      yearOfProduction: ['', [Validators.required]],
      power: ['', [Validators.required, Validators.min(0)]],
      carGiver: [''],
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
  }

  onEditVehicle(): void {
    this.vehiclesService
      .editVehicle(this.id, this.editCarForm.value)
      .pipe(takeUntil(this._destroying$))
      .subscribe(() => {
        this.router.navigateByUrl('pojazdy/przeglądaj');
      });
  }

  onDeleteVehicle(): void {
    this.vehiclesService.deleteVehicle(this.id).subscribe();
    this.router.navigateByUrl('pojazdy/przegladaj');
  }
  onEndReservation(): void {}

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
