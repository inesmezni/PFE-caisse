import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ArticleService } from '../../../services/article/article.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from '../../../services/category/category.service';

@Component({
  selector: 'app-modifier-article',
  templateUrl: './modifier-article.component.html',
  styleUrls: ['./modifier-article.component.css']
})
export class ModifierArticleComponent implements OnInit {
  ArticleId!: number;
  editArticleForm!: FormGroup;
  Category: any[] = [];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private articleService: ArticleService,
    private categoryService: CategoryService,
    private fb: FormBuilder,
  ) {
    this.editArticleForm = this.fb.group({
      prixUnitaireHt: ['', Validators.required],
      prixUnitaireTtc: ['', Validators.required],
      tauxTva: ['', Validators.required],
      codeArticle: ['', Validators.required],
      designation: ['', Validators.required],
      idCategory: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.ArticleId = +this.route.snapshot.paramMap.get('id')!;
    this.articleService.getArticleById(this.ArticleId).subscribe(data => {
      this.editArticleForm.patchValue(data);
    });

    this.categoryService.getCategory().subscribe(
      (categories) => {
        this.Category = categories;
      },
      (error) => {
        console.error('Erreur lors de la récupération des catégories :', error);
      }
    );
  }

  modifierArticle() {
    this.articleService.modifierArticle(this.ArticleId, this.editArticleForm.value)
        .subscribe({
            next: response => {
                console.log('Article modifié avec succès', response);
                this.router.navigate(['/articles']);
            },
            error: error => {
                console.error('Erreur lors de la modification de l\'article', error);
            }
        });
        
  }

  cancel(): void {
    this.router.navigate(['/articles']); 
  }
}
