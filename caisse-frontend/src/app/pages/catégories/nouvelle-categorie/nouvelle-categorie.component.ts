import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from '../../../services/category/category.service';
import { Category } from '../category';

@Component({
  selector: 'app-nouvelle-categorie',
  templateUrl: './nouvelle-categorie.component.html',
  styleUrls: ['./nouvelle-categorie.component.css'],
  
})


export class NouvelleCategorieComponent implements OnInit {
  
  category: Category = {
    code: '',
    designation: ''
};

  constructor(
   private router:Router,
   private categoryService: CategoryService
  ){ }

  ngOnInit(): void {
     }

  cancel():void {
    this.router.navigate(['/categories']);
  }

  enregistrerCategory() {
    this.categoryService.enregistrerCategory(this.category).subscribe(
        response => {
          console.log('Catégorie ajoutée avec succès!', response);
          
        },

        error => {
          console.error('Catégorie existe déja !', error);
        }
    );
    this.router.navigate(['/categories']);
 }   
}
