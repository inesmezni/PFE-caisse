import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class EmailService {
  readonly API_URL="http://localhost:8081"
  readonly ENDPOINT_postEmail="/api/email/send"


 
  constructor(private httpClient: HttpClient) {}


 
  sendEmail(email: any) {
    return this.httpClient.post(this.API_URL + this.ENDPOINT_postEmail, email);
  }
}

