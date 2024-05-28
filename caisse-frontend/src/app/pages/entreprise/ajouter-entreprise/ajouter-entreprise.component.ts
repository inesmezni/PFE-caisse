import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EntrepriseService } from '../../../services/entreprise/entreprise.service';
import { Entreprise } from '../entreprise';

@Component({
  selector: 'app-ajouter-entreprise',
  templateUrl: './ajouter-entreprise.component.html',
  styleUrl: './ajouter-entreprise.component.css'
})
export class AjouterEntrepriseComponent implements OnInit{

  entreprise: Entreprise = {
    
    nom: ''  ,                        
    description: '',     
    adresseEntreprise:'', 
    codefiscal :'' ,
    email: '' ,
    numtel:'',
    siteweb :'',
};

  constructor(
   private router:Router,
   private entrepriseService: EntrepriseService,
  ){ }

  ngOnInit(): void {
     }

  cancel():void {
    this.router.navigate(['/entreprise']);
  }

  enregistrerEntreprise() {
    this.entrepriseService.enregistrerEntreprise(this.entreprise).subscribe(
        response => {
          console.log('Entreprise ajoutée avec succès!', response);
          
        },

    );
    this.router.navigate(['/entreprise']);
 }   
}



