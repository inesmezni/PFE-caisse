import { Component } from '@angular/core';
import { KeycloakAuthService } from '../../services/auth/keycloak.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  
  constructor(
    private authService: KeycloakAuthService,
  ) {
  }
  logout() {
    localStorage.removeItem('currentUser');
    this.authService.logout()
  }
}
