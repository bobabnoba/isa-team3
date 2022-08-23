
export interface Report {

    id : number;
    type: string;
    ownerEmail : string;
    clientEmail : string;
    comment : string;
    clientShowedUp : boolean;
    adminReviewed : boolean;

    instructorId : number;
    boatOwnerId : number;
    homeOwnerId : number;

}
