export enum RegistrationType {"INSTRUCTOR_ADVERTISER", "VACATION_BOAT_ADVERTISER", "VACATION_BOAT_OWNER", "CLIENT"}

export interface RegistrationRequest {
    id: number;
    response : string;
    approved : boolean;
    userEmail : string;
    registrationType : RegistrationType;
    motivation : string;
}
