import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Entreprise } from '../../pages/entreprise/entreprise';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EntrepriseService {

  readonly API_URL="http://localhost:8081"
  readonly ENDPOINT_postEntreprise="/Entreprise/add"
  
  

  constructor(private httpClient: HttpClient) {}


  enregistrerEntreprise(entreprise: Entreprise) {
    return this.httpClient.post(this.API_URL+this.ENDPOINT_postEntreprise,entreprise);
  }

  
}

