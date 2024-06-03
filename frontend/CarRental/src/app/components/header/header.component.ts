import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent implements OnInit {
  constructor(private authService: AuthService) {}
  loginState$: Observable<string> = this.authService.LoggedIn;

  ngOnInit(): void {
    if (this.authService.getToken()) {
      this.authService.setLoggedIn('Wyloguj');
    } else {
      this.authService.setLoggedIn('Zaloguj');
    }
  }

  onLogut(): void {
    this.authService.logout();
  }
}
