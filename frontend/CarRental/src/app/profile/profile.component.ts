import { Component } from '@angular/core';
import {
  FormGroup,
  FormBuilder,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, takeUntil, finalize, Observable } from 'rxjs';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { SpinnerComponent } from '../components/spinner/spinner.component';
import { ProfileService } from './profile.service';
import { AuthService } from '../services/auth.service';
import { UserRole } from '../users/role-enum';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    CommonModule,
    SpinnerComponent,
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss',
})
export class ProfileComponent {
  form!: FormGroup;
  isLoading = true;
  roleLogged$!: Observable<UserRole | null>;
  userRole = UserRole.USER;
  adminRole = UserRole.ADMIN;
  keeperRole = UserRole.KEEPER;

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private profileService: ProfileService,
    private authService: AuthService
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      login: ['', Validators.required],
      password: ['', Validators.required],
      role: [{ value: '', disabled: true }, Validators.required],
    });
  }

  ngOnInit(): void {
    this.roleLogged$ = this.authService.roleLoggedIn$;
  }

  onEdit(): void {
    if (this.form.valid) {
      this.isLoading = true;

      // this.usersService
      //   .editUser({ ...this.form.value, id: this.usersService.userIdentifier })
      //   .pipe(
      //     finalize(() => (this.isLoading = false)),
      //     takeUntil(this._destroying$)
      //   )
      //   .subscribe();
    } else {
      this.form.markAllAsTouched();
    }
  }

  onDelete(): void {
    // this.usersService
    //   .deleteUser()
    //   .pipe(
    //     finalize(() => this.router.navigate(['uzytkownicy'])),
    //     takeUntil(this._destroying$)
    //   )
    //   .subscribe();
  }

  isControlValid(controlName: string): boolean {
    return (
      this.form.controls[controlName].invalid &&
      (this.form.controls[controlName].dirty ||
        this.form.controls[controlName].touched)
    );
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
