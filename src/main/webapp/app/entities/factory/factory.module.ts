import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GoodApplicationSharedModule } from 'app/shared/shared.module';
import { FactoryComponent } from './factory.component';
import { FactoryDetailComponent } from './factory-detail.component';
import { FactoryUpdateComponent } from './factory-update.component';
import { FactoryDeleteDialogComponent } from './factory-delete-dialog.component';
import { factoryRoute } from './factory.route';

@NgModule({
  imports: [GoodApplicationSharedModule, RouterModule.forChild(factoryRoute)],
  declarations: [FactoryComponent, FactoryDetailComponent, FactoryUpdateComponent, FactoryDeleteDialogComponent],
  entryComponents: [FactoryDeleteDialogComponent]
})
export class GoodApplicationFactoryModule {}
