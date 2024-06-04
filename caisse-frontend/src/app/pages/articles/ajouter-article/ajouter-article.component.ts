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
    idCategory: 0, // Assurez-vous d'initialiser correctement l'ID de la catégorie
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
    const articleToSend = {
      ...this.article,
      category: { id: this.article.idCategory } // Ajouter l'objet category avec l'id de la catégorie
    };

    this.articleService.enregistrerArticle(articleToSend).subscribe(
      response => {
        console.log('Article ajouté avec succès!', response);
        this.router.navigate(['/articles']);
      },
      error => {
        console.error('Erreur lors de l\'ajout de l\'article :', error);
      }
    );
  }


  
  

  calculerTTC(): void {
    if (this.article.prixUnitaireHt && this.article.tauxTva) {
      const prixUnitaireHt = parseFloat(this.article.prixUnitaireHt);
      const tauxTva = parseFloat(this.article.tauxTva);
  
      if (!isNaN(prixUnitaireHt) && !isNaN(tauxTva)) {
        // prixHT + (prixHT * (tauxTVA / 100))
        this.article.prixUnitaireTtc = (prixUnitaireHt + (prixUnitaireHt * (tauxTva / 100))).toFixed(2);
      }
    }
  }
}  