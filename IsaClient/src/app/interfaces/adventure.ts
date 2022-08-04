export interface FishingEquipment {
    name: string;
}

export interface AdditionalService{
    name: string;
    price : number;
}

export interface Adventure {
    id: number;
    title : string;
    address : string;
    description : string;
    images : string[];
    codeOfConduct : string;
    pricePerDay : number;
    cancelingPercentage : number;
    maxNumberOfParticipants : number;
    instructorFirstName: string;
    instructorLastName: string;
    instructorBio : string;
    fishingEquipment : FishingEquipment[];
    additionalServices : AdditionalService[];
}
