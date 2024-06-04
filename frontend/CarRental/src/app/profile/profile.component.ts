import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormGroup,
  FormBuilder,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, Subject, combineLatest } from 'rxjs';
import { takeUntil, finalize, switchMap } from 'rxjs/operators';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { SpinnerComponent } from '../components/spinner/spinner.component';
import { ProfileService } from './profile.service';
import { AuthService } from '../services/auth.service';
import { UserRole } from '../users/role-enum';
import { LoggedUser } from './logged-user.interface';

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
export class ProfileComponent implements OnInit, OnDestroy {
  form!: FormGroup;
  isLoading = true;
  roleLogged$ = this.authService.roleLoggedIn$;
  loggedUser$!: Observable<LoggedUser>;
  userId: string | null = '';
  role: string = '';
  userRole = UserRole.USER;
  adminRole = UserRole.ADMIN;
  keeperRole = UserRole.KEEPER;

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private profileService: ProfileService,
    private authService: AuthService,
    private router: Router
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      login: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    combineLatest([
      this.authService.idLoggedIn$,
      this.authService.roleLoggedIn$,
    ])
      .pipe(
        takeUntil(this._destroying$),
        switchMap(([id, role]) => {
          this.userId = id;
          this.role = role;
          return this.profileService
            .getProfileDataById(id, role)
            .pipe(finalize(() => (this.isLoading = false)));
        })
      )
      .subscribe((user) => {
        this.form.patchValue({
          name: user?.name,
          surname: user?.surname,
          login: user?.login,
          password: user?.password,
        });
      });
  }

  onEdit(): void {
    if (this.form.valid) {
      this.isLoading = true;
      this.profileService
        .editProfile(
          { ...this.form.value, id: this.userId, roleType: null },
          this.userId,
          this.role
        )
        .pipe(
          finalize(() => (this.isLoading = false)),
          takeUntil(this._destroying$)
        )
        .subscribe();
    } else {
      this.form.markAllAsTouched();
    }
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
