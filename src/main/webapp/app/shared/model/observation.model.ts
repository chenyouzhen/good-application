import { Moment } from 'moment';

export interface IObservation {
  id?: number;
  phenomenonTime?: Moment;
  result?: string;
  resultTime?: Moment;
  property?: string;
  unitOfMeasurement?: string;
  intervalTime?: number;
  reserve?: string;
  assemblyLineId?: number;
  mappingId?: number;
}

export class Observation implements IObservation {
  constructor(
    public id?: number,
    public phenomenonTime?: Moment,
    public result?: string,
    public resultTime?: Moment,
    public property?: string,
    public unitOfMeasurement?: string,
    public intervalTime?: number,
    public reserve?: string,
    public assemblyLineId?: number,
    public mappingId?: number
  ) {}
}
