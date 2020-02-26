import { Moment } from 'moment';
import { IObservation } from 'app/shared/model/observation.model';
import { IMapping } from 'app/shared/model/mapping.model';
import { IRecord } from 'app/shared/model/record.model';

export interface IAssemblyLine {
  id?: number;
  name?: string;
  type?: string;
  description?: string;
  capacity?: Moment;
  planCapacity?: Moment;
  reserve?: string;
  observations?: IObservation[];
  mappings?: IMapping[];
  records?: IRecord[];
  productId?: number;
}

export class AssemblyLine implements IAssemblyLine {
  constructor(
    public id?: number,
    public name?: string,
    public type?: string,
    public description?: string,
    public capacity?: Moment,
    public planCapacity?: Moment,
    public reserve?: string,
    public observations?: IObservation[],
    public mappings?: IMapping[],
    public records?: IRecord[],
    public productId?: number
  ) {}
}
