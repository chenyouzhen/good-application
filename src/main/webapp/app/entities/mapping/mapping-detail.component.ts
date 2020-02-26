import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMapping } from 'app/shared/model/mapping.model';

@Component({
  selector: 'jhi-mapping-detail',
  templateUrl: './mapping-detail.component.html'
})
export class MappingDetailComponent implements OnInit {
  mapping: IMapping | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mapping }) => (this.mapping = mapping));
  }

  previousState(): void {
    window.history.back();
  }
}
