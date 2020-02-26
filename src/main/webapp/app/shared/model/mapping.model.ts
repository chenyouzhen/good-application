import { IObservation } from 'app/shared/model/observation.model';

export interface IMapping {
  id?: number;
  name?: string;
  deviceId?: string;
  description?: string;
  observations?: IObservation[];
  assemblyLineId?: number;
}

export class Mapping implements IMapping {
  constructor(
    public id?: number,
    public name?: string,
    public deviceId?: string,
    public description?: string,
    public observations?: IObservation[],
    public assemblyLineId?: number
  ) {}
}
