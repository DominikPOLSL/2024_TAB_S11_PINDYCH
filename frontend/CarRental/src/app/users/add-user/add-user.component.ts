import { CommonModule } from '@angular/common';
import { Component, OnDestroy } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { UserRole } from '../role-enum';
import { UsersService } from '../users.service';
import { Subject, finalize, takeUntil } from 'rxjs';
import { SpinnerComponent } from '../../components/spinner/spinner.component';

@Component({
  selector: 'app-add-user',
  standalone: true,
  imports: [
    DropdownModule,
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    CommonModule,
    SpinnerComponent,
  ],
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.scss',
})
export class AddUserComponent implements OnDestroy {
  roles = Object.keys(UserRole);
  isLoading = false;
  form: FormGroup;

  private readonly _destroying$ = new Subject<void>();

  constructor(private fb: FormBuilder, private usersService: UsersService) {
    this.form = this.fb.group({
      name: ['', [Validators.required]],
      surname: ['', [Validators.required]],
      login: ['', [Validators.required]],
      password: ['', [Validators.required]],
      role: ['', Validators.required],
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
      this.usersService
        .addUser(this.form.value)
        .pipe(
          finalize(() => (this.isLoading = false)),
          takeUntil(this._destroying$)
        )
        .subscribe();
    } else {
      this.form.markAllAsTouched();
    }
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
