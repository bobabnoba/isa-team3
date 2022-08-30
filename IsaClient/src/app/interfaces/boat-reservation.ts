import { Report } from "./report";

export interface BoatReservation {
    id : number;
    startDate : Date;
    endDate : Date;
    isCaptain : boolean;
    price : number;
    guests : number;
    report : Report;
    cancelingPercentage : number;
    boatName: string; 
}
