import { IAddress } from "./address";
import { FishingEquipment, Utility } from "./adventure";
import { Rule } from "./rule";

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
    
}