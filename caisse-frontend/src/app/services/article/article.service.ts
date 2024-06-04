import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article } from '../../pages/articles/article';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  readonly API_URL="http://localhost:8081";
  readonly ENDPOINT_getArticle="/articles/all";
  readonly ENDPOINT_postArticle="/articles/save";
  readonly ENDPOINT_deleteArticle="/articles/delete";
  readonly ENDPOINT_getByIdArticle = "/articles/article/";
  readonly ENDPOINT_putArticle = "/articles/put/";


  constructor(private httpClient: HttpClient) {}


  public getArticle(): Observable<Article[]> {
    return this.httpClient.get<Article[]>( this.API_URL + this.ENDPOINT_getArticle);
  }

  enregistrerArticle(article: Article): Observable<Article> {
    return this.httpClient.post<Article>(this.API_URL + this.ENDPOINT_postArticle, article);
  }
 
  getArticleById(id: number): Observable<Article> {
    return this.httpClient.get<Article>(`${this.API_URL + this.ENDPOINT_getByIdArticle}${id}`);
  }

  modifierArticle(id: number, updateArticle: any): Observable<any> {
    return this.httpClient.put(`${this.API_URL + this.ENDPOINT_putArticle }${id}`, updateArticle);
  }

  deleteArticle(id?: number): Observable<any> {
    return this.httpClient.delete(`${this.API_URL + this.ENDPOINT_deleteArticle}/${id}`);
  }
}



