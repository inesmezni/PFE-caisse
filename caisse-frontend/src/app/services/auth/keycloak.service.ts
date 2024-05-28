import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { KeycloakService } from "keycloak-angular";
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class KeycloakAuthService {
  SERVER_URL: string = "http://localhost:8081" + "/keycloak";

  constructor(private keycloakService: KeycloakService,private httpClient: HttpClient) { }

  ngOnInit() {}
  logout() {
    localStorage.clear()
    this.keycloakService.logout();
  }

  getAllProfiles(): Observable<any> {
    return this.httpClient.get(this.SERVER_URL + "/profiles");
  }

  
}
