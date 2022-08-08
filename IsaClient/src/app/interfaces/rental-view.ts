import { IAddress } from "./address";
import { IOwnerInfo, IUtility } from "./vacation-house-profile";

export interface IProfileView {
    id:number;
    name: string,
    address: IAddress,
    description: string,
    utilities: IUtility[],
    pricePerDay: number,
    rating: number,
    rentalType: string,
    owner: IOwnerInfo
}

