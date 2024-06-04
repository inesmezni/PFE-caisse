export interface Article {
  id?: number; 
  idCategory?: number; 
  prixUnitaireHt?: string;
  prixUnitaireTtc?: string;
  tauxTva?: string;
  codeArticle?: string;
  designation?: string;
  creationDate?: Date;
  category?: any; 
}