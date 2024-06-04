import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { SpinnerComponent } from '../components/spinner/spinner.component';
import { Subject, finalize, takeUntil } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    DropdownModule,
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    CommonModule,
    SpinnerComponent,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  isLoading = false;
  form: FormGroup;

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.form = this.fb.group({
      login: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(5)]],
    });
  }

  isControlValid(controlName: string): boolean {
    return (
      this.form.controls[controlName].invalid &&
      (this.form.controls[controlName].dirty ||
        this.form.controls[controlName].touched)
    );
  }

  onLogin(): void {
    if (this.form.valid) {
      this.isLoading = true;
      this.authService
        .login({
          username: this.form.controls['login'].value,
          password: this.form.controls['password'].value,
        })
        .pipe(takeUntil(this._destroying$))
        .subscribe(() => {
          this.router.navigate(['/']);
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
