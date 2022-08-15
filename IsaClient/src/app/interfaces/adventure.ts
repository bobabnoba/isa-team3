import { IAddress } from "./address";
import { Rule } from "./rule";
import { SpecialOffer } from "./special-offer";

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
    codeOfConduct : Rule[];
    pricePerDay : number;
    cancelingPercentage : number;
    maxNumberOfParticipants : number;
    instructorFirstName: string;
    instructorLastName: string;
    instructorBio : string;
    fishingEquipment : FishingEquipment[];
    utilities : Utility[];
    instructorEmail : string;
    durationInHours : number;
    specialOffers : SpecialOffer[];
}
