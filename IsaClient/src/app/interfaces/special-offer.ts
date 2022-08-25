import { Utility } from "./adventure";

export enum ReservationType {
    BOAT,
    VACATION_HOME,
    ADVENTURE
}

export interface SpecialOffer {
    id : number;
    discount : number;
    price : number;
    activeFrom : string;
    activeTo : string;
    reservationStartDate : string;
    reservationEndDate : string;
    guests : number;
    type : ReservationType;
    utilities : Utility[];
    isCaptain : boolean;
}
