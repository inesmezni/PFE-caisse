import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from '../../../services/category/category.service';
import { Category } from '../category';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-modifier-categorie',
  templateUrl: './modifier-categorie.component.html',
  styleUrl: './modifier-categorie.component.css'
})
export class ModifierCategorieComponent implements OnInit {

 
  categoryId!:number;
  
    category: Category = {
    
      code: '',
      designation: ''
  };
  
    constructor(
      private route: ActivatedRoute,
     private router:Router,
     private categoryService: CategoryService
    ){}
  
    ngOnInit(): void {
      
      
    }


    



    cancel():void {
      this.router.navigate(['/categories']);
    }
  
     
  }
  
