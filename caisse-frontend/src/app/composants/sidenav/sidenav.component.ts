import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrl: './sidenav.component.css'
})
export class SidenavComponent implements OnInit {
list =[
  {
    number:'1',
    name:'Tableau de bord Caissier',
    icon:'fa-solid fa-chart-line',
    url:'caissier-dashboard/dashboard',
  },
  {
    number:'2',
    name:'Clients',
    icon:'fa-solid fa-user',
    url:'caissier-dashboard/client',
  },
  {
    number:'3',
    name:'Facture',
    icon:'fas fa-file-invoice',
    url:'caissier-dashboard/listeFacture',
  },
]

  constructor(){}

  ngOnInit(): void {
    
  }
}



