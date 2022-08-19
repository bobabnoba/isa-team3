export enum Rank {
    REGULAR_CLIENT,
    SILVER_CLIENT,
    GOLD_CLIENT,
    REGULAR_ADVERTISER,
    SILVER_ADVERTISER,
    GOLD_ADVERTISER,
}

export interface UserRank {
    id : number;
    name : string;
    minPoints : number;
    reservationPercentage : number;
    percentage : number;
}
