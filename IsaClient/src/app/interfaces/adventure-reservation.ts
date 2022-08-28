import { Report } from "./report";

export interface AdventureReservation {
    id : number;
    startDate : Date;
    endDate : Date;
    durationInHours : number;
    price : number;
    guests : number;
    report : Report;
    cancelingPercentage : number;
}
