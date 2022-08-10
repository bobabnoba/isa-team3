import { IAddress } from "./address";

export interface FishingEquipment {
    id : number;
    name: string;
}

export interface Utility{
    name: string;
    price : number;
}

export interface Adventure {
    id: number;
    title : string;
    address : IAddress;
    description : string;
    images : string[];
    codeOfConduct : string[];
    pricePerDay : number;
    cancelingPercentage : number;
    maxNumberOfParticipants : number;
    instructorFirstName: string;
    instructorLastName: string;
    instructorBio : string;
    fishingEquipment : FishingEquipment[];
    utilities : Utility[];
    instructorEmail : string;
}
