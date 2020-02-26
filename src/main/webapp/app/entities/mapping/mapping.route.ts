import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMapping, Mapping } from 'app/shared/model/mapping.model';
import { MappingService } from './mapping.service';
import { MappingComponent } from './mapping.component';
import { MappingDetailComponent } from './mapping-detail.component';
import { MappingUpdateComponent } from './mapping-update.component';

@Injectable({ providedIn: 'root' })
export class MappingResolve implements Resolve<IMapping> {
  constructor(private service: MappingService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMapping> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mapping: HttpResponse<Mapping>) => {
          if (mapping.body) {
            return of(mapping.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Mapping());
  }
}

export const mappingRoute: Routes = [
  {
    path: '',
    component: MappingComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Mappings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MappingDetailComponent,
    resolve: {
      mapping: MappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Mappings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MappingUpdateComponent,
    resolve: {
      mapping: MappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Mappings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MappingUpdateComponent,
    resolve: {
      mapping: MappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Mappings'
    },
    canActivate: [UserRouteAccessService]
  }
];
