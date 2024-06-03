import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { UserRole } from '../../users/role-enum';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent implements OnInit {
  loginState$!: Observable<boolean>;
  roleLogged$!: Observable<UserRole | null>;

  userRole = UserRole.USER;
  adminRole = UserRole.ADMIN;
  keeperRole = UserRole.KEEPER;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.loginState$ = this.authService.isLoggedIn$;
    this.roleLogged$ = this.authService.roleLoggedIn$;
  }

  onLogout(): void {
    this.authService.logout();
  }
}
