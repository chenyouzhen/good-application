import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { GoodApplicationSharedModule } from 'app/shared/shared.module';
import { GoodApplicationCoreModule } from 'app/core/core.module';
import { GoodApplicationAppRoutingModule } from './app-routing.module';
import { GoodApplicationHomeModule } from './home/home.module';
import { GoodApplicationEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    GoodApplicationSharedModule,
    GoodApplicationCoreModule,
    GoodApplicationHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    GoodApplicationEntityModule,
    GoodApplicationAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class GoodApplicationAppModule {}
