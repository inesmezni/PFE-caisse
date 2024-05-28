import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ArticleService } from '../../../services/article/article.service';
import { Article } from '../article';
import { Category } from '../../catégories/category';
import { CategoryService } from '../../../services/category/category.service';

@Component({
  selector: 'app-ajouter-article',
  templateUrl: './ajouter-article.component.html',
  styleUrls: ['./ajouter-article.component.css']  
})
export class AjouterArticleComponent implements OnInit {

  article: Article = {
    idCategory: '', // Assurez-vous d'initialiser correctement l'ID de la catégorie
    prixUnitaireHt: '',
    prixUnitaireTtc: '',
    tauxTva: '',
    codeArticle: '',
    designation: '',
  };

  Category: any[] = [];

  constructor(
    private router: Router,
    private activatedRouter: ActivatedRoute,
    private articleService: ArticleService,
    private categoryService: CategoryService
  ) {}

  ngOnInit(): void {
    console.log('on init...');
    this.categoryService.getCategory().subscribe(
      (result) => {
        this.Category = result; 
      }
    );
  }

  cancel(): void {
    this.router.navigate(['/articles']); 
  }

  enregistrerArticle() {
    this.articleService.enregistrerArticle(this.article).subscribe(
      response => {
        console.log('Article ajoutée avec succès!', response);
      },
      error => {
        console.error('Erreur lors de l\'ajout de l\'article :', error);
      }
    );
    this.router.navigate(['/articles']);
  }
}
