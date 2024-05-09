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

  constructor(private usersService: UsersService) {}

  ngOnInit(): void {
    this.users$ = this.usersService.getUsers();
  }

  // onSearch(): void {
  //   if (this.query.trim() === '') {
  //     return;
  //   }

  //   const searchText = this.query.toLowerCase();
  //   this.results = this.users.filter(
  //     (user) =>
  //       user.role.toLowerCase().includes(searchText) ||
  //       user.surname.toLowerCase().includes(searchText) ||
  //       user.name.toLowerCase().includes(searchText) ||
  //       user.id.toLowerCase().includes(searchText)
  //   );
  // }
}
