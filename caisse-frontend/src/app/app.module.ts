import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CaissierDashboardComponent } from './caissier-dashboard/caissier-dashboard.component';
import { GerantDashboardComponent } from './gerant-dashboard/gerant-dashboard.component';
import { PageStatistiquesComponent } from './pages/page-statistiques/page-statistiques.component';
import { MenuComponent } from './composants/menu/menu.component';
import { HeaderComponent } from './composants/header/header.component';
import { PageArticleComponent } from './pages/articles/page-article/page-article.component';
import { DetailArticleComponent } from './composants/detail-article/detail-article.component';
import { ButtonActionComponent } from './composants/button-action/button-action.component';
import { AjouterArticleComponent } from './pages/articles/ajouter-article/ajouter-article.component';
import { PageCategorieComponent } from './pages/catégories/page-categorie/page-categorie.component';
import { NouvelleCategorieComponent } from './pages/catégories/nouvelle-categorie/nouvelle-categorie.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ModifierCategorieComponent } from './pages/catégories/modifier-categorie/modifier-categorie.component';
import { ListClientComponent } from './pages/clients/list-client/list-client.component';
import { PageEntrepriseComponent } from './pages/entreprise/page-entreprise/page-entreprise.component';
import { AjouterEntrepriseComponent } from './pages/entreprise/ajouter-entreprise/ajouter-entreprise.component';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { AppAuthGuard } from './services/auth/auth.guard';
import { initializer } from './services/auth/keycloak-inisializer';
import { httpInterceptorProviders } from './services/utils';
import { SidenavComponent } from './composants/sidenav/sidenav.component';
import { AjouterClientComponent } from './pages/clients/ajouter-client/ajouter-client.component';
import { ModifierClientComponent } from './pages/clients/modifier-client/modifier-client.component';
import { PageClientComponent } from './pages/clients/page-client/page-client.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { MvmStockComponent } from './pages/mvm-stock/mvm-stock.component';
import { ListFactureComponent } from './list-facture/list-facture.component';
import { AjouterFactureComponent } from './ajouter-facture/ajouter-facture.component';

@NgModule({
  declarations: [
    AppComponent,
    CaissierDashboardComponent,
    GerantDashboardComponent,
    PageStatistiquesComponent,
    MenuComponent,
    HeaderComponent,
    PageArticleComponent,
    DetailArticleComponent,
    ButtonActionComponent,
    AjouterArticleComponent,
    PageCategorieComponent,
    NouvelleCategorieComponent,
    ModifierCategorieComponent,
    ListClientComponent,
    PageEntrepriseComponent,
    AjouterEntrepriseComponent,
    SidenavComponent,
    AjouterClientComponent,
    ModifierClientComponent,
    PageClientComponent,
    DashboardComponent,
    MvmStockComponent,
    ListFactureComponent,
    AjouterFactureComponent
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    KeycloakAngularModule
  
  ],
  providers: [
    httpInterceptorProviders,
    {
      provide: APP_INITIALIZER,
      useFactory: initializer,
      deps: [KeycloakService],
      multi: true,
    },
    AppAuthGuard,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
