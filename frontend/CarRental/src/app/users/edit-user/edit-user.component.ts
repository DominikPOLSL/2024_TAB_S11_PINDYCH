import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  NonNullableFormBuilder,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { UserRole } from '../role-enum';
import { ActivatedRoute } from '@angular/router';
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
  form!: FormGroup<UserForm>;
  roleOptions = Object.values(UserRole);
  isLoading = true;
  user$!: Observable<User | undefined>;

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private usersService: UsersService
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      login: ['', Validators.required],
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
      console.log(user);
      this.form.patchValue({
        name: user?.name,
        surname: user?.surname,
        login: user?.login,
        role: user?.role,
      });
    });
  }

  isControlValid(controlName: string): boolean {
    const control = this.form.get(controlName);
    return control
      ? control?.invalid && control?.dirty && control.touched
      : false;
  }

  onSubmit(): void {
    if (this.form.valid) {
      //TODO submit form action
    } else {
      this.form.markAllAsTouched();
    }
  }

  onEdit(): void {
    //TODO
  }

  onDelete(): void {
    //TODO
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}

interface UserForm {
  name: FormControl<string | null>;
  surname: FormControl<string | null>;
  login: FormControl<string | null>;
  role: FormControl<UserRole | null>;
}
