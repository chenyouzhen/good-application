import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMapping } from 'app/shared/model/mapping.model';
import { MappingService } from './mapping.service';

@Component({
  templateUrl: './mapping-delete-dialog.component.html'
})
export class MappingDeleteDialogComponent {
  mapping?: IMapping;

  constructor(protected mappingService: MappingService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mappingService.delete(id).subscribe(() => {
      this.eventManager.broadcast('mappingListModification');
      this.activeModal.close();
    });
  }
}
