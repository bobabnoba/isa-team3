import { Utility } from "./adventure";
import { LoggedUser } from "./logged-user";
import { ReservationType } from "./special-offer";

export interface Reservation {
    id: number;
    isCancelled : boolean;
    type : ReservationType;
    startDate: string;
    endDate: string;
    guests: number;
    price: number;
    utilities: Utility[];
    client : LoggedUser;
    cancelingPercentage : number;
}
