import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { CardModule } from 'primeng/card';
import { User } from '../user-interface';
import { SpinnerComponent } from '../../components/spinner/spinner.component';
import { Observable } from 'rxjs';
import { UsersService } from '../users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-browse-users',
  standalone: true,
  imports: [
    IconFieldModule,
    InputIconModule,
    InputTextModule,
    CommonModule,
    ButtonModule,
    FormsModule,
    SpinnerComponent,
    CardModule,
  ],
  templateUrl: './browse-users.component.html',
  styleUrl: './browse-users.component.scss',
})
export class BrowseUsersComponent implements OnInit {
  users$!: Observable<User[]>;
  query: string = '';

  constructor(private usersService: UsersService, private router: Router) {}

  ngOnInit(): void {
    this.users$ = this.usersService.getUsers();
  }

  onUser(id: string) {
    this.router.navigate(['uzytkownicy', 'edytuj', `${id}`]);
  }

  onSearch(): void {
    if (this.query === '') {
      this.users$ = this.usersService.getUsers();
    } else {
      this.users$ = this.usersService.searchUsers(this.query);
    }
  }

  getSearchIconName(): string {
    if (this.query === '') {
      return 'search';
    } else {
      return 'times';
    }
  }

  clearQuery() {
    this.users$ = this.usersService.getUsers();
    this.query = '';
  }
}
