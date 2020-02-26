import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GoodApplicationSharedModule } from 'app/shared/shared.module';
import { AlarmComponent } from './alarm.component';
import { AlarmDetailComponent } from './alarm-detail.component';
import { AlarmUpdateComponent } from './alarm-update.component';
import { AlarmDeleteDialogComponent } from './alarm-delete-dialog.component';
import { alarmRoute } from './alarm.route';

@NgModule({
  imports: [GoodApplicationSharedModule, RouterModule.forChild(alarmRoute)],
  declarations: [AlarmComponent, AlarmDetailComponent, AlarmUpdateComponent, AlarmDeleteDialogComponent],
  entryComponents: [AlarmDeleteDialogComponent]
})
export class GoodApplicationAlarmModule {}
