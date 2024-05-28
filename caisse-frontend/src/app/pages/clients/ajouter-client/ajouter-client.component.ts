import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../../services/client/client.service';
import { client } from '../client';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ajouter-client',
  templateUrl: './ajouter-client.component.html',
  styleUrl: './ajouter-client.component.css'
})
export class AjouterClientComponent implements OnInit {
  client: client= {
    nom: '',
    prenom: '',
    adresse: '',
    mail: '',
    numTel: '',
  };

  constructor(
   private router:Router,
   private clientService: ClientService
  ){ }

  ngOnInit(): void {
     }

  cancel():void {
    this.router.navigate(['caissier-dashboard/client']);
  }

  enregistrerClient() {
    this.clientService.enregistrerClient(this.client).subscribe(
        response => {
          console.log('Client ajoutée avec succès!', response);
          
        },

        error => {
          console.error('Client existe déja !', error);
        }
    );
    this.router.navigate(['caissier-dashboard/client']);
 }   
}


