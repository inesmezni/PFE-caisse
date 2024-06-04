import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from '../../../services/category/category.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-modifier-categorie',
  templateUrl: './modifier-categorie.component.html',
  styleUrls: ['./modifier-categorie.component.css']
})
export class ModifierCategorieComponent implements OnInit {

  categoryId!: number;
  editCategoryForm!: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private categoryService: CategoryService,
    private fb: FormBuilder,
  ) {
    this.editCategoryForm = this.fb.group({
      code: ['', Validators.required],
      designation: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.categoryId = +this.route.snapshot.paramMap.get('id')!;
    this.categoryService.getCategoryById(this.categoryId).subscribe(data => {
      this.editCategoryForm.patchValue(data);
    });
  }

  
  modifierCategory() {
    this.categoryService.modifierCategory(this.categoryId, this.editCategoryForm.value)
        .subscribe({
            next: response => {
                console.log('Category updated successfully', response);
            },
            error: error => {
                console.error('Error updating category', error);
            }
        });
        this.router.navigate(['/categories']);
}

  cancel(): void {
    this.router.navigate(['/categories']);
  }
}
