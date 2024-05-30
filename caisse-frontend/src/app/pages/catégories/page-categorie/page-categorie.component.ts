import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from '../../../services/category/category.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-page-categorie',
  templateUrl: './page-categorie.component.html',
  styleUrls: ['./page-categorie.component.css'], 
  providers: [CategoryService] 
})

export class PageCategorieComponent implements OnInit {
  Category: any[] = []; // Stocke les catégories récupérées depuis le serveur
  selectedCatIdToDelete ?= -1;
  errorMsgs= ''; // Variable pour stocker les messages d'erreur

  constructor(
    private router: Router,
    private categoryService: CategoryService
  ) {}

  ngOnInit() {
    this.findAllCategory();
  }

  findAllCategory(){
    console.log('on init...');
    this.categoryService.getCategory().subscribe(
      (result) => {
        this.Category = result; 
      }
      
    );
  }

  nouvelCategorie(): void {
    this.router.navigate(['nouvelleCategorie']); 
  }

  
  confirmerEtSupprimerCat(): void {
    if (this.selectedCatIdToDelete !== -1) {
      this.categoryService.deleteCategory(this.selectedCatIdToDelete)
      .subscribe(res => {
        this.findAllCategory();
      }, error => {
        this.errorMsgs = error.error.message;
      });
    }
  }
  
  
  


  annulerSuppressionCat(): void {
    this.selectedCatIdToDelete = -1;
  }

  selectCatPourSupprimer(id?: number): void {
    this.selectedCatIdToDelete = id;
  }
}
