import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMapping } from 'app/shared/model/mapping.model';
import { MappingService } from './mapping.service';
import { MappingDeleteDialogComponent } from './mapping-delete-dialog.component';

@Component({
  selector: 'jhi-mapping',
  templateUrl: './mapping.component.html'
})
export class MappingComponent implements OnInit, OnDestroy {
  mappings?: IMapping[];
  eventSubscriber?: Subscription;

  constructor(protected mappingService: MappingService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.mappingService.query().subscribe((res: HttpResponse<IMapping[]>) => (this.mappings = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMappings();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMapping): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMappings(): void {
    this.eventSubscriber = this.eventManager.subscribe('mappingListModification', () => this.loadAll());
  }

  delete(mapping: IMapping): void {
    const modalRef = this.modalService.open(MappingDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.mapping = mapping;
  }
}
