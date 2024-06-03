import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { Observable, Subject, finalize, takeUntil } from 'rxjs';
import { SpinnerComponent } from '../../components/spinner/spinner.component';
import { Rental } from '../rental.interface';
import { RentalsService } from '../rentals.service';

@Component({
  selector: 'app-rentals',
  standalone: true,
  imports: [
    IconFieldModule,
    InputIconModule,
    InputTextModule,
    CommonModule,
    ButtonModule,
    FormsModule,
    SpinnerComponent,
  ],
  templateUrl: './rentals.component.html',
  styleUrl: './rentals.component.scss',
})
export class RentalsComponent implements OnInit, OnDestroy {
  isLoading = true;
  query: string = '';

  rentals$: Observable<Rental[]> = this.rentalsService.getRentals();

  private readonly _destroying$ = new Subject<void>();

  constructor(private rentalsService: RentalsService) {}

  ngOnInit(): void {
    this.rentals$ = this.rentalsService.getRentals();
  }

  onSearch(): void {
    if (this.query === '') {
      this.rentals$ = this.rentalsService.getRentals();
    } else {
      this.rentals$ = this.rentalsService.searchRenatals(this.query);
    }
  }

  onEndRental(rental: Rental): void {
    this.isLoading = true;

    this.rentalsService
      .deleteRental(rental.id)
      .pipe(
        finalize(() => (this.isLoading = false)),
        takeUntil(this._destroying$)
      )
      .subscribe(() => {
        this.rentals$ = this.rentalsService.getRentals();
      });
  }

  clearQuery() {
    this.rentals$ = this.rentalsService.getRentals();
    this.query = '';
  }

  getSearchIconName(): string {
    if (this.query === '') {
      return 'search';
    } else {
      return 'times';
    }
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
