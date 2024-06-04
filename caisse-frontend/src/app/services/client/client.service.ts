import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { client } from '../../pages/clients/client'; 

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  readonly API_URL = "http://localhost:8081";
  readonly ENDPOINT_getClient = "/client/clients-all";
  readonly ENDPOINT_deleteClient = "/client/delet-client";
  readonly ENDPOINT_postClient = "/client/clients";
  readonly ENDPOINT_getByIdClient = "/Category/categories/";
  readonly ENDPOINT_putClient = "/edit-client/";

  constructor(private httpClient: HttpClient) {}

  enregistrerClient(client: client): Observable<client> {
    return this.httpClient.post<client>(this.API_URL + this.ENDPOINT_postClient, client);
  }

  getClient(): Observable<client[]> {
    return this.httpClient.get<client[]>(this.API_URL + this.ENDPOINT_getClient);
  }

  deleteClient(id ?: number): Observable<any> {
    return this.httpClient.delete(`${this.API_URL + this.ENDPOINT_deleteClient}/${id}`);
  }

  getClientById(id: number): Observable<client> {
    return this.httpClient.get<client>(`${this.API_URL + this.ENDPOINT_getByIdClient}${id}`);
  }

  modifierClient(id: number, updateClient: any): Observable<any> {
    return this.httpClient.put(`${this.API_URL + this.ENDPOINT_putClient}${id}`, updateClient);
  }
}
