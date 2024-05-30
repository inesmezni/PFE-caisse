import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CaissierDashboardComponent } from './caissier-dashboard/caissier-dashboard.component';
import { GerantDashboardComponent } from './gerant-dashboard/gerant-dashboard.component';
import { PageStatistiquesComponent } from './pages/page-statistiques/page-statistiques.component';
import { PageArticleComponent } from './pages/articles/page-article/page-article.component';
import { AjouterArticleComponent } from './pages/articles/ajouter-article/ajouter-article.component';
import { NouvelleCategorieComponent } from './pages/catégories/nouvelle-categorie/nouvelle-categorie.component';
import { PageCategorieComponent } from './pages/catégories/page-categorie/page-categorie.component';
import { ModifierCategorieComponent } from './pages/catégories/modifier-categorie/modifier-categorie.component';
import { ListClientComponent } from './pages/clients/list-client/list-client.component';
import { PageEntrepriseComponent } from './pages/entreprise/page-entreprise/page-entreprise.component';
import { AjouterEntrepriseComponent } from './pages/entreprise/ajouter-entreprise/ajouter-entreprise.component';
import { AppAuthGuard } from './services/auth/auth.guard';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { PageClientComponent } from './pages/clients/page-client/page-client.component';
import { AjouterClientComponent } from './pages/clients/ajouter-client/ajouter-client.component';
import { ModifierClientComponent } from './pages/clients/modifier-client/modifier-client.component';
import { MvmStockComponent } from './pages/mvm-stock/mvm-stock.component';
import { AjouterFactureComponent } from './ajouter-facture/ajouter-facture.component';
import { ListFactureComponent } from './list-facture/list-facture.component';
import { PromotionComponent } from './pages/promotion/promotion.component';


const routes: Routes = [
  { 
    path: 'caissier-dashboard',
     component: CaissierDashboardComponent,
     children:[
      {
      path:'dashboard',
       component:DashboardComponent,
      },
      {
        path:'client',
         component:PageClientComponent,
        },
        {
          path:'ajouterClient',
           component:AjouterClientComponent,
          },
          {
            path:':id/modifierClient',
             component:ModifierClientComponent,
            },
            {
              path:'listeFacture',
               component:ListFactureComponent,
              },
            {
              path:'ajouterFacture',
               component:AjouterFactureComponent,
              },
    ]
     },

  {
     path: '', 
     component: GerantDashboardComponent ,
     canActivate: [AppAuthGuard], 
     children:[
  {
  path:'statistiques',
   component:PageStatistiquesComponent
  },
  {
    path:'articles',
     component:PageArticleComponent
    },
    {
      path:'ajouterArticle',
      component:AjouterArticleComponent
    },
    {
      path:'categories',
      component:PageCategorieComponent
    },
    {
      path:'nouvelleCategorie',
      component:NouvelleCategorieComponent
    },
    {
      path:'modifierCategorie/:id',
      component:ModifierCategorieComponent
    },
    {
      path:'listClient',
      component:ListClientComponent
    },
    {
      path:'entreprise',
      component:PageEntrepriseComponent
    },
    {
      path:'ajouterEntreprise',
      component:AjouterEntrepriseComponent
    },
    {
      path:'mvmStock',
      component:MvmStockComponent
    },
    {
      path:'promotion',
      component:PromotionComponent
    }

  ]

  },
  { path: '', redirectTo: '/dashboard-gerant', pathMatch: 'full' } // Rediriger vers le tableau de bord du gerant par défaut
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
