import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-list-facture',
  templateUrl: './list-facture.component.html',
  styleUrl: './list-facture.component.css'
})
export class ListFactureComponent implements OnInit {


  constructor ( 
    private router :Router
  ) {}

  ngOnInit() {}


  nouvelFacture():void{
    this.router.navigate(['caissier-dashboard/ajouterFacture']);
  }

}
