import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { client } from '../../pages/clients/client';


@Injectable({
  providedIn: 'root'
})
export class ClientService {

  readonly API_URL="http://localhost:8081"
  readonly ENDPOINT_getClient="/client/clients-all"
  readonly ENDPOINT_deleteClient="/client/delet-client"
   readonly ENDPOINT_postClient="/client/clients"

  constructor(private httpClient: HttpClient) {}

  enregistrerClient(client: client) {
    return this.httpClient.post(this.API_URL+this. ENDPOINT_postClient,client);
  }

  public getClient(): Observable<client[]> {
    return this.httpClient.get<client[]>( this.API_URL+this.ENDPOINT_getClient)
}

deleteClient(id ?: number): Observable<any> {
  return this.httpClient.delete(`${this.API_URL+this.ENDPOINT_deleteClient}/${id}`);
}






}
