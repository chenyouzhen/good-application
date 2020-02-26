import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GoodApplicationTestModule } from '../../../test.module';
import { MappingDetailComponent } from 'app/entities/mapping/mapping-detail.component';
import { Mapping } from 'app/shared/model/mapping.model';

describe('Component Tests', () => {
  describe('Mapping Management Detail Component', () => {
    let comp: MappingDetailComponent;
    let fixture: ComponentFixture<MappingDetailComponent>;
    const route = ({ data: of({ mapping: new Mapping(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GoodApplicationTestModule],
        declarations: [MappingDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MappingDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MappingDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load mapping on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mapping).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
