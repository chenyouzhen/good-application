import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMapping, Mapping } from 'app/shared/model/mapping.model';
import { MappingService } from './mapping.service';
import { IAssemblyLine } from 'app/shared/model/assembly-line.model';
import { AssemblyLineService } from 'app/entities/assembly-line/assembly-line.service';

@Component({
  selector: 'jhi-mapping-update',
  templateUrl: './mapping-update.component.html'
})
export class MappingUpdateComponent implements OnInit {
  isSaving = false;
  assemblylines: IAssemblyLine[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    deviceId: [null, [Validators.required]],
    description: [],
    assemblyLineId: []
  });

  constructor(
    protected mappingService: MappingService,
    protected assemblyLineService: AssemblyLineService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mapping }) => {
      this.updateForm(mapping);

      this.assemblyLineService.query().subscribe((res: HttpResponse<IAssemblyLine[]>) => (this.assemblylines = res.body || []));
    });
  }

  updateForm(mapping: IMapping): void {
    this.editForm.patchValue({
      id: mapping.id,
      name: mapping.name,
      deviceId: mapping.deviceId,
      description: mapping.description,
      assemblyLineId: mapping.assemblyLineId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mapping = this.createFromForm();
    if (mapping.id !== undefined) {
      this.subscribeToSaveResponse(this.mappingService.update(mapping));
    } else {
      this.subscribeToSaveResponse(this.mappingService.create(mapping));
    }
  }

  private createFromForm(): IMapping {
    return {
      ...new Mapping(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      deviceId: this.editForm.get(['deviceId'])!.value,
      description: this.editForm.get(['description'])!.value,
      assemblyLineId: this.editForm.get(['assemblyLineId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMapping>>): void {
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

  trackById(index: number, item: IAssemblyLine): any {
    return item.id;
  }
}
