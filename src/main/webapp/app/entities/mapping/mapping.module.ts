import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GoodApplicationSharedModule } from 'app/shared/shared.module';
import { MappingComponent } from './mapping.component';
import { MappingDetailComponent } from './mapping-detail.component';
import { MappingUpdateComponent } from './mapping-update.component';
import { MappingDeleteDialogComponent } from './mapping-delete-dialog.component';
import { mappingRoute } from './mapping.route';

@NgModule({
  imports: [GoodApplicationSharedModule, RouterModule.forChild(mappingRoute)],
  declarations: [MappingComponent, MappingDetailComponent, MappingUpdateComponent, MappingDeleteDialogComponent],
  entryComponents: [MappingDeleteDialogComponent]
})
export class GoodApplicationMappingModule {}
