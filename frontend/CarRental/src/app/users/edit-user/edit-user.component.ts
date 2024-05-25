import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { UserRole } from '../role-enum';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user-interface';
import {
  Observable,
  Subject,
  finalize,
  map,
  of,
  switchMap,
  takeUntil,
} from 'rxjs';
import { UsersService } from '../users.service';
import { SpinnerComponent } from '../../components/spinner/spinner.component';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-edit-user',
  standalone: true,
  imports: [
    DropdownModule,
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    CommonModule,
    SpinnerComponent,
  ],
  templateUrl: './edit-user.component.html',
  styleUrl: './edit-user.component.scss',
})
export class EditUserComponent implements OnInit, OnDestroy {
  form!: FormGroup;
  roleOptions = Object.values(UserRole);
  isLoading = true;
  user$!: Observable<User | undefined>;

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private usersService: UsersService,
    private router: Router
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      login: ['', Validators.required],
      password: ['', Validators.required],
      role: ['' as UserRole, Validators.required],
    });
  }

  ngOnInit(): void {
    this.user$ = this.route.paramMap.pipe(
      map((params) => params.get('id')),
      switchMap(
        (id): Observable<User | undefined> =>
          id ? this.usersService.getUser(id) : of(undefined)
      ),
      takeUntil(this._destroying$)
    );

    this.user$.pipe(takeUntil(this._destroying$)).subscribe((user) => {
      this.isLoading = false;
      this.form.patchValue({
        name: user?.name,
        surname: user?.surname,
        login: user?.login,
        password: user?.password,
        role: user?.role,
      });
    });
  }

  onEdit(): void {
    if (this.form.valid) {
      this.isLoading = true;

      this.usersService
        .editUser({ ...this.form.value, id: this.usersService.userIdentifier })
        .pipe(
          finalize(() => (this.isLoading = false)),
          takeUntil(this._destroying$)
        )
        .subscribe();
    } else {
      this.form.markAllAsTouched();
    }
  }

  onDelete(): void {
    this.usersService
      .deleteUser()
      .pipe(
        finalize(() => this.router.navigate(['uzytkownicy'])),
        takeUntil(this._destroying$)
      )
      .subscribe();
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
