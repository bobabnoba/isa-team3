import { IAddress } from "./address";
import { FishingEquipment, Utility } from "./adventure";
import { BoatAvailability } from "./boat-availability";
import { Rule } from "./rule";
import { SpecialOffer } from "./special-offer";

export interface Boat {
    id: number;
    name : string;
    address : IAddress;
    deleted : boolean;
    rating : number;   
    type : string;
    length : number;   
    engineCount : number;
    enginePower : number;
    maxSpeed : number;
    guestLimit : number;
    pricePerDay : number;
    description : string;
    information : string;
    navigationTypes : string[];
    images : string[];
    codeOfConduct : Rule[];
    utilities : Utility[];
    fishingEquipment : FishingEquipment[];
    cancelingPercentage : number;
    ownerEmail : string;
    ownerFirstName: string;
    ownerLastName: string;
    availability: BoatAvailability[];
    specialOffers : SpecialOffer[];
}
