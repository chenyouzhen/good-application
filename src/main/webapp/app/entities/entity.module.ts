import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'factory',
        loadChildren: () => import('./factory/factory.module').then(m => m.GoodApplicationFactoryModule)
      },
      {
        path: 'product',
        loadChildren: () => import('./product/product.module').then(m => m.GoodApplicationProductModule)
      },
      {
        path: 'assembly-line',
        loadChildren: () => import('./assembly-line/assembly-line.module').then(m => m.GoodApplicationAssemblyLineModule)
      },
      {
        path: 'observation',
        loadChildren: () => import('./observation/observation.module').then(m => m.GoodApplicationObservationModule)
      },
      {
        path: 'alarm',
        loadChildren: () => import('./alarm/alarm.module').then(m => m.GoodApplicationAlarmModule)
      },
      {
        path: 'kpi',
        loadChildren: () => import('./kpi/kpi.module').then(m => m.GoodApplicationKpiModule)
      },
      {
        path: 'record',
        loadChildren: () => import('./record/record.module').then(m => m.GoodApplicationRecordModule)
      },
      {
        path: 'mapping',
        loadChildren: () => import('./mapping/mapping.module').then(m => m.GoodApplicationMappingModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class GoodApplicationEntityModule {}
