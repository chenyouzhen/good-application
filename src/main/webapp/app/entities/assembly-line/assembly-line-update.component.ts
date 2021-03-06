import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IAssemblyLine, AssemblyLine } from 'app/shared/model/assembly-line.model';
import { AssemblyLineService } from './assembly-line.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';

@Component({
  selector: 'jhi-assembly-line-update',
  templateUrl: './assembly-line-update.component.html'
})
export class AssemblyLineUpdateComponent implements OnInit {
  isSaving = false;
  products: IProduct[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    type: [null, [Validators.required]],
    description: [],
    capacity: [null, [Validators.required]],
    planCapacity: [null, [Validators.required]],
    reserve: [],
    productId: []
  });

  constructor(
    protected assemblyLineService: AssemblyLineService,
    protected productService: ProductService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ assemblyLine }) => {
      if (!assemblyLine.id) {
        const today = moment().startOf('day');
        assemblyLine.capacity = today;
        assemblyLine.planCapacity = today;
      }

      this.updateForm(assemblyLine);

      this.productService.query().subscribe((res: HttpResponse<IProduct[]>) => (this.products = res.body || []));
    });
  }

  updateForm(assemblyLine: IAssemblyLine): void {
    this.editForm.patchValue({
      id: assemblyLine.id,
      name: assemblyLine.name,
      type: assemblyLine.type,
      description: assemblyLine.description,
      capacity: assemblyLine.capacity ? assemblyLine.capacity.format(DATE_TIME_FORMAT) : null,
      planCapacity: assemblyLine.planCapacity ? assemblyLine.planCapacity.format(DATE_TIME_FORMAT) : null,
      reserve: assemblyLine.reserve,
      productId: assemblyLine.productId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const assemblyLine = this.createFromForm();
    if (assemblyLine.id !== undefined) {
      this.subscribeToSaveResponse(this.assemblyLineService.update(assemblyLine));
    } else {
      this.subscribeToSaveResponse(this.assemblyLineService.create(assemblyLine));
    }
  }

  private createFromForm(): IAssemblyLine {
    return {
      ...new AssemblyLine(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      type: this.editForm.get(['type'])!.value,
      description: this.editForm.get(['description'])!.value,
      capacity: this.editForm.get(['capacity'])!.value ? moment(this.editForm.get(['capacity'])!.value, DATE_TIME_FORMAT) : undefined,
      planCapacity: this.editForm.get(['planCapacity'])!.value
        ? moment(this.editForm.get(['planCapacity'])!.value, DATE_TIME_FORMAT)
        : undefined,
      reserve: this.editForm.get(['reserve'])!.value,
      productId: this.editForm.get(['productId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssemblyLine>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IProduct): any {
    return item.id;
  }
}
