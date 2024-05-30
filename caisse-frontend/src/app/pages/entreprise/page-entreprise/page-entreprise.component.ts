import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-page-entreprise',
  templateUrl: './page-entreprise.component.html',
  styleUrl: './page-entreprise.component.css'
})
export class PageEntrepriseComponent {


constructor(
  private router: Router){}

nouvelEntreprise(): void {
  this.router.navigate(['ajouterEntreprise']); 
}}