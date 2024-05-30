export interface Article {
  id?: number; 
  idCategory?: string;
  prixUnitaireHt?: string;
  prixUnitaireTtc?: string;
  tauxTva?: string;
  codeArticle?: string;
  designation?: string;
  creationDate?: Date;
  idEntreprise?: number;
  category?: any; 
}
