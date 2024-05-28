import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../../pages/catégories/category';


@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  readonly API_URL="http://localhost:8081"
  readonly ENDPOINT_getCategory="/Category/categories/all"
  readonly ENDPOINT_postCategory="/Category/Save"
  readonly ENDPOINT_getByIdCategory="/Category/categories/{idCategory}"
  readonly ENDPOINT_putCategory="/Category/put/{idCategory}"
  readonly ENDPOINT_deleteCategory="/Category/deleteCat"
  

  constructor(private httpClient: HttpClient) {}


  public getCategory(): Observable<Category[]> {
    return this.httpClient.get<Category[]>( this.API_URL+this.ENDPOINT_getCategory)
}

  enregistrerCategory(category: Category) {
    return this.httpClient.post(this.API_URL+this.ENDPOINT_postCategory,category);
  }

  getCategoryById(id: number): Observable<Category> {
    return this.httpClient.get<Category>( this.API_URL+this.ENDPOINT_getByIdCategory);}


  modifierCategory(id: number, updateCategory: any): Observable<any> {
    return this.httpClient.put(`${this.API_URL+this.ENDPOINT_putCategory}/${id}`, updateCategory);
  }

  deleteCategory(id ?: number): Observable<any> {
    return this.httpClient.delete(`${this.API_URL+this.ENDPOINT_deleteCategory}/${id}`);
  }

 
}
