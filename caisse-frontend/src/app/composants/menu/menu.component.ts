import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  list =[
    {
      number:'1',
      name:'Tableau de bord Gerant',
      icon:'fa-solid fa-chart-line',
      url:'statistiques',
    },
    {
      number:'2',
      name:'Categories',
      icon:'fa-sharp fa-solid fa-list',
      url:'categories',
    },
    {
      number:'3',
      name:'Articles',
      icon:'fas fa-file-alt',
      url:'articles',
    },
   
    {
      number: '4',
      name: 'Clients',
      icon: 'fa-solid fa-user',
      url: 'listClient'
    }


  ]
  
  

  
  constructor(
    
  ) { }

  ngOnInit(): void {
  }

}