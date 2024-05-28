import { Component } from '@angular/core';
import { ClientService } from '../../../services/client/client.service'; 
import { Router } from '@angular/router';
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-page-client',
  templateUrl: './page-client.component.html',
  styleUrl: './page-client.component.css'
})
export class PageClientComponent implements OnInit{

  listClients: any[] = []; // Stocke les clients récupérées depuis le serveur
 
 


  constructor(
    private router: Router,
    private clientService: ClientService
  ) {}

  ngOnInit() {
    this.findAllClient();
  }

  findAllClient(){
    console.log('on init...');
    this.clientService.getClient().subscribe(
      (result) => {
        this.listClients = result; 
      }
      );
     }


     ajouterClient(): void {
      this.router.navigate(['caissier-dashboard/ajouterClient']); 
    }



}
